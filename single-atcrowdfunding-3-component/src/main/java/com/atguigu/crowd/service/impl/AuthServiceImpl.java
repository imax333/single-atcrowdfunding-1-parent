package com.atguigu.crowd.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.AuthExample;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.service.api.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthMapper authMapper;

	@Override
	public List<Auth> queryAllAuth() {
		
		return authMapper.selectByExample(new AuthExample());
	}

	@Override
	public List<Integer> queryAssignAuth(Integer roleId) {
		
		return authMapper.selectAssignAuth(roleId);
	}

	@Override
	public void updateReletionShip(Map<String, List<Integer>> mapData) {
		
		List<Integer> roliIdList = mapData.get("roliIdList");
		Integer roleId = roliIdList.get(0);
		// 把之前的所有关系都删除
		authMapper.deleteOldReletionship(roleId);
		// 如果authIdList不为空 则执行添加操作  把 roleId 和 authIdList 一起传入SQL语句
		List<Integer> authIdList = mapData.get("authIdList");
		if(authIdList != null && authIdList.size() != 0) {
			authMapper.insertNewReletionship(roleId,authIdList);
		}
		
	}
}
