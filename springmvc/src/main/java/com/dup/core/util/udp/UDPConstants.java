/**
 * 项   目  名：SCEC
 * 包          名：cn.mopon.cec.api.monitor.constant
 * 文   件  名：UDPConstants.java
 * 版本信息：SCEC_Branches
 * 日          期：2015年8月28日-上午10:23:11
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.core.util.udp;

/**
 * @author LRL
 * @version [SCEC_Branches, 2015年8月28日]
 * @备注：
 */
public class UDPConstants {

	// public static final String COMMON_SEPARATE = "_";
	public static String SYS_CODE_TYPE = "scec";// 系统操作码

	/***************** 统一错误码 *******************************/
	public static final String SUCCESS = "001";// 成功
	public static final String ERROR = "999";// 失败

	/***************** 操作类型 *********************************/
	public static final String QUERY_CINEMAS = "queryCinemas"; // 查询影院列表
	public static final String QUERY_CINEMA = "queryCinema"; // 查询影院
	public static final String QUERY_HALLS = "queryHalls"; // 查询影厅列表
	public static final String QUERY_SEATS = "querySeats"; // 查询影厅座位列表
	public static final String QUERY_FILMS = "queryFilms"; // 查询影片列表
	public static final String QUERY_FILM = "queryFilm"; // 查询影片
	public static final String QUERY_SHOWS = "queryShows"; // 查询场次列表
	public static final String QUERY_REPLACE_SHOW = "queryReplaceShow"; // 查询替代场次
	public static final String QUERY_SHOW_SEATS = "queryShowSeats"; // 查询场次座位列表
	public static final String LOCK_SEATS = "lockSeats"; // 锁座总耗时
	public static final String REQUEST_GROUND = "requestGround"; // 请求地面耗时
	public static final String ALARM_CINEMA_DATA = "alarmCinemaData"; // 请求地面耗时超过1秒的数据
	public static final String RELEASE_SEATS = "releaseSeats"; // 释放座位
	public static final String SUBMIT_ORDER = "submitOrder"; // 确认订单
	public static final String QUERY_ORDER = "queryOrder"; // 查询订单
	public static final String REVOKE_TICKET = "revokeTicket"; // 退票
	public static final String MARK_TICKET_REVOKED = "markTicketRevoked"; // 标记退票
	public static final String QUERY_PRINT_BY_VOUCHER = "queryPrintByVoucher"; // 根据凭证编码进行凭证验证
	public static final String QUERY_PRINT_BY_VERIFYCODE = "queryPrintByVerifyCode"; // 根据取票号和取票验证码进行凭证验证
	public static final String CONFIRM_PRINT = "confirmPrint"; // 确认打票
	public static final String CHANGE_VOUCHER = "changeVoucher"; // 更换凭证
	public static final String RESET_VOUCHER = "resetVoucher"; // 重置凭证
	public static final String QUERY_CHANNELS = "queryChannels"; // 查询渠道列表
	public static final String QUERY_SNACKS = "querySnacks"; // 查询卖品列表
	public static final String QUERY_BENEFITCARD_TYPES = "queryBenefitCardTypes"; // 获取卡类
	public static final String OPEN_BENEFITCARD = "openBenefitCard"; // 开卡
	public static final String RECHARGE_BENEFITCARD = "rechargeBenefitCard"; // 续费
	public static final String QUERY_RECHARGE_BENEFITCARD_ORDER = "queryRechargeBenefitCardOrder"; // 查询续费订单
	public static final String QUERY_OPEN_BENEFITCARD_ORDER = "queryOpenBenefitCardOrder"; // 查询开卡订单
	public static final String QUERY_BENEFITCARD = "queryBenefitCard"; // 查询卡信息
	public static final String CHANGE_MOBILE = "changeMobile"; // 更改手机号码
	public static final String SUBMIT_SNACK = "submitSnack"; // 确认卖品
	public static final String REVOKE_SNACK = "revokeSnack"; // 退订卖品
	public static final String MARK_SNACK_REVOKED = "markSnackRevoked"; // 标记退订卖品
	public static final String QUERY_SNACK_ORDER = "querySnackOrder"; // 查询卖品订单

	// 会员卡接口
	public static final String CARD_VERIFY = "cardVerify"; // 验证会员卡
	public static final String QUERY_CARD_INFO_BY_CODE = "queryCardInfoByCode"; // 获取会员卡信息
	public static final String QUERY_CARD_INFO_BY_CHIP = "queryCardInfoByChip"; // 获取会员卡信息接口(芯片号)
	public static final String CARD_RECHARGE_BY_CODE = "cardRechargeByCode"; // 会员卡充值
	public static final String CARD_RECHARGE_BY_CHIP = "cardRechargeByChip"; // 会员卡充值接口(芯片号)
	public static final String CARD_DISCOUNT = "cardDiscount"; // 会员卡折扣查询
	public static final String CARD_PAY = "cardPay"; // 会员卡消费扣款


}