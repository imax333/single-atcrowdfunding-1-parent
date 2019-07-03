package com.atguigu.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.entity.MenuExample;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.service.api.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getMenuList() {
		
		MenuExample example = new MenuExample();
		return menuMapper.selectByExample(example);
	}

	@Override
	public void saveMenu(Menu menu) {
		
		menuMapper.insertSelective(menu);
	}

	@Override
	public Menu selectMenuById(Integer currentId) {
		
		return menuMapper.selectByPrimaryKey(currentId);
	}

	@Override
	public void deleteMenuById(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void updateMenu(Menu menu) {
		menuMapper.updateByPrimaryKeySelective(menu);
	}
}
