package com.mopon.card.api.actions.apitest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 运营管理菜单。
 * 没有做权限控制
 */
@Controller
@RequestMapping("/apitest")
public class MenuAction {
	{
		System.out.println("----加载当前类：" + getClass());
	}
	
	/**
	 * 查看菜单。
	 */
	public void menu2() 
	{
		System.out.println("-----22----------");
	}
	/**
	 * 查看菜单。
	 */
	@RequestMapping("menu")
	public void menu() {
		System.out.println("---------------");
	}
}