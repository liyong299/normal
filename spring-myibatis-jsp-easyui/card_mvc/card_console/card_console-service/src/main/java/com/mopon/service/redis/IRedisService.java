package com.mopon.service.redis;

public interface IRedisService {

	/***
	 * 获取活动当前时间放量
	 * @param host redis服务器地址
	 * @param activeid 活动编号
	 * @return
	 */
	public String queryActiveDayCount(String host,String activeid);

	public String queryActiveDayMoney(String host,String activeid);
}
