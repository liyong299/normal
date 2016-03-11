package com.mopon.entity.console.base;

import com.mopon.entity.BaseAccount;

public class BaseAccountExt extends BaseAccount{
	//角色名称
	private String roleName;
	
	//上次登录时间（显示）
	private String lastTimeShow;
	
	//创建时间（显示）
	private String createTimeShow;
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getLastTimeShow() {
		return lastTimeShow;
	}

	public void setLastTimeShow(String lastTimeShow) {
		this.lastTimeShow = lastTimeShow;
	}
	
	public String getCreateTimeShow() {
		return createTimeShow;
	}

	public void setCreateTimeShow(String createTimeShow) {
		this.createTimeShow = createTimeShow;
	}
}