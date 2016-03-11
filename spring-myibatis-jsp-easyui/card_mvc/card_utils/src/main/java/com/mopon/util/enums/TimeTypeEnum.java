package com.mopon.util.enums;

/**
 * 闲忙时枚举
 * @author mopon
 *
 */
public enum TimeTypeEnum {
	COMMON(0),
	BUSY(1),
	IDLE(2);
	
	private TimeTypeEnum(int code)
	{
		this.code = code;
	}
	
	private final int code;
	
	public int getCode() {
		return code;
	}
	
	public static String getPriceName(int name)
	{
		String priceName = "";
		switch(name)
		{
			case 0:
				priceName = "";
				break;
			case  1:
				priceName = "忙时";
				break;
			case  2:
				priceName = "闲时";
				break;
				}
		return priceName;
	}
}
