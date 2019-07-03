package com.atguigu.crowd.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.service.api.MenuService;

@RestController
public class MenuHandler {

	@Autowired
	private MenuService menuService;
	
	/**
	 * 获取MenuList 方法
	 * @return
	 */
	@RequestMapping("/menu/get/root/node")
	public ResultEntity<Menu> getMenuList(){
		
		List<Menu> menuList = menuService.getMenuList();
		Map<Integer, Menu> menuMap = new HashMap<>();
		for (Menu menu : menuList) {
			Integer id = menu.getId();
			menuMap.put(id, menu);
		}
		
		Menu root = null;
		for (Menu menu : menuList) {
			
			Integer pid = menu.getPid();
			if(pid == null) {
				root = menu;
				continue;
			}
			Menu father = menuMap.get(pid);
			father.getChildren().add(menu);
		}
		return new ResultEntity<Menu>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, root);
	}
	/**
	 * 添加menu方法
	 * @param menu
	 * @return
	 */
	@RequestMapping("/menu/to/add")
	public ResultEntity<String> doSaveMenu(Menu menu){
		
		menuService.saveMenu(menu);
		
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
	/**
	 * 删除前查询方法
	 * @param currentId
	 * @return
	 */
	@RequestMapping("/menu/get/{id}")
	public ResultEntity<Menu> queryMenu(@PathVariable("id") Integer currentId){
		Menu menu = menuService.selectMenuById(currentId);
		
		return new ResultEntity<Menu>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, menu);
	}
	/**
	 * 删除方法
	 * @param id
	 * @return
	 */
	@RequestMapping("/menu/to/remove{id}")
	public ResultEntity<String> doRemove(@PathVariable("id") Integer id){
		menuService.deleteMenuById(id);
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
	}
	
	@RequestMapping("/menu/to/update")
	public ResultEntity<String> doUpdate(Menu menu){
		menuService.updateMenu(menu);
		return new ResultEntity<String>(ResultEntity.SUCCESS, ResultEntity.NO_MSG, ResultEntity.NO_DATA);
		
	}
	
}
