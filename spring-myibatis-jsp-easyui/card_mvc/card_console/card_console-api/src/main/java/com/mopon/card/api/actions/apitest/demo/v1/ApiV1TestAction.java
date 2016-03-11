package com.mopon.card.api.actions.apitest.demo.v1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mopon.card.api.facade.v1.UserQuery;

/**
 * 标准接口测试。
 * 没有做权限控制
 */
@Controller
@RequestMapping("/apitest/demo/v1")
public class ApiV1TestAction {
	/**
	 * 查看菜单。
	 */
	@RequestMapping("menu")
	public void menu() {
		// nothing to do
	}
	/**
	 * 查询用户
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("queryUser")
	public void queryUser(Model model) {
		model.addAttribute("query", new UserQuery());
	}
}