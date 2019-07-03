package com.atguigu.crowd.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.RoleExample;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public PageInfo<Role> getRolePageinfo( Integer pageNo,String keyword,Integer pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<Role> roleList = roleMapper.selectByKeyWord(keyword);
		PageInfo<Role> pageInfo = new PageInfo<>(roleList);
		return pageInfo;
	}

	@Override
	public List<Role> queryRoleByList(List<Integer> roleIdList) {
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andIdIn(roleIdList);
		
		return roleMapper.selectByExample(roleExample);
	}

	@Override
	public void removeByRoleIdList(List<Integer> roleIdList) {
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andIdIn(roleIdList);
		roleMapper.deleteByExample(roleExample);
		
	}

	@Override
	public void saveRole(String roleName) {
		roleMapper.insert(new Role(null, roleName));
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKeySelective(role);
		
	}

	@Override
	public List<Role> queryAssignRole(Integer adminId) {
	
		return 	roleMapper.assignRole(adminId);
	}

	@Override
	public List<Role> queryUnAssignRole(Integer adminId) {
		return 	roleMapper.unAssignRole(adminId);
	}
}
