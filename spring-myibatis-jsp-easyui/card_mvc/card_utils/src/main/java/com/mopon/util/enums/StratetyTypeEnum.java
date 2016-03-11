package com.mopon.util.enums;

/**
 * 销售策略类型枚举
 * @author mengxiangrui
 *
 */

public enum StratetyTypeEnum {
	Global(0,"全局销售策略"),
	Distributor(1,"分销商销售策略");
	
	private StratetyTypeEnum(int val,String name){
		this.val = val;
		this.name = name;
	}
	
	private int val;

	private String name;

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
