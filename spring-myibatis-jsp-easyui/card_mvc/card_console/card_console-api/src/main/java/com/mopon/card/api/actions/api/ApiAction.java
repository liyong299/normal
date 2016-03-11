package com.mopon.card.api.actions.api;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mopon.card.api.ApiException;
import com.mopon.card.api.ApiReply;
import com.mopon.card.api.ApiReplyCode;
import com.mopon.util.StringUtil;

/**
 * API Action基类。
 */
public abstract class ApiAction 
{
	protected final Logger log = LoggerFactory.getLogger(getClass());
	protected ObjectMapper mapper = new ObjectMapper();
	

	/**
	 * 异常拦截处理。
	 * 
	 * @param request
	 *            HTTP请求
	 * @param ex
	 *            异常
	 * @return 返回异常视图。
	 */
	@ExceptionHandler(value = { ApiException.class, BindException.class,
			Exception.class })
	protected ApiReply checkedException(HttpServletRequest request, Exception ex) {
		ApiReply reply = new ApiReply(ApiReplyCode.FAILED);
		if (ex instanceof ApiException) {
			reply = new ApiReply(((ApiException) ex).getReplyCode());
		}
		if (ex instanceof ConnectException) {
			reply = new ApiReply(ApiReplyCode.CONNECT_FAILED);
		}

		if (ex instanceof BindException) {
			List<String> errorMsgs = new ArrayList<>();
			List<ObjectError> errors = ((BindException) ex).getBindingResult()
					.getAllErrors();
			for (ObjectError error : errors) {
				errorMsgs.add(error.getDefaultMessage());
			}
			reply = new ApiReply(ApiReplyCode.PARAMS_VALIDATE_FAILED);
			reply.setMsg(StringUtil.join(errorMsgs, "|"));
		}
		logError(request, reply, ex);
		return reply;
	}

	/**
	 * 记录接口异常日志。
	 * 
	 * @param request
	 *            HTTP请求
	 * @param reply
	 *            响应对象
	 * @param ex
	 *            异常
	 */
	private void logError(HttpServletRequest request, ApiReply reply,
			Exception ex) {
		String appCode = request.getParameter("appCode");
		String params;
		String replyMsg;
		try {
			params = mapper.writeValueAsString(request.getParameterMap());
			replyMsg = mapper.writeValueAsString(reply);
		} catch (JsonProcessingException e) {
			params = e.getMessage();
			replyMsg = e.getMessage();
		}
		String msg = String.format("[%s][请求地址：%s][调用失败]%n[请求参数：%s]%n[响应消息：%s]",
				appCode, request.getRequestURI(), params, replyMsg);
		if (ex instanceof ApiException) {
			log.error(msg);
		} else {
			log.error(msg, ex);
		}
	}

	protected static String getIntefaceName(HttpServletRequest request) {
		String name = "";
		String uri = request.getRequestURI();
		name = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		return name;
	}

	@SuppressWarnings("rawtypes")
	protected static String getappCode(HttpServletRequest request) {
		String appCode = "";
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if (paramName.equals("appCode")) {
				String[] paramValues = request.getParameterValues(paramName);
				if (paramValues.length == 1) {
					String paramValue = paramValues[0];
					if (paramValue.length() != 0) {
						appCode = paramValue;
					}
				}
			}
		}
		return appCode;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static String showParams(HttpServletRequest request) {
		String strParam = "[";
		Map map = new HashMap();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					map.put(paramName, paramValue);
				}
			}
		}
		Set<Map.Entry<String, String>> set = map.entrySet();
		for (Map.Entry entry : set) {
			strParam += entry.getKey() + ":" + entry.getValue() + "|";
		}
		strParam = strParam.substring(0, strParam.length() - 1);
		strParam = strParam + "]";
		return strParam;
	}
}