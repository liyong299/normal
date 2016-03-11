package com.mopon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.mopon.aspect.AuthorizeMenu;
import com.mopon.controller.common.BaseController;
import com.mopon.entity.BaseAccount;
import com.mopon.entity.console.base.BaseAccountExt;
import com.mopon.entity.base.ResultList;
import com.mopon.entity.base.Result;
import com.mopon.service.account.AccountService;
import com.mopon.service.role.RoleService;
import com.mopon.util.Page;
import com.mopon.util.ResponseWriteUtils;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController<BaseAccountExt>
{
	@Autowired
	private  HttpServletRequest request;
	
	@Autowired
	private AccountService accountService;
	
	//@AuthorizeMenu
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "account/index";
	}
	
	//@AuthorizeMenu
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Locale locale, Model model) {
		return "account/add";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(Integer id) {
		BaseAccountExt account = new BaseAccountExt();
		account.setId(id);
		
		account = accountService.query(account);
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("id", account.getId());
		mv.addObject("name", account.getName());
		mv.addObject("fullName", account.getFullName());
		mv.addObject("roleID", account.getRoleID());
		mv.setViewName("account/edit");
		
		return mv;
	}
	
	//@Authorize
	@RequestMapping(value = "getlist", method = RequestMethod.POST)
	public void getlist(BaseAccount baseAccount,Page<BaseAccountExt> page, HttpServletRequest request, HttpServletResponse response) {
		ResultList<BaseAccountExt> result = new ResultList<BaseAccountExt>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", baseAccount.getName());
		param.put("fullName", baseAccount.getFullName());
		param.put("roleID", baseAccount.getRoleID());
		param.put("status", baseAccount.getStatus());
		
		page.setParams(param);
		List<BaseAccountExt> list = accountService.queryList(page);
		
		result.setRows(list);
		result.setTotal(page.getTotalRecord());

		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(BaseAccountExt baseAccountExt, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		result.setIsSuccess(accountService.delete(baseAccountExt.getId()));
		if(result.getIsSuccess()){
			result.setMessage("成功");
		}
		else{
			result.setMessage("失败");
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "enable", method = RequestMethod.POST)
	public void enable(BaseAccountExt baseAccountExt, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		result.setIsSuccess(accountService.enable(baseAccountExt.getId()));
		if(result.getIsSuccess()){
			result.setMessage("成功");
		}
		else{
			result.setMessage("失败");
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "disable", method = RequestMethod.POST)
	public void disable(BaseAccountExt baseAccountExt, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		result.setIsSuccess(accountService.disable(baseAccountExt.getId()));
		if(result.getIsSuccess()){
			result.setMessage("成功");
		}
		else{
			result.setMessage("失败");
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add(BaseAccountExt baseAccountExt, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		
		if(baseAccountExt.getName() == null || baseAccountExt.getName() == ""){
			result.setMessage("登录名不能为空"); 
		}
		else if(baseAccountExt.getFullName() == null || baseAccountExt.getFullName() == ""){
			result.setMessage("用户名不能为空"); 
		}
		else if(baseAccountExt.getPassword() == null || baseAccountExt.getPassword() == ""){
			result.setMessage("密码不能为空"); 
		}
		else{
			com.mopon.entity.common.Result daoresult = accountService.add(baseAccountExt);
			result.setIsSuccess(daoresult.getIsSuccess());
			if(daoresult.getIsSuccess()){
				result.setMessage("成功");
			}
			else{
				result.setMessage(daoresult.getMessage());
			}
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public void edit(BaseAccountExt baseAccountExt, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		
		if(baseAccountExt.getId() == null || baseAccountExt.getId() == 0){
			result.setMessage("ID不能为空"); 
		}
		else if(baseAccountExt.getFullName() == null || baseAccountExt.getFullName() == ""){
			result.setMessage("用户名不能为空"); 
		}
		else{
			com.mopon.entity.common.Result daoresult = accountService.update(baseAccountExt);
			result.setIsSuccess(daoresult.getIsSuccess());
			if(daoresult.getIsSuccess()){
				result.setMessage("成功");
			}
			else{
				result.setMessage(daoresult.getMessage());
			}
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	
	@RequestMapping(value = "ChangePassword", method = RequestMethod.POST)
	public void ChangePassword(String userID,String OldPassword,String NewPassword,String ConfirmPassword, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		
		if(OldPassword == null || OldPassword == ""){
			result.setMessage("原始密码不能为空"); 
		}
		else if(NewPassword == null || NewPassword == ""){
			result.setMessage("新密码不能为空"); 
		}
		else if(ConfirmPassword == null || ConfirmPassword == ""){
			result.setMessage("确认密码不能为空"); 
		}
		else if(NewPassword.endsWith(OldPassword)){
			result.setMessage("新密码不能和旧密码相同"); 
		}
		else if(!NewPassword.endsWith(ConfirmPassword)){
			result.setMessage("新密码和确认密码不一致"); 
		}
		else{
			int id = userID ==null || userID=="" ? 0 :  Integer.parseInt(userID); 
			BaseAccountExt baseAccountExt = accountService.query(id);
			
			if(baseAccountExt.getPassword().endsWith(OldPassword)){
				baseAccountExt.setPassword(ConfirmPassword);
				com.mopon.entity.common.Result daoresult = accountService.update(baseAccountExt);
				result.setIsSuccess(daoresult.getIsSuccess());
				if(daoresult.getIsSuccess()){
					result.setMessage("密码修改成功");
				}
				else{
					result.setMessage(daoresult.getMessage());
				}
			}else{
				result.setMessage("原始密码输入错误"); 
			}
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	
	
	
}