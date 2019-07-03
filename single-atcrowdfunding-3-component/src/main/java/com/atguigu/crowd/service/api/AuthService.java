package com.atguigu.crowd.service.api;

import java.util.List;
import java.util.Map;

import com.atguigu.crowd.entity.Auth;

public interface AuthService {

	List<Auth> queryAllAuth();

	List<Integer> queryAssignAuth(Integer roleId);

	void updateReletionShip(Map<String, List<Integer>> mapData);

}
