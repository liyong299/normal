package com.mopon.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mopon.controller.common.BaseController;
import com.mopon.entity.base.ResultList;
import com.mopon.entity.base.Result;
import com.mopon.entity.base.Tree;
import com.mopon.entity.console.base.BaseMenuExt;
import com.mopon.entity.console.base.BaseOperatorExt;
import com.mopon.entity.console.base.BaseRoleExt;
import com.mopon.entity.console.base.BaseRoleMenuExt;
import com.mopon.entity.console.base.BaseRoleMenuOperatorExt;
import com.mopon.service.menu.MenuService;
import com.mopon.service.role.RoleService;
import com.mopon.util.Page;
import com.mopon.util.ResponseWriteUtils;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController<BaseRoleExt>
{
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 展示role列表页
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "role/index";
	}
	
	/**
	 * 添加角色页
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Locale locale, Model model) {
		return "role/add";
	}
	
	/**
	 * 编辑角色页
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(Integer id) {
		BaseRoleExt role = new BaseRoleExt();
		role.setId(id);
		
		role = roleService.queryRole(role);
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("id", role.getId());
		mv.addObject("name", role.getName());
		mv.setViewName("role/edit");
		
		return mv;
	}
	
	/**
	 * 权限编辑页
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "authority", method = RequestMethod.GET)
	public ModelAndView authority(Integer roleid) {
		//获取一级菜单（大模块）
		List<BaseMenuExt> moduleList = menuService.queryMenuListByParentID(0);
		
		ModelAndView mv= new ModelAndView();
		mv.addObject("roleID", roleid);
		mv.addObject("moduleList", moduleList);
		mv.setViewName("role/authority");
		
		return mv;
	}
	
	/**
	 * 保存权限
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "saveauthority", method = RequestMethod.POST)
	public void saveAuthority(Integer roleid, Integer moduleid, @RequestParam(value = "operatorList[]") String[] operatorList, @RequestParam(value = "menuList[]") String[] menuList, HttpServletRequest request, HttpServletResponse response) {
		List<BaseRoleMenuOperatorExt> operatorList_temp = new ArrayList<BaseRoleMenuOperatorExt>();
		if(operatorList != null && !operatorList[0].equals("0")){
			for(int i = 0; i < operatorList.length; i ++){
				String[] temp = operatorList[i].split("-");
				BaseRoleMenuOperatorExt ext = new BaseRoleMenuOperatorExt();
				
				ext.setRoleId(roleid);
				ext.setMenuId(Integer.parseInt(temp[0]));
				ext.setOperatorId(Integer.parseInt(temp[1]));
				
				operatorList_temp.add(ext);
			}
		}
		
		List<BaseRoleMenuExt> menuList_temp = new ArrayList<BaseRoleMenuExt>();
		if(menuList != null && !menuList[0].equals("0")){
			for(int i = 0; i < menuList.length; i ++){
				BaseRoleMenuExt ext = new BaseRoleMenuExt();
				
				ext.setRoleId(roleid);
				ext.setMenuId(Integer.parseInt(menuList[i]));
				
				menuList_temp.add(ext);
			}
		}
		
		Result result = new Result();
		result.setIsSuccess(roleService.addRoleMenuOperator(roleid, moduleid, operatorList_temp, menuList_temp));
		if(result.getIsSuccess()){
			result.setMessage("成功");
		}
		else{
			result.setMessage("失败");
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	/**
	 * 获取role列表json数据接口
	 * @param role
	 * @param page
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getrolelist", method = RequestMethod.POST)
	public void getRolelist(BaseRoleExt role,Page<BaseRoleExt> page, HttpServletRequest request, HttpServletResponse response) {
		ResultList<BaseRoleExt> result = new ResultList<BaseRoleExt>();
//		this.setParams(request, role, page);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", role.getName());
		page.setParams(param);
		List<BaseRoleExt> list = roleService.queryRoleList(page);
		
		result.setRows(list);
		result.setTotal(page.getTotalRecord());

		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	/**
	 * 获取权限列表json数据接口
	 * @param role
	 * @param page
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getauthority", method = RequestMethod.POST)
	public void getAuthority(Integer roleid, Integer moduleid, HttpServletRequest request, HttpServletResponse response) {
		List<BaseMenuExt> menuList = roleService.queryRoleAuthorityListByModuleID(roleid, moduleid);
		List<Tree> results = new ArrayList<Tree>();
		
		for(BaseMenuExt firstMenu: menuList){
			Tree firstTree = new Tree();
			firstTree.setId(firstMenu.getId());
			firstTree.setText(firstMenu.getName());
			//parentList.setChecked(false);
			//parentList.setController(menu.getController());
			//parentList.setAction(menu.getAction());
			
			List<Tree> secondTreeList = new ArrayList<Tree>();
			for(BaseMenuExt secondMenu: firstMenu.getSubMenu()){
				Tree secondTree = new Tree();
				secondTree.setId(secondMenu.getId());
				secondTree.setText(secondMenu.getName());
				//parentList.setChecked(false);
				//parentList.setController(menu.getController());
				//parentList.setAction(menu.getAction());
				
				List<Tree> childList = new ArrayList<Tree>();
				for(BaseOperatorExt operator: secondMenu.getOperator()){
					Tree child = new Tree();
					child.setId(operator.getId());
					child.setText(operator.getName());
					child.setChecked(operator.getHaveAuthority());
//					child.setController(operator.getController());
//					child.setAction(operator.getAction());
					
					childList.add(child);
				}
				secondTree.setChildren(childList);
				
				secondTreeList.add(secondTree);
			}
			
			firstTree.setChildren(secondTreeList);
			
			results.add(firstTree);
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(results).toString());
	}
	
	/**
	 * 删除role接口
	 * @param role
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(BaseRoleExt role, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		result.setIsSuccess(roleService.deleteRole(role.getId()));
		if(result.getIsSuccess()){
			result.setMessage("成功");
		}
		else{
			result.setMessage("失败");
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	/**
	 * 添加role接口
	 * @param role
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add(BaseRoleExt role, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		
		if(role.getName() == null || role.getName() == ""){
			result.setMessage("角色名不能为空"); 
		}
		else{
			result.setIsSuccess(roleService.addRole(role));
			if(result.getIsSuccess()){
				result.setMessage("成功");
			}
			else{
				result.setMessage("失败");
			}
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	/**
	 * 编辑role接口
	 * @param role
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public void edit(BaseRoleExt role, HttpServletRequest request, HttpServletResponse response){
		Result result = new Result();
		
		if(role.getId() == null || role.getId() == 0){
			result.setMessage("ID不能为空"); 
		}
		else{
			result.setIsSuccess(roleService.updateRole(role));
			if(result.getIsSuccess()){
				result.setMessage("成功");
			}
			else{
				result.setMessage("失败");
			}
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
}