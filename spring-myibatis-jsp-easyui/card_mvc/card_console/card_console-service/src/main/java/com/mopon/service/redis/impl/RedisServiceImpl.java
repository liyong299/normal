package com.mopon.service.redis.impl;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.mopon.service.redis.IRedisService;
import com.mopon.util.DateUtils;
@Service
public class RedisServiceImpl implements IRedisService {

	@Override
	public String queryActiveDayCount(String host,String activeid) {
		String result="";
		String curDate = DateUtils.format(new java.util.Date(), "yyyy-MM-dd");
		Jedis jedis = new Jedis(host);
		String key = String.format("ActiveDayCount%s%s", curDate,activeid);
		result = jedis.get(key);
		if("nil".equals(result)){
			result="0";
		}
		jedis.close();
		return result;
	}

	@Override
	public String queryActiveDayMoney(String host, String activeid) {
		String result="";
		String curDate = DateUtils.format(new java.util.Date(), "yyyy-MM-dd");
		Jedis jedis = new Jedis(host);
		String key = String.format("ActiveDayMoney%s%s", curDate,activeid);
		result = jedis.get(key);
		if("nil".equals(result)){
			result="0";
		}
		jedis.close();
		return result;
	}

}
