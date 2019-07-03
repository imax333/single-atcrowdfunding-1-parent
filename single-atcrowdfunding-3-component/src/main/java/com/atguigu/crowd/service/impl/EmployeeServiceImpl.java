package com.atguigu.crowd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.mapper.EmployeeMapper;
import com.atguigu.crowd.service.api.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public void updateEmployee(Integer age, double salary,Integer empId) {
		employeeMapper.updateAgeById(empId,age);
		employeeMapper.updateSalaryById(empId,salary);
	}

}
