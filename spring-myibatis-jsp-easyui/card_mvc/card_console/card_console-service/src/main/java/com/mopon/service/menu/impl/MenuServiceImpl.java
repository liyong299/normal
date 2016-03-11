package com.mopon.service.menu.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.entity.BaseMenu;
import com.mopon.entity.BaseMenuOperator;
import com.mopon.entity.BaseOperator;
import com.mopon.entity.console.base.BaseMenuExt;
import com.mopon.entity.console.base.BaseMenuOperatorExt;
import com.mopon.entity.console.base.BaseOperatorExt;
import com.mopon.service.menu.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private com.mopon.service.base.BaseMenuService baseMenuService;
	
	@Autowired
	private com.mopon.service.base.BaseOperatorService baseOperatorService;
	
	@Autowired
	private com.mopon.service.base.BaseMenuOperatorService baseMenuOperatorService;
	
	/**
	 * 根据parentID获取menu列表
	 * 需要缓存
	 */
	public List<BaseMenuExt> queryMenuListByParentID(Integer parentID){
		BaseMenuExt where = new BaseMenuExt();
		where.setParentId(parentID);
		
		return queryMenuList(where);
	}
	
	public List<BaseMenuExt> queryMenuList(BaseMenuExt baseMenuExt){
		BaseMenu where = new BaseMenu();
		where.setParentId(baseMenuExt.getParentId());
		
		List<BaseMenu> list =  baseMenuService.queryList(where);
		List<BaseMenuExt> results = new ArrayList<BaseMenuExt>();
		for(BaseMenu temp: list){
			BaseMenuExt ext = new BaseMenuExt();
			
			ext.setId(temp.getId());
			ext.setName(temp.getName());
			ext.setParentId(temp.getParentId());
			ext.setPosition(temp.getPosition());
			ext.setController(temp.getController());
			ext.setAction(temp.getAction());
			ext.setCode(temp.getCode());
			ext.setStatus(temp.getStatus());
			
			results.add(ext);
		}
		
		return results;
	}
	
	/**
	 * 查询菜单
	 * @param baseMenuExt
	 * @return
	 */
	public BaseMenuExt query(BaseMenuExt baseMenuExt){
		BaseMenu where = new BaseMenu();
		where.setId(baseMenuExt.getId());
		where.setController(baseMenuExt.getController());
		where.setAction(baseMenuExt.getAction());
		
		BaseMenu menu = baseMenuService.query(where);
		if(menu == null){
			return null;
		}
		BaseMenuExt ext = new BaseMenuExt();
		ext.setId(menu.getId());
		ext.setName(menu.getName());
		ext.setParentId(menu.getParentId());
		ext.setPosition(menu.getPosition());
		ext.setController(menu.getController());
		ext.setAction(menu.getAction());
		ext.setCode(menu.getCode());
		ext.setStatus(menu.getStatus());
		return ext;
	}
	
	/**
	 * 
	 */
	public List<BaseMenuExt> queryMenuOperatorList(Integer moduleID)
	{
		//取得一级菜单
		List<BaseMenuExt> menuList = queryMenuListByParentID(moduleID);
		Map<String, String> operatorMap = queryOperatorList();
		List<BaseMenuOperatorExt> menuOperatorList = queryMenuOperatorList();
		
		List<BaseMenuExt> results = new ArrayList<BaseMenuExt>();
		
		for(BaseMenuExt oneLevelMenu: menuList){
			//取得二级菜单
			List<BaseMenuExt> twoLevelMenuList = queryMenuListByParentID(oneLevelMenu.getId());
			for(BaseMenuExt twoLevelMenu: twoLevelMenuList){
				//取得关联的操作项
				List<BaseOperatorExt> operators = new ArrayList<BaseOperatorExt>();
				for(BaseMenuOperatorExt menuOperator: menuOperatorList){
					if(menuOperator.getMenuId() == twoLevelMenu.getId()){
						BaseOperatorExt operator = new BaseOperatorExt();
						operator.setId(menuOperator.getOperatorId());
						operator.setName(operatorMap.get(menuOperator.getOperatorId().toString()));
						operator.setAction(menuOperator.getAction());
						operator.setController(menuOperator.getController());
						operators.add(operator);
					}
				}
				
				twoLevelMenu.setOperator(operators);
			}
			oneLevelMenu.setSubMenu(twoLevelMenuList);
			
			results.add(oneLevelMenu);
		}
		
		return results;
	}
	
//	public BaseMenuExt queryMenu(Menu menu){
//		return menuDao.query(menu);
//	}
	
	/**
	 * 获取operator列表
	 */
	public Map<String, String> queryOperatorList(){
		List<BaseOperator> operatorList = baseOperatorService.queryList();
		
		Map<String, String> results = new HashMap<String, String>(operatorList.size());
		
		for(BaseOperator operator: operatorList){
			results.put(operator.getId().toString(), operator.getName());
		}
		
		return results;
	}
	
	/**
	 * 获取MenuOperator关联表
	 */
	public List<BaseMenuOperatorExt> queryMenuOperatorList(){
		List<BaseMenuOperator> list = baseMenuOperatorService.queryList();
		List<BaseMenuOperatorExt> results = new ArrayList<BaseMenuOperatorExt>();
		
		for(BaseMenuOperator temp: list){
			BaseMenuOperatorExt ext = new BaseMenuOperatorExt();
			
			ext.setId(temp.getId());
			ext.setMenuid(temp.getMenuId());
			ext.setOperatorId(temp.getOperatorId());
			ext.setController(temp.getController());
			ext.setAction(temp.getAction());
			
			results.add(ext);
		}
		//需要缓存
		
		return results;
	}
}