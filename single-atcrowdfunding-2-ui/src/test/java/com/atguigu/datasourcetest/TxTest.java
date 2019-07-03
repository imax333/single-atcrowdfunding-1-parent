package com.atguigu.datasourcetest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crowd.service.api.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring-context-db.xml","classpath:spring-context-mybatis.xml","classpath:spring-context-tx.xml"})
public class TxTest {
	@Autowired
	private EmployeeService employeeService;
	@Test
	public void test() {
		int age = 10;
		int salary = 1000;
		employeeService.updateEmployee(age, salary, 2);
	}

}
