package com.mopon.entity;

/**
 * 菜单
 * @author 王贤志
 *
 */
public class BaseMenu{
	//菜单ID
	private Integer id;

	//菜单名称
	private String name;

	//菜单父ID
	private Integer parentId;

	//排序位置
	private Integer position;
	
	//菜单控制器
	private String controller;

	//菜单动作
	private String action;

	//菜单编码
	private String code;

	//菜单状态
	private Integer status;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
}