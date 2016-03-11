package com.mopon.entity.console.base;

import com.mopon.entity.BaseOperator;
/**
 * 操作项
 * @author 陈誉
 *
 */
public class BaseOperatorExt extends BaseOperator{
	/**
	 * 是否具有操作项权限
	 */
	private Boolean haveAuthority = false;

	/**
	 * 是否具有操作项权限
	 */
	public Boolean getHaveAuthority() {
		return haveAuthority;
	}

	/**
	 * 是否具有操作项权限
	 */
	public void setHaveAuthority(Boolean haveAuthority) {
		this.haveAuthority = haveAuthority;
	}
}