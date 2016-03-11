package com.mopon.entity.console.base;

import com.mopon.entity.BaseRoleMenuOperator;
/**
 * 角色菜单操作项关联
 * @author 王贤志
 *
 */
public class BaseRoleMenuOperatorExt extends BaseRoleMenuOperator{
	//创建时间（显示）
	private String createTimeShow;

	public String getCreateTimeShow() {
		return createTimeShow;
	}

	public void setCreateTimeShow(String createTimeShow) {
		this.createTimeShow = createTimeShow;
	}
}