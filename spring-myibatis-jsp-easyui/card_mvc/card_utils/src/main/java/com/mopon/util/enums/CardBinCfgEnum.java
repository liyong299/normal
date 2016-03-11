package com.mopon.util.enums;

/**
 * cardbincfg类型枚举
 * @author mopon
 *
 */
public enum CardBinCfgEnum {
	Unknown(999),		        
	 //第三方储值卡
	 ThirdCardTicket(0),
	 //第三方点卡
	ThirdPointCard(32),
	//现金卡
	CashCard(1),
	//点卡
	PointCard(2),
	//电影兑换卡
	ExchangeCard(3),
	//卡券通兑票
	CommonTicket(4),
	//现金券
	CashTicket(5),
	//兑换券
	ExchangeTicket(6),
	//点券
	PointTicket(7),
	/// 兜有选座票
	DooyoXuanZuoPiao(51),
	/// 兜有通兑票
	DooyoExchangePiao(52),
	///兜有点卡
	DooyoPointCard (54),
	/// QQ票
	DooyoQQCinema(56),
	/// 微信票
	DooyoWEIXINCinema(57),
	///兜有单点点卡 
	DooyoOnecePointCard(60),
	/// 电影兑换卡
	DooyoExchangeCard(61),
	//批量验证虚拟类型
	DooyoBatch(62),		        
	///5.0 选座票
	SeatTicket(80),
	/// 5.0 单家通兑票
	SingleTicket( 81),
	/// 5.0 多兑1使用的单家通兑
	SingleTicketChange(82),
	/// 5.0 线上多家通兑票
	MultTicket(83);

	private CardBinCfgEnum(int code) {
		this.code = code;
	}

	private int code;

	public int getCode() {
		return code;
	}


	
}
