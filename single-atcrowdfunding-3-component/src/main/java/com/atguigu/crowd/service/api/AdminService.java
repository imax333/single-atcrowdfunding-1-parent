package com.atguigu.crowd.service.api;

import java.util.List;

import com.atguigu.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;

public interface AdminService {
//com.atguigu.crowd.service.api.AdminService.getLoginAdmin(String, String)
	Admin getLoginAdmin(String loginacct,String userpswd );
	
	PageInfo<Admin> getAdminPageInfoWithKeyword(String keyword,int pageNo,int pageSize);

	void batchRemove(List<Integer> adminIdList);

	int selectAdminCount(String loginacct);

	int saveAdmin(Admin admin);

	Admin selectAdminByAdminId(Integer adminId);

	void updateAdmin(Admin admin);

	void updateRelationShip(Integer adminId, List<Integer> roleIdList);
}
