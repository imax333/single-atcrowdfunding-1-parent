package com.atguigu.crowd.handler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.AuthService;
import com.atguigu.crowd.service.api.RoleService;

@Controller
public class AssignHandler {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AdminService adminService ;
	@Autowired
	private AuthService authService;
	

	@RequestMapping("/asssign/role/page")
	public String toAsssignRole(@RequestParam("adminId") Integer adminId,Model model) {
		List<Role> assignRole = roleService.queryAssignRole(adminId);
		List<Role> unAssignRole = roleService.queryUnAssignRole(adminId);
		model.addAttribute("assignRole", assignRole);
		model.addAttribute("unAssignRole", unAssignRole);
		return "assign_role";
	}
	
	@RequestMapping("/assign/role")
	public String assignRole(@RequestParam("adminId") Integer adminId,
			@RequestParam(value="roleIdList",required=false) List<Integer> roleIdList) {
		
		adminService.updateRelationShip(adminId,roleIdList);
		
		return "redirect:/query/with/search.html";
	}
	
	@ResponseBody
	@RequestMapping("/get/all/auth")
	public ResultEntity<List<Auth>> queryAllAuth(){
		List<Auth> allAuth = authService.queryAllAuth();
		return new ResultEntity<List<Auth>>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, allAuth);
	}
	
	@ResponseBody
	@RequestMapping("/query/assign/auth")
	public ResultEntity<List<Integer>> queryAssignAuth(@RequestParam("roleId") Integer roleId){
		List<Integer> list = authService.queryAssignAuth(roleId);
		return new ResultEntity<List<Integer>>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, list);
		
	}
	
	@ResponseBody
	@RequestMapping("/save/auth")
	public ResultEntity<String> doAssignAuth(@RequestBody Map<String, List<Integer>> mapData){
		
		authService.updateReletionShip(mapData);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
}
