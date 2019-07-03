package com.atguigu.crowd.service.api;



import java.util.List;

import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {

	PageInfo<Role> getRolePageinfo(Integer pageNo,String keyword, Integer pageSize);

	List<Role> queryRoleByList(List<Integer> roleIdList);

	void removeByRoleIdList(List<Integer> roleIdList);

	void saveRole(String roleName);

	void updateRole(Role role);

	List<Role> queryAssignRole(Integer adminId);

	List<Role> queryUnAssignRole(Integer adminId);

}
