package com.atguigu.crowd.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityAdmin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Admin originalAdmin;
	public SecurityAdmin(Admin admin,Collection<GrantedAuthority> authorities) {
		super(admin.getLoginacct(), admin.getUserpswd(), true, true	, true, true, authorities);
		
		this.originalAdmin = admin;
	}
	
	public Admin getOriginalAdmin() {
		return originalAdmin;
	}

}
