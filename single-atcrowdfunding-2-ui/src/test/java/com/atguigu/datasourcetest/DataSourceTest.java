package com.atguigu.datasourcetest;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations="classpath:spring-context-db.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DataSourceTest {
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testDataSource() throws SQLException {
		Connection connection = dataSource.getConnection();
		
		System.out.println(connection);
	}
	
	
}
