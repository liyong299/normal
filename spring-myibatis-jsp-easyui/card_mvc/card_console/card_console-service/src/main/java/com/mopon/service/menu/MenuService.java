package com.mopon.service.menu;

import java.util.List;
import java.util.Map;

import com.mopon.entity.console.base.BaseMenuExt;
import com.mopon.entity.console.base.BaseMenuOperatorExt;

public interface MenuService {
	/**
	 * 根据parentID获取菜单列表
	 * @param parentID
	 * @return
	 */
	public List<BaseMenuExt> queryMenuListByParentID(Integer parentID);
	
	/**
	 * 查询菜单列表
	 * @param menu
	 * @return
	 */
	public List<BaseMenuExt> queryMenuList(BaseMenuExt baseMenuExt);
	
	/**
	 * 查询菜单
	 * @param baseMenuExt
	 * @return
	 */
	public BaseMenuExt query(BaseMenuExt baseMenuExt);
	
	/**
	 * 根据模块ID查询菜单及操作项列表
	 * @param moduleID
	 * @return
	 */
	public List<BaseMenuExt> queryMenuOperatorList(Integer moduleID);
//	public BaseMenuExt queryMenu(BaseMenuExt baseMenuExt);
	
	/**
	 * 获取所有操作项
	 * @return
	 */
	public Map<String, String> queryOperatorList();
	
	/**
	 * 获取菜单操作项关联列表
	 * @return
	 */
	public List<BaseMenuOperatorExt> queryMenuOperatorList();
}