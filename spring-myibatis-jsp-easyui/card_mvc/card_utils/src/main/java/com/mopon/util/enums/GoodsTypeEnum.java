/**
 * @Title: GoodsTypeEnum.java
 * @Package com.mopon.util.enums
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:深圳市泰久信息系统股份有限公司
 * 
 * @author Comsys-yuezt
 * @date 2015年5月19日 下午12:19:44
 * @version V1.0
 */

package com.mopon.util.enums;

/**
 * @ClassName: GoodsTypeEnum
 * @Description: TODO
 * @author Comsys-yuezt
 * @date 2015年5月19日 下午12:19:44
 *
 */

public enum GoodsTypeEnum {
	CHOOSE_SEAT(1),
	SINGLE_EXCHANGE(2),
	MULTI_EXCHANGE(3);
	
	private GoodsTypeEnum(int code)
	{
		this.code = code;
	}
	
	private final int code;
	
	public int getCode() {
		return code;
	}
	public String getGoodsTypeNameByCode(int code){
		//商品类型 1：选座票 2：单家通兑票 3：多家通兑票
		String goodsTypeName = "";
		switch (code)
		{
			case 1:
				goodsTypeName = "选坐票" ;
				break;
			case 2:
				goodsTypeName = "单价通兑票" ;
				break;
			case 3:
				goodsTypeName = "多家通兑票" ;
				break;
		}
		return goodsTypeName;
	}
}
