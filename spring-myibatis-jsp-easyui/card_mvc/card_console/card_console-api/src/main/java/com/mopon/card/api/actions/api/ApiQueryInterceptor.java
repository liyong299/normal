package com.mopon.card.api.actions.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mopon.card.api.ApiException;
import com.mopon.card.api.ApiReply;
import com.mopon.card.api.ApiReplyCode;
import com.mopon.card.api.actions.api.demo.ApiMethodAuth;
import com.mopon.entity.console.base.BaseAccountExt;
import com.mopon.service.account.impl.AccountServiceImpl;
import com.mopon.util.MD5;
import com.mopon.util.StringUtil;

/**
 * 对外接口请求拦截器。
 * 
 * 方法迁移到 TicketApiInterceptor里面
 * 
 */
public class ApiQueryInterceptor extends HandlerInterceptorAdapter 
{
	@Autowired
	private AccountServiceImpl accountService;
	
	private ObjectMapper mapper = new ObjectMapper();
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String appCode = request.getParameter("appCode");
		appCode = appCode == null ? "admin" : appCode;
		request.getParameterMap();
		BaseAccountExt user = accountService.queryByName(appCode);
		
		if (user == null)
		{
			if (log.isWarnEnabled()) log.warn("当前接口用户不存在，请确认：" + appCode);
			return false;
		}
		
		checkTicketApiMethodAuth((HandlerMethod) handler, user);
		
		// 需要判断是否校验签名
		if (user.getStatus() == 1) {
			checkSign(request, user);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView == null)
		{
			return ;
		}
		ModelMap modelMap = modelAndView.getModelMap();
		ApiReply reply = getReply(modelMap);
		logReply(request, reply);
		modelMap.clear();
		modelMap.addAttribute(reply);
		modelMap = null;
	}
	
	/**
	 * 检查渠道是否具有访问接口的权限。
	 * 
	 * @param method
	 *            接口方法
	 * @param channel
	 *            渠道
	 */
	private void checkTicketApiMethodAuth(HandlerMethod method, BaseAccountExt user) 
	{
		ApiMethodAuth auth = method
				.getMethodAnnotation(ApiMethodAuth.class);
		
		// 权限判断，此处暂时未实现，仅以是否配置权限为控制。 TODO
		if (auth.value() == null) {
			ApiException.thrown(ApiReplyCode.ACCESS_DENIDE);
		}
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
		String appCode = request.getParameter("appCode");
		String params;
		try {
			params = mapper.writeValueAsString(request.getParameterMap());
		} catch (JsonProcessingException e) {
			params = e.getMessage();
		}
		String msg = String.format("[%s][请求地址：%s][调用成功]%n[请求参数：%s]%n[响应消息：%s]",
				appCode, request.getRequestURI(), params,
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
		} catch (JsonProcessingException e) {
			return e.getMessage();
		}
	}

	/**
	 * 验证签名。
	 * @throws UnsupportedEncodingException
	 *             不支持UTF-8编码时抛出异常
	 */
	private void checkSign(HttpServletRequest request, BaseAccountExt user)
			throws UnsupportedEncodingException {
		String origSign = request.getParameter("sign");
		String paramSignStr = genParamSignStr(request);
		String secKey = user.getKey();
		String targetSign = MD5.md5(secKey + paramSignStr + secKey);
		if (origSign == null) return;
		// 前面判断，需要在测试的时候关闭。 当前缺少开关。  TODO
		if (!origSign.equals(targetSign) && false) {
			ApiException.thrown(ApiReplyCode.SIGN_VERIFY_FAILED);
		}
	}

	/**
	 * 生成请求参数拼接的待签名字符串。
	 * 
	 * @param request
	 *            请求对象
	 * @return 返回请求参数拼接的待签名字符串。
	 * @throws UnsupportedEncodingException
	 *             不支持UTF-8编码时抛出异常
	 */
	private String genParamSignStr(HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String, String> paramsMap = new TreeMap<>();
		Enumeration<String> paramNamesIterator = request.getParameterNames();
		while (paramNamesIterator.hasMoreElements()) {
			String paramName = paramNamesIterator.nextElement();
			paramsMap.put(paramName, request.getParameter(paramName));
		}
		paramsMap.remove("sign");
		List<String> paramPairs = new ArrayList<String>();
		for (Entry<String, String> entry : paramsMap.entrySet()) {
			paramPairs.add(entry.getKey() + "=" + entry.getValue());
		}
		String paramSignStr = StringUtil.join(paramPairs, "&");
		return URLEncoder.encode(paramSignStr, "UTF-8");
	}
}