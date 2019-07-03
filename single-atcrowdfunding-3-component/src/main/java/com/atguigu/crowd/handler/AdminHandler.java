package com.atguigu.crowd.handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.constant.ArgumentsConstant;
import com.atguigu.constant.AttrNameConstant;
import com.atguigu.constant.MessageConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;



@Controller
public class AdminHandler {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Autowired
	private AdminService adminService;
		/**
		 * 登录方法  测试  提交测试信息   远程修改测试  再次测试
		 * @param loginacct 账号
		 * @param userpswd 密码
		 * @param model
		 * @param session
		 * @return 
		 */
	@RequestMapping("/admin/do/login")
	public String doLogin(@RequestParam("loginacct") String loginacct, @RequestParam("userpswd") String userpswd,
			Model model, HttpSession session) {

		Admin loginAdmin = adminService.getLoginAdmin(loginacct, userpswd);
		if (loginAdmin == null) {
			model.addAttribute(AttrNameConstant.MESSAGE, MessageConstant.LOGIN_FAILED_MESSAGE);

			return "admin_login";
		}
		session.setAttribute(AttrNameConstant.LOGIN_ADMIN, loginAdmin);

		return "main";

	}
	/**
	 * 注销方法
	 * @param session
	 * @return
	 */
	@RequestMapping("/admin/do/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();

		return "redirect:/index.html";
	}
	/**
	 * 查询方法
	 * @param keyword 关键字
	 * @param pageNo 页码
	 * @param model
	 * @return
	 */
	@PreAuthorize(value="hasRole('PM - 项目经理')")
	@RequestMapping("/query/with/search")
	public String queryWithSearch(@RequestParam(value="keyword",defaultValue="") String keyword, 
			@RequestParam(value="pageNo",defaultValue="1")int pageNo,
			 Model model) {
		PageInfo<Admin> adminList = adminService.getAdminPageInfoWithKeyword(keyword, pageNo, ArgumentsConstant.PAGE_SIZE);
		model.addAttribute(AttrNameConstant.PAGE, adminList);
		return "admin_page";

	} 
	/**
	 * 删除方法
	 * @param adminIdList 选中的用户的ID集合
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/admin/to/remove")
	public ResultEntity<String> batchRemove(@RequestBody List<Integer> adminIdList ) {
		ResultEntity<String> resultEntity = new ResultEntity<>();
		
		try {
			adminService.batchRemove(adminIdList);
			resultEntity.setResult(ResultEntity.SUCCESS);
			
		} catch (Exception e) {
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(e.getMessage());
		}
		return resultEntity;
	}
	
	/**
	 * 判断用户名是否存在方法
	 * @param loginacct
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/admin/check/loginacct")
	public ResultEntity<String> getAdminCount(@RequestParam("loginacct") String loginacct) {
		
		ResultEntity<String> resultEntity = new  ResultEntity<String>();
			
		int adminCount = adminService.selectAdminCount(loginacct);
		if(adminCount == 0) {
			resultEntity.setResult(ResultEntity.SUCCESS);
			resultEntity.setMessage(ResultEntity.NO_MSG);
			resultEntity.setData(ResultEntity.NO_DATA);
		}else {
			resultEntity.setResult(ResultEntity.FAILED);
			resultEntity.setMessage(MessageConstant.LOGIN_ACCT_ALREADY_IN_USE);
			resultEntity.setData(ResultEntity.NO_DATA);
		}
		return resultEntity;
	}
	/**
	 * 添加方法
	 * @param admin
	 * @return
	 */
	@RequestMapping("/admin/to/add")
	public String saveAdmin(Admin admin) {
		String userpswd = admin.getUserpswd();
		userpswd = bCryptPasswordEncoder.encode(userpswd);
		admin.setUserpswd(userpswd);
		
		adminService.saveAdmin(admin);
		// 自动修正页面  添加一个最大的页码
		return "redirect:/query/with/search.html?pageNo="+Integer.MAX_VALUE;
		
	}
	/**
	 * 跳转修改页面
	 * @param adminId  要修改的用户的ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/to/edit/page")
	public String adminEdit(@RequestParam("adminId") Integer adminId,Model model) {
		
		System.out.println(adminId);
		Admin admin = adminService.selectAdminByAdminId(adminId);
		
		model.addAttribute("admin", admin);
		System.out.println(admin);
		
		return "admin_edit";
	}
	
	@RequestMapping("/admin/update")
	public String updateAdmin(Admin admin ,@RequestParam("pageNo") Integer pageNo) {
		
		adminService.updateAdmin(admin);
		
		return "redirect:/query/with/search.html?pageNo="+pageNo;
		
	}
	

}
