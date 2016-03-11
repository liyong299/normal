/**
 * 项   目  名：SCEC
 * 包          名：cn.mopon.cec.api.log
 * 文   件  名：LogService.java
 * 版本信息：SCEC_Branches
 * 日          期：2015年9月9日-下午2:02:11
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.mopon.card.api.log;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mopon.card.api.ApiReply;
import com.mopon.entity.console.base.BaseAccountExt;
import com.mopon.service.account.AccountService;

/**
 * @author LRL
 * @version [SCEC_Branches, 2015年9月9日]
 * @备注：
 */
@Service
public class LogService {

	private Logger log = LoggerFactory.getLogger(getClass());
	private ObjectMapper mapper = new ObjectMapper();

	@Resource
	private AccountService accountService;

	@Async
	public void logReply(String appCode, Map paraM, String URI,
			ApiReply reply) {
		BaseAccountExt user = accountService.query(appCode);
		String params;
		try {
			params = mapper.writeValueAsString(paraM);
		} catch (JsonProcessingException e) {
			params = e.getMessage();
		}
		String msg = String.format("[%s][请求地址：%s][调用成功]%n[请求参数：%s]%n[响应消息：%s]",
				appCode, URI, params, getReplyLogString(user, reply));
		log.info(msg);
	}

	/**
	 * 获取响应消息的日志记录内容。根据渠道设置中的日志长度进行截取。
	 * 
	 * @param channel
	 *            渠道
	 * @param reply
	 *            响应消息
	 * @return 返回响应消息的日志记录内容。
	 */
	private String getReplyLogString(BaseAccountExt user, ApiReply reply) {
		try {
			String replyString = mapper.writeValueAsString(reply);
			
			return replyString;
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}
	}
}
