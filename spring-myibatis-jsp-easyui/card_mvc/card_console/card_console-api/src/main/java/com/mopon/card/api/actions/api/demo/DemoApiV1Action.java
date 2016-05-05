package com.mopon.card.api.actions.api.demo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mopon.card.api.actions.api.APIAuth;
import com.mopon.card.api.actions.api.ApiAction;
import com.mopon.card.api.bean.HallVo;
import com.mopon.card.api.facade.AccountFacade;
import com.mopon.card.api.facade.v1.UserQuery;
import com.mopon.card.api.facade.v1.UserReply;

/**
 * 接出API。
 * 注解ApiMethodAuth指定方法的编码，通过拦截器判断用户是否具备调用该方法的权限
 */
@Controller
@RequestMapping("/api/demo/v1")
public class DemoApiV1Action extends ApiAction {
	@Resource
	private AccountFacade accountFacade;

	/**
	 * 查询影院列表。
	 * 
	 * @param query
	 *            请求对象
	 * @return 返回响应对象。
	 */
	@RequestMapping("queryUser")
	@ApiMethodAuth(APIAuth.QUERY_USERBYCODE)
	public @ResponseBody UserReply queryCinemas(@Valid UserQuery query,
			HttpServletRequest req, HttpServletResponse resp) {
		StopWatch watch = new StopWatch();
		watch.start();
		UserReply result = accountFacade.queryUser(query);
		watch.stop();
		
		result.setCostTime(watch.getTotalTimeMillis());
		
		return result;
	}

	/**
	 * JS对象转为JAVA对象
	 * 
	 * @param query
	 *            请求对象
	 * @return 返回响应对象。
	 */
	@RequestMapping("jsonChange")
	@ApiMethodAuth(APIAuth.QUERY_USERBYCODE)
	public @ResponseBody UserReply jsonChange(@RequestBody HallVo hall) {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println(hall);
		UserReply result = new UserReply();
		watch.stop();
		
		result.setCostTime(watch.getTotalTimeMillis());
		
		return result;
	}

}