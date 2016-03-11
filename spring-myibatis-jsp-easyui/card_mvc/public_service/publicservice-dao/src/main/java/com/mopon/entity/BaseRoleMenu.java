package com.mopon.entity;
/**
 * 角色菜单
 * @author 王贤志
 *
 */
public class BaseRoleMenu{
	//操作ID
	private Integer id;
	
	//系统角色ID
	private Integer roleId;
	
	//菜单ID
	private Integer menuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}