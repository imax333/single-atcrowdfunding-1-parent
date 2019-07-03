package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    List<Admin> selectForEach(String keyword);

	void batchDelete(@Param("adminIdList") List<Integer> adminIdList);

	void deleteAssignRole(Integer adminId);

	void insertNewAssignRole(@Param("adminId")Integer adminId,@Param("roleIdList") List<Integer> roleIdList);
    
}