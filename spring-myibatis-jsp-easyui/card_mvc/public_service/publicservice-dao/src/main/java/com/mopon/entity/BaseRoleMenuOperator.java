package com.mopon.entity;
/**
 * 角色菜单操作项关联
 * @author 王贤志
 *
 */
public class BaseRoleMenuOperator{
	//id
	private Integer id;
	
	//系统角色ID
	private Integer roleId;
	
	//菜单ID
	private Integer menuId;
	
	//系统操作ID
	private Integer operatorId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id =id;
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

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
}