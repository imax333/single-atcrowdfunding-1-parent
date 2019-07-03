package com.atguigu.crowd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.crowd.service.api.EmployeeService;

@Controller
public class EmpHandler {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/test/ssm")
	public String testEmp() {
		
		employeeService.updateEmployee(20, 3000, 1);
		return "target";
	}

}
