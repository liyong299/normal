package com.mopon.entity.console.base;

import com.mopon.entity.BaseRole;
/**
 * 角色
 * @author 陈誉
 *
 */
public class BaseRoleExt extends BaseRole{
	private String createTimeShow;

	public String getCreateTimeShow() {
		return createTimeShow;
	}

	public void setCreateTimeShow(String createTimeShow) {
		this.createTimeShow = createTimeShow;
	}
}