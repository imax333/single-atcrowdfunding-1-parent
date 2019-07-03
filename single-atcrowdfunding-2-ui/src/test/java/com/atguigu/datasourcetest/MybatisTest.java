package com.atguigu.datasourcetest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crowd.entity.Employee;
import com.atguigu.crowd.mapper.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring-context-db.xml","classpath:spring-context-mybatis.xml"})
public class MybatisTest {
	@Autowired
	private EmployeeMapper employeeMapper;
	@Test
	public void testMybatis() {
		
		Employee employee = employeeMapper.selectByPrimaryKey(new Integer(2));
		
		System.out.println(employee);
		
	}


}
