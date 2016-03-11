package com.mopon.entity;
/**
 * 菜单操作项
 * @author 王贤志
 *
 */
public class BaseMenuOperator{
	//ID
	private Integer id;
	
	//菜单ID
	private Integer menuId;
	
	//操作项ID
	private Integer operatorId;
	
	//权限控制器
	private String controller;
	
	//权限动作
	private String action;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuid(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}