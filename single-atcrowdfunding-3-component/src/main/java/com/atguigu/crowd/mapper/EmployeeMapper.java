package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Employee;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer empId);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

	void updateAgeById(@Param("empId")Integer empId,@Param("age") Integer age);

	void updateSalaryById(@Param("empId")Integer empId,@Param("salary") double salary);
    
}