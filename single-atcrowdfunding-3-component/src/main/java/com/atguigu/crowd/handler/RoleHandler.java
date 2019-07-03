package com.atguigu.crowd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.constant.ArgumentsConstant;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.RoleService;
import com.github.pagehelper.PageInfo;

@RestController  // 等同于 @responseBody + @Controller  
public class RoleHandler {
	@Autowired
	private RoleService roleService;
	
	/**
	 * 显示页面
	 * @param pageNo
	 * @param keyword
	 * @return
	 */
	
	@RequestMapping("/get/role/page")
	public ResultEntity<PageInfo<Role>> getRolePage(@RequestParam(value="pageNo",defaultValue="1") Integer pageNo,
			@RequestParam(value="keyword",defaultValue="") String keyword){
		PageInfo<Role> rolePageinfo = roleService.getRolePageinfo(pageNo,keyword,ArgumentsConstant.PAGE_SIZE);
		return new ResultEntity<PageInfo<Role>>(ResultEntity.SUCCESS,ResultEntity.NO_MSG,rolePageinfo);
	}
	/**
	 * 根据选择的id进行填充模态框  为删除做准备
	 * @param roleIdList
	 * @return
	 */
	
	@RequestMapping("/role/get/modal/list")
	public ResultEntity<List<Role>> getRoleModalList(@RequestBody List<Integer> roleIdList){
		
		List<Role> queryRoleByList = roleService.queryRoleByList(roleIdList);
		
		return new ResultEntity<List<Role>>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, queryRoleByList);
	}

	/**
	 * 批量删除方法
	 * @param roleIdList role ID 集合
	 * @return
	 */
	@RequestMapping("/role/madal/batch/remove")
	public ResultEntity<String> doBatchRemove(@RequestBody List<Integer> roleIdList){
		
		roleService.removeByRoleIdList(roleIdList);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	/**
	 * 添加role 方法 
	 * @param roleName 
	 * @return
	 */
	@RequestMapping("/role/to/save")
	public ResultEntity<String> doSaveRole(@RequestParam("roleName") String roleName){
		
		roleService.saveRole(roleName);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
	@RequestMapping("/role/to/update")
	public ResultEntity<String> updateRole(Role role){
		roleService.updateRole(role);
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
}
