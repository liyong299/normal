/**
 * 
 */
package com.mopon.service.contant;

/**
 * 
 * <p>Description:接口返回错误代码类 </p>
 * @date 17 Apr 2015
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public enum ErrorCodeEnum {

	
	SUCCESS(001,"操作成功"),
	ERROR(400,""), //为业务抛出异常时操作的code，msg从本页取
	E_CHANNEL_CODE(9001,"渠道不存在,或未开放"),
	E_SIGN_N(9002,"缺少sign参数"),
	E_SIGN(9003,"签名错误"),
	E_METHOD(9004,"请求业务码不存在"),
	E_SYSTEM(9005,"系统错误"),
	E_AREA_NO(9006,"参数areaNo不能为空"),
	E_CINEMA_NO(9007,"参数cinemaCode不能为空"),
	E_PARAMTER(9008,"参数不能为空"),
	E_COUNT(9009,"数量错误"),
	E_PRICE(9010,"价格错误"),
	E_LOCKSEATS(9011,"每次最多允许锁定4个座位"),
	E_CHANCEL_ORDER(9012,"平台不存在渠道[%s]订单[%s]"),
	E_FILMNO_NO(9013,"参数filmNo不能为空"),
	E_ORDER_CODE(9014,"参数orderCode不能为空"),
	E_SUBMIT_ORDER(9015,"参数mobile,amount,orderNo不能为空"),
	E_DY_SUBMIT_ORDER(9016,"参数orderNo,mobile,amount,payChannelNo不能为空"),
	E_DYCREATECOMMORDER(9017,"参数price,count,mobile不能为空"),
	E_DYLOCKSEATS(9018,"参数channelShowCode,seatCodes,mobile不能为空"),
	E_MOBILE(9019,"参数mobile不能为空"),
	E_CREATECOMMORDER(9020,"参数mobile,ticketNo,price,count,channelOrderCode不能为空"),
	E_LOCKSEATS_PARAM(9021,"参数mobile,channelShowCode,seatCodes,channelOrderCode不能为空"),
	E_CHANNELSHOWCODE(9022,"参数channelShowCode不能为空"),
	E_CINEMANO_NO(9023,"参数cinemaNo不能为空"),
	E_ORDERCODE_NO(9024,"订单不存在"),
	E_TICKET(9025,"商品不存在"),
	E_LOCKSEATS_STOPERROR(9026,"已过停售时间,不能购票"),
	E_LOCKSEATS_QUERYSHOWERROR(9027,"系统繁忙,请稍候重试!"),
	E_QUERYSEATS(9028,"参数cinemaCode,hallCode不能为空"),
	
	//支付订单、确认校验
	E_ORDER_PAY(7001,"订单[%s]已支付成功"),
	E_ORDER_PHONE(7002,"手机号码输入错误"),
	E_ORDER_AMOUNT(7003,"金额错误为整数（分）"),
	E_ORDER_CHANNEL(7004,"渠道[%s]下已经存在订单[%s]"),
	E_ORDER_CHANNEL_NO(7005,"订单[%s]不存在"),
	E_ORDER_AMOUNT_N(7006,"订单金额与支付金额不一致"),
	E_ORDER_SUBMIT(7007,"订单不能重复确认"),
	E_ORDER_SHOW_DOWN(7008,"场次不存在或已经下架"),
	E_ORDER_SEAT_N(7009,"座位已卖完，请选择其它场次"),
	E_ORDER_SEAT(7010,"座位状态不正确，请选择其它座位"),
	E_ORDER_GOODS(7011,"商品[%s]不存在或已经下架"),
	E_ORDER_GOODS_CHN(7012,"商品[%s]不允许在渠道销售"),
	E_ORDER_PRICE(7013,"价格与商品配置价格不一致"),
	E_ORDER_STRATEG(7014,"未配置策略价格，此商品不能销售"),
	E_ORDER_SEAT_STA(7015,"影院选座票状态不正确"),//后台影院选座票停售
	E_ORDER_CINEMA_N(7016,"影院不存在或未上架"),
	E_ORDER_COMM_STA(7017,"影院通兑票状态不正确"),
	E_ORDER_TICK_CHAN(7018,"渠道下商品未开放"),
	E_ORDER_CHAN_SALE(7019,"渠道销售状态不正确"),
	E_ORDER_UNPAID(7022,"订单[%s]未支付"),
	E_ORDER_CREATEFAILURE(7023,"订单[%s]生成支付订单失败"),
	E_ORDER_UPDATEPHONE(7024,"订单[%s]修改发送短信手机号失败"),
	E_ORDER_STATUS(7026,"订单[%s]状态不正确"),
	E_ORDER_PAY_NONE(7029,"支付订单号不存在"),
	E_ORDERDETAIL_NO(7030,"订单明细[%s]不存在"),
	E_ORDER_COMMGOODS_NONE(7031,"通兑票号不存在"),
	E_ORDER_TICKET_ING(7032,"出票中"),
	E_ORDER_CREATE(7037,"创建订单失败");

	private ErrorCodeEnum(int errorCode, String codeName) {
		this.errorCode = errorCode;
		this.codeName = codeName;
	}

	private int errorCode;

	private String codeName;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
}
