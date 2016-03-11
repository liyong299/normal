package com.mopon.util.enums;

public enum LogOperationEnum {
	QUERY(1,"查询"),
	ADD(2,"添加"),
	EDIT(3,"编辑"),
	DELETE(4,"删除"),
	EXPORT(9,"导出");
	
	private LogOperationEnum(int val,String name) {
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
