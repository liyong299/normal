package com.mopon.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mopon.controller.common.BaseController;
import com.mopon.entity.base.Result;
import com.mopon.entity.base.Tree;
import com.mopon.entity.console.base.BaseAccountExt;
import com.mopon.entity.console.base.BaseMenuExt;
import com.mopon.service.account.AccountService;
import com.mopon.service.role.RoleService;
import com.mopon.util.ResponseWriteUtils;


@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "home/login";
	}
	
	@RequestMapping(value = "verify", method = RequestMethod.POST)
	public void verify(BaseAccountExt account, HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		result.setIsSuccess(true);
		
		if(account.getName() == null || account.getName().equals("")){
			result.setIsSuccess(false);
			result.setMessage("请输入用户名");
		}
		if(account.getPassword() == null || account.getPassword().equals("")){
			result.setIsSuccess(false);
			result.setMessage("请输入密码");
		}
		
		if(result.getIsSuccess()){
			account.setStatus(1);//请用状态下的用户
			com.mopon.entity.common.ResultT<BaseAccountExt> accountResult = accountService.checkAccount(account);

			result.setIsSuccess(accountResult.getIsSuccess());
			result.setMessage(accountResult.getMessage());
			if(result.getIsSuccess()){
				
				try {
					
					request.getSession().setAttribute("userID", accountResult.getResult().getId());
					request.getSession().setAttribute("roleID", accountResult.getResult().getRoleID());
					request.getSession().setAttribute("fullName", URLEncoder.encode(accountResult.getResult().getFullName(),"utf-8"));
					
					Cookie userID = new Cookie("userID",accountResult.getResult().getId().toString());
					Cookie roleID = new Cookie("roleID",accountResult.getResult().getRoleID().toString());
					Cookie fullName = new Cookie("fullName",URLEncoder.encode(accountResult.getResult().getFullName(),"utf-8"));
					
					userID.setPath(request.getContextPath());
					roleID.setPath(request.getContextPath());
					fullName.setPath(request.getContextPath());
					
					userID.setMaxAge(-1);
					roleID.setMaxAge(-1);
					fullName.setMaxAge(-1);
					
					response.addCookie(userID);
					response.addCookie(roleID);
					response.addCookie(fullName);
					
					accountResult.getResult().setFullName(URLEncoder.encode(accountResult.getResult().getFullName(),"utf-8"));
				} catch (Exception e) {
					// TODO: handle exception
				}

				//修改登录次数，登录时间
				accountService.updateLastTime(accountResult.getResult().getId());
			}
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "getmenulist", method = RequestMethod.POST)
	public void getMenuList(Integer moduleid, HttpServletRequest request, HttpServletResponse response) {
		List<BaseMenuExt> menuList = roleService.queryRoleAuthorityList(this.getRoleID(), moduleid);
		List<Tree> results = new ArrayList<Tree>();
		
		for(BaseMenuExt firstMenu: menuList){
			Tree firstTree = new Tree();
			firstTree.setId(firstMenu.getId());
			firstTree.setText(firstMenu.getName());
			
			List<Tree> secondTreeList = new ArrayList<Tree>();
			for(BaseMenuExt secondMenu: firstMenu.getSubMenu()){
				Tree secondTree = new Tree();
				secondTree.setId(secondMenu.getId());
				secondTree.setText(secondMenu.getName());
				secondTree.setController(secondMenu.getController());
				secondTree.setAction(secondMenu.getAction());
				
				secondTreeList.add(secondTree);
			}
			
			firstTree.setChildren(secondTreeList);
			
			results.add(firstTree);
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(results).toString());
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv= new ModelAndView();
		if(this.getFullName() != "" && this.getRoleID() != -1){
			List<BaseMenuExt> moduleList = roleService.queryRoleModule(this.getRoleID());
			mv.addObject("moduleList", moduleList);
			try {
				mv.addObject("userID", this.getUserID());
				mv.addObject("fullName", this.getFullName());
			} catch (Exception e) {
				mv.addObject("fullName","");
			}
			
			mv.setViewName("home/index");
		}else{
			mv.setViewName("home/login");
		}		
		
		return mv;
	}
}