package com.atguigu.crowd.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Admin getLoginAdmin(String loginacct, String userPsw) {
		// 1.根据登录账号从数据库查询Admin对象
		// ①创建AdminExample对象
		AdminExample adminExample = new AdminExample();
		// ②封装查询条件
		adminExample.createCriteria().andLoginacctEqualTo(loginacct);
		// ③执行查询
		List<Admin> adminList = adminMapper.selectByExample(adminExample);
		if(adminList != null && adminList.size() == 1 ) {
			// 2.获取admin对象的密码
			Admin admin = adminList.get(0);
			String pswDB= admin.getUserpswd();
			// 3.对表单密码进行加密
			userPsw = StringUtils.md5(userPsw);
			
			
			// 4.将表单密码和数据库密码进行比较
			if(Objects.equals(pswDB, userPsw)) {
				return admin;
			}
		}
		return null;
	}

	@Override
	public PageInfo<Admin> getAdminPageInfoWithKeyword(String keyword, int pageNo, int pageSize) {
		// 1.调用PageHelper的startPage()方法启用分页功能
		// 调用这个方法后面的SQL语句就会被附加LIMIT子句
		// 不调用这个方法后面的SQL语句就不会被附加LIMIT子句
		PageHelper.startPage(pageNo, pageSize);
		
		List<Admin> adminList = adminMapper.selectForEach(keyword);
		
		PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
		
		return pageInfo;
	}

	@Override
	public void batchRemove(List<Integer> adminIdList) {
		
		adminMapper.batchDelete(adminIdList);
		
	}

	@Override
	public int selectAdminCount(String loginacct) {

		AdminExample adminExample = new AdminExample();
		adminExample.createCriteria().andLoginacctEqualTo(loginacct);
		
		return 	adminMapper.countByExample(adminExample);
		
	}

	@Override
	public int saveAdmin(Admin admin) {
		String userpswd = admin.getUserpswd();
		userpswd = StringUtils.md5(userpswd);
		admin.setUserpswd(userpswd);
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		admin.setCreatetime(date);
		return adminMapper.insert(admin);
		
	}

	@Override
	public Admin selectAdminByAdminId(Integer adminId) {
		
		return adminMapper.selectByPrimaryKey(adminId);
	}

	@Override
	public void updateAdmin(Admin admin) {
		String userpswd = admin.getUserpswd();
		userpswd = StringUtils.md5(userpswd);
		admin.setUserpswd(userpswd);
		
		 adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void updateRelationShip(Integer adminId, List<Integer> roleIdList) {
		// 全部删除之前已经保存的关系
		adminMapper.deleteAssignRole(adminId);
		// 添加新的关系
		if(roleIdList != null && roleIdList.size() > 0) {
			adminMapper.insertNewAssignRole(adminId,roleIdList);
		}
		
	}

}
