package com.atguigu.crowd.service.api;

import java.util.List;

import com.atguigu.crowd.entity.Menu;

public interface MenuService {

	List<Menu> getMenuList();

	void saveMenu(Menu menu);

	Menu selectMenuById(Integer currentId);

	void deleteMenuById(Integer id);

	void updateMenu(Menu menu);

}
