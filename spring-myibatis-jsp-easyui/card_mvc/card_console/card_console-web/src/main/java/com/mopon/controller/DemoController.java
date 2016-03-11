package com.mopon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mopon.entity.base.ResultList;
import com.mopon.entity.demo.*;
import com.mopon.util.ResponseWriteUtils;

@Controller
@RequestMapping(value = "/demo")
public class DemoController
{
	@RequestMapping(value = "button", method = RequestMethod.GET)
	public String button(Locale locale, Model model) {
		return "demo/button";
	}
	
	@RequestMapping(value = "datagrid", method = RequestMethod.GET)
	public String datagrid(Locale locale, Model model) {
		return "demo/datagrid";
	}
	
	@RequestMapping(value = "extensions", method = RequestMethod.GET)
	public String extensions(Locale locale, Model model) {
		return "demo/extensions";
	}
	
	@RequestMapping(value = "getlist", method = RequestMethod.POST)
	public void getlist(HttpServletRequest request, HttpServletResponse response) {
		ResultList<DemoEntity> result = new ResultList<DemoEntity>();
		List<DemoEntity> list = new ArrayList<DemoEntity>();
		
		for(int i = 0; i < 21; i ++){
			DemoEntity entity = new DemoEntity();
			
			entity.setId(i);
			entity.setName("chenyu" + i);
			entity.setAge(i);
			entity.setAddress("address" + i);
			
			list.add(entity);
		}
		
		//result.setIsSuccess(true);
		result.setRows(list);
		result.setTotal(list.size());
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) {
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(com.mopon.helpers.ui.combobox.Data.getDemoList()).toString());
	}
}