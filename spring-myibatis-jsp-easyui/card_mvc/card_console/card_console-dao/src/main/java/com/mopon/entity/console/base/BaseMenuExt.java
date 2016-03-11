package com.mopon.entity.console.base;

import java.util.List;

import com.mopon.entity.BaseMenu;

/**
 * 菜单扩展
 * @author 陈誉
 *
 */
public class BaseMenuExt extends BaseMenu{
	
	//子级菜单
	private List<BaseMenuExt> subMenu;
	
	//菜单包含的操作项
	private List<BaseOperatorExt> operator;

	public List<BaseMenuExt> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<BaseMenuExt> subMenu) {
		this.subMenu = subMenu;
	}
	
	public List<BaseOperatorExt> getOperator() {
		return operator;
	}

	public void setOperator(List<BaseOperatorExt> operator) {
		this.operator = operator;
	}
}