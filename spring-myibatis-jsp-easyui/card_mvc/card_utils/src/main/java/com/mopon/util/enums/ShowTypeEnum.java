package com.mopon.util.enums;

/**
 * 放映类型枚举
 * @author mopon
 *
 */
public enum ShowTypeEnum {
	T2D(0),
	T3D(1),
	TIMAX2D(2),
	TIMAX3D(3),
	TDMAX2D(4),
	TDMAX3D(5),
	TSPE(99);
	
	private ShowTypeEnum(int code)
	{
		this.code = code;
	}
	
	private final int code;
	
	public int getCode() {
		return code;
	}
	
	public static String getShowType(int code)
	{
		String showTypeName = "";
		switch(code)
		{
			case  0:
				showTypeName = "2D";
				break;
			case  1:
				showTypeName = "3D";
				break;
			case  2:
				showTypeName = "IMAX2D";
				break;				
			case  3:
				showTypeName = "IMAX3D";
				break;
			case  4:
				showTypeName = "巨幕2D";
				break;
			case  5:
				showTypeName = "巨幕3D";
				break;
				}
		return showTypeName;
	}
}
