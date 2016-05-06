package com.dup.server.actions.platform;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dup.server.actions.platform.bean.ApiReply;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 对外接口请求拦截器。
 * 
 * 方法迁移到 TicketApiInterceptor里面
 * 
 */
public class ApiInterceptor extends HandlerInterceptorAdapter {

	private ObjectMapper mapper = new ObjectMapper();
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String channelCode = request.getParameter("channelCode");
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ModelMap modelMap = modelAndView.getModelMap();
		com.dup.server.actions.platform.bean.ApiReply reply = getReply(modelMap);
		logReply(request, reply);
		modelMap.clear();
		modelMap.addAttribute(reply);
	}

	/**
	 * 获取响应对象。
	 * 
	 * @param modelMap
	 *            ModelMap
	 * @return 返回响应对象。
	 */
	private ApiReply getReply(ModelMap modelMap) {
		for (Object model : modelMap.values()) {
			if (model instanceof ApiReply) {
				return (ApiReply) model;
			}
		}
		return null;
	}

	/**
	 * 记录接口调用日志。
	 * 
	 * @param request
	 *            HTTP请求
	 * @param reply
	 *            响应对象
	 */
	protected void logReply(HttpServletRequest request, ApiReply reply) {
		String channelCode = request.getParameter("channelCode");
		String params;
		try {
			params = mapper.writeValueAsString(request.getParameterMap());
		} catch (Exception e) {
			params = e.getMessage();
		}
		String msg = String.format("[%s][请求地址：%s][调用成功]%n[请求参数：%s]%n[响应消息：%s]",
				channelCode, request.getRequestURI(), params,
				getReplyLogString(reply));
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
	private String getReplyLogString(ApiReply reply) {
		try {
			String replyString = mapper.writeValueAsString(reply);
			
			return replyString;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}