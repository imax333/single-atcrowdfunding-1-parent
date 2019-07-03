package com.atguigu.crowd.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.SecurityAdmin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.mapper.RoleMapper;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private RoleMapper  roleMapper;
	@Autowired
	private AuthMapper  authMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Collection<GrantedAuthority> authorities =new ArrayList<>();
		// 创建权限列表
		// List<GrantedAuthority> createAuthorityList = AuthorityUtils.createAuthorityList();
		
		AdminExample example = new AdminExample();
		
		example.createCriteria().andLoginacctEqualTo(username);
		
		List<Admin> adminList = adminMapper.selectByExample(example);
		
		if(adminList == null || adminList.size() != 1) {
			return null;
		}
		
		Admin admin = adminList.get(0);
		
		
		Integer adminId = admin.getId();
		// 根据用户id查询角色
		List<Role> assignRole = roleMapper.assignRole(adminId);
		List<String> assignAuthList = authMapper.assignAuthList(adminId);
		
		for (Role role : assignRole) {
			String roleName = role.getName();
			// 注意：一定要加“ROLE_”
			authorities.add(new SimpleGrantedAuthority("ROLE_"+roleName));
		}

		for (String auth : assignAuthList) {
			authorities.add(new SimpleGrantedAuthority(auth));
		}
		
		//User user = new User(username, userpswd, authorities);
		 SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);
		return securityAdmin;
	}

}
