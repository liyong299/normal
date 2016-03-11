package com.mopon.entity;

/**
 * 操作项
 * @author 王贤志
 *
 */
public class BaseOperator{
	//操作ID
	private Integer id;

	//操作名称
	private String name;
	
	//控制器
	private String controller;

	//动作
	private String action;

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

}