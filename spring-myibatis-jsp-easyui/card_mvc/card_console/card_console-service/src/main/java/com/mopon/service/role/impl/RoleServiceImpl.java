package com.mopon.service.role.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.entity.BaseOperator;
import com.mopon.entity.BaseRole;
import com.mopon.entity.BaseRoleMenu;
import com.mopon.entity.BaseRoleMenuOperator;
import com.mopon.entity.console.base.BaseMenuExt;
import com.mopon.entity.console.base.BaseOperatorExt;
import com.mopon.entity.console.base.BaseRoleExt;
import com.mopon.entity.console.base.BaseRoleMenuExt;
import com.mopon.entity.console.base.BaseRoleMenuOperatorExt;
import com.mopon.service.role.RoleService;
import com.mopon.util.Page;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private com.mopon.service.base.BaseRoleService baseRoleService;
	
	@Autowired
	private com.mopon.service.menu.MenuService menuService;
	
	@Autowired
	private com.mopon.service.base.BaseRoleMenuOperatorService baseRoleMenuOperatorService;
	
	@Autowired
	private com.mopon.service.base.BaseRoleMenuService baseRoleMenuService;
	
	@Autowired
	private com.mopon.service.base.BaseOperatorService baseOperatorService;
	
	public List<BaseRoleExt> queryRoleList(BaseRoleExt baseRoleExt){
		BaseRole where = new BaseRole();
		where.setName(baseRoleExt.getName());
		List<BaseRole> list = baseRoleService.queryList(where);
		List<BaseRoleExt> results = new ArrayList<BaseRoleExt>();
		
		for(BaseRole temp: list){
			BaseRoleExt ext = new BaseRoleExt();
			ext.setId(temp.getId());
			ext.setName(temp.getName());
			ext.setSystem(temp.getSystem());
			ext.setCreateTime(temp.getCreateTime());
			ext.setCreator(temp.getCreator());
			ext.setModifiedDate(temp.getModifiedDate());
			ext.setModifier(temp.getModifier());
			
			if(temp.getCreateTime() != null){
				ext.setCreateTimeShow(DateFormat.getDateInstance().format(temp.getCreateTime()));
			}
			
			results.add(ext);
		}
		
		return results;
	}
	
	/**
	 * 获取角色列表（分页）
	 */
	public List<BaseRoleExt> queryRoleList(Page<BaseRoleExt> page){
		Page<BaseRole> where = new Page<BaseRole>();
		where.setParams(page.getParams());
		where.setPage(page.getPage());
		where.setRows(page.getRows());
		
		List<BaseRole> list = baseRoleService.queryList(where);
		List<BaseRoleExt> results = new ArrayList<BaseRoleExt>();
		
		page.setTotalRecord(where.getTotalRecord());
		
		for(BaseRole temp: list){
			BaseRoleExt ext = new BaseRoleExt();
			ext.setId(temp.getId());
			ext.setName(temp.getName());
			ext.setSystem(temp.getSystem());
			ext.setCreateTime(temp.getCreateTime());
			ext.setCreator(temp.getCreator());
			ext.setModifiedDate(temp.getModifiedDate());
			ext.setModifier(temp.getModifier());
			
			if(temp.getCreateTime() != null){
				ext.setCreateTimeShow(DateFormat.getDateInstance().format(temp.getCreateTime()));
			}
			
			results.add(ext);
		}
		
		return results;
	}
	
	/**
	 * 查询角色信息
	 */
	public BaseRoleExt queryRole(BaseRoleExt baseRoleExt){
		BaseRole where = new BaseRole();
		where.setId(baseRoleExt.getId());
		
		BaseRole entity = baseRoleService.query(baseRoleExt);
		BaseRoleExt result = new BaseRoleExt();
		
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setSystem(entity.getSystem());
		result.setCreateTime(entity.getCreateTime());
		result.setCreator(entity.getCreator());
		result.setModifiedDate(entity.getModifiedDate());
		result.setModifier(entity.getModifier());
		
		return result;
	}
	
	/**
	 * 获取角色所具有权限的模块
	 * @param roleID
	 * @return
	 */
	public List<BaseMenuExt> queryRoleModule(Integer roleID){
		List<BaseMenuExt> menuList = menuService.queryMenuListByParentID(0);
		
		BaseRoleMenu baseRoleMenu = new BaseRoleMenu();
		baseRoleMenu.setRoleId(roleID);
		List<BaseRoleMenu> roleMenuList = baseRoleMenuService.queryList(baseRoleMenu);
		
		List<BaseMenuExt> results = new ArrayList<BaseMenuExt>();
		for(BaseMenuExt module: menuList){
			for(BaseRoleMenu roleMenu: roleMenuList){
				if(module.getId() == roleMenu.getMenuId()){
					results.add(module);
				}
			}
		}
		return results;
	}
	
	/**
	 * 根据角色ID、模块ID返回角色所有具有权限的菜单
	 */
	public List<BaseMenuExt> queryRoleAuthorityList(Integer roleID, Integer moduleID){
		List<BaseMenuExt> results = new ArrayList<BaseMenuExt>();
		List<BaseMenuExt> menuList = menuService.queryMenuOperatorList(moduleID);
		BaseRoleMenu baseRoleMenu = new BaseRoleMenu();
		baseRoleMenu.setRoleId(roleID);
		List<BaseRoleMenu> roleMenuList = baseRoleMenuService.queryList(baseRoleMenu);
		
		for(BaseMenuExt firstMenu: menuList){
			BaseMenuExt firstMenu_temp = new BaseMenuExt();
			
			List<BaseMenuExt> secondMenu_temp = new ArrayList<BaseMenuExt>();
			for(BaseMenuExt secondMenu: firstMenu.getSubMenu()){
				secondMenu.setOperator(null);
				for(BaseRoleMenu roleMenu: roleMenuList){
					if(secondMenu.getId() == roleMenu.getMenuId()){
						secondMenu_temp.add(secondMenu);
					}
				}
			}
			
			for(BaseRoleMenu roleMenu: roleMenuList){
				if(firstMenu.getId() == roleMenu.getMenuId()){
					firstMenu_temp.setId(firstMenu.getId());
					firstMenu_temp.setName(firstMenu.getName());
					firstMenu_temp.setSubMenu(secondMenu_temp);
					results.add(firstMenu_temp);
				}
			}
		}
		return results;
	}
	
	/**
	 * 查询是否具有这个操作项权限
	 * @param roleID
	 * @param controller
	 * @param action
	 * @return
	 */
	public Boolean queryRoleOperatorAuthority(Integer roleID, String operatorName, String controller, String action){
		BaseRoleMenuOperator baseRoleMenuOperator = new BaseRoleMenuOperator();
		baseRoleMenuOperator.setRoleId(roleID);
		List<BaseRoleMenuOperator> roleMenuOperatorList = baseRoleMenuOperatorService.queryList(baseRoleMenuOperator);
		
		BaseOperator operator = new BaseOperator();
		operator.setName(operatorName);
		operator = baseOperatorService.query(operator);
		
		BaseMenuExt menuExt = new BaseMenuExt();
		menuExt.setController(controller);
		menuExt.setAction(action);
		menuExt = menuService.query(menuExt);
		
		if(operator == null || menuExt == null){
			return false;
		}
		
		for(BaseRoleMenuOperator roleMenuOperator: roleMenuOperatorList){
			if(roleMenuOperator.getOperatorId() == operator.getId() && roleMenuOperator.getMenuId() == menuExt.getId()){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 根据角色ID、模块ID返回角色所有菜单及是否具有操作项的权限
	 */
	public List<BaseMenuExt> queryRoleAuthorityListByModuleID(Integer roleID, Integer moduleID){
		List<BaseMenuExt> menuList = menuService.queryMenuOperatorList(moduleID);
		
		BaseRoleMenuOperator baseRoleMenuOperator = new BaseRoleMenuOperator();
		baseRoleMenuOperator.setRoleId(roleID);
		List<BaseRoleMenuOperator> roleMenuOperatorList = baseRoleMenuOperatorService.queryList(baseRoleMenuOperator);
		for(BaseMenuExt firstMenu: menuList){
			for(BaseMenuExt secondMenu: firstMenu.getSubMenu()){
				//过滤重复的操作项
				List<BaseOperatorExt> operatorList = new ArrayList<BaseOperatorExt>();
				for(BaseOperatorExt operator: secondMenu.getOperator()){
					Boolean b = false;
					for(BaseOperatorExt temp: operatorList){
						if(operator.getId() == temp.getId()){
							b = true;
							break;
						}
					}
					if(!b){
						operatorList.add(operator);
					}
				}
				secondMenu.setOperator(operatorList);
				
				for(BaseOperatorExt operator: secondMenu.getOperator()){
					for(BaseRoleMenuOperator roleMenuOperator: roleMenuOperatorList){
						if(secondMenu.getId() == roleMenuOperator.getMenuId() && operator.getId() == roleMenuOperator.getOperatorId()){
							operator.setHaveAuthority(true);
						}
					}
				}
			}
		}
		
		
		return menuList;
	}
	
	public Boolean  addRole(BaseRoleExt role){
		role.setCreateTime(new Date());
		return baseRoleService.add(role);
	}
	public Boolean deleteRole(Integer id){
		return baseRoleService.delete(id);
	}
	public Boolean updateRole(BaseRoleExt role){
		return baseRoleService.update(role);
	}
	
	/**
	 * 添加角色菜单操作项关联
	 */
	public Boolean addRoleMenuOperator(Integer roleID, Integer moduleID, List<BaseRoleMenuOperatorExt> menuOperatorList, List<BaseRoleMenuExt> menuList){
		//删除之前的
		List<BaseMenuExt> del_menuList = menuService.queryMenuOperatorList(moduleID);
		BaseRoleMenuOperator roleMenuOperatorWhere = new BaseRoleMenuOperator();
		BaseRoleMenu roleMenuWhere = new BaseRoleMenu();
		
		for(BaseMenuExt firstMenu: del_menuList){
			for(BaseMenuExt secondMenu: firstMenu.getSubMenu()){
				for(BaseOperatorExt operator: secondMenu.getOperator()){
					roleMenuOperatorWhere.setRoleId(roleID);
					roleMenuOperatorWhere.setMenuId(secondMenu.getId());
					roleMenuOperatorWhere.setOperatorId(operator.getId());
					
					baseRoleMenuOperatorService.delete(roleMenuOperatorWhere);
				}
				
				roleMenuWhere.setRoleId(roleID);
				roleMenuWhere.setMenuId(secondMenu.getId());
				baseRoleMenuService.delete(roleMenuWhere);
			}
			
			roleMenuWhere.setRoleId(roleID);
			roleMenuWhere.setMenuId(firstMenu.getId());
			baseRoleMenuService.delete(roleMenuWhere);
		}
		
		roleMenuWhere.setRoleId(roleID);
		roleMenuWhere.setMenuId(moduleID);
		baseRoleMenuService.delete(roleMenuWhere);
		
		//循环插入
		for(BaseRoleMenuOperatorExt ext: menuOperatorList){
			roleMenuOperatorWhere.setRoleId(ext.getRoleId());
			roleMenuOperatorWhere.setMenuId(ext.getMenuId());
			roleMenuOperatorWhere.setOperatorId(ext.getOperatorId());
			
			baseRoleMenuOperatorService.add(roleMenuOperatorWhere);
		}

		//循环插入
		for(BaseRoleMenuExt ext: menuList){
			roleMenuWhere.setRoleId(ext.getRoleId());
			roleMenuWhere.setMenuId(ext.getMenuId());
			
			baseRoleMenuService.add(roleMenuWhere);
		}
		
		return true;
	}
}