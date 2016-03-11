package com.mopon.util;

/**
 * 
 * <p>Description: 活动常量类</p>
 * @date 2015年5月8日
 * @author 孙磊
 * @version 1.0.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2015</p>
 */
public class ActConstants {

	/** 活动状态：删除 */
	public static final int ACT_STATUS_DEL = 0;

	/** 活动状态：待信息审核 */
	public static final int ACT_STATUS_UNCHECK = 1;

	/** 活动状态：信息审核拒绝 */
	public static final int ACT_STATUS_CHECK_FAIL = -1;

	/** 活动状态：待财务审核 */
	public static final int ACT_STATUS_AUDIT = 2;

	/** 活动状态：财务审核拒绝 */
	public static final int ACT_STATUS_AUDIT_FAIL = -2;

	/** 活动状态：上架 */
	public static final int ACT_STATUS_UP = 3;

	/** 活动状态：下架 */
	public static final int ACT_STATUS_DOWN = 4;
	
	/** 商品类型：选座票 */
	public static final int GOODS_TYPE_TICKET = 0;
	
	/** 商品类型：通兑票 */
	public static final int GOODS_TYPE_VOUCHER = 1;
}
