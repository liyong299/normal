package com.mopon.service.role;

import java.util.List;

import com.mopon.entity.console.base.BaseMenuExt;
import com.mopon.entity.console.base.BaseRoleExt;
import com.mopon.entity.console.base.BaseRoleMenuExt;
import com.mopon.entity.console.base.BaseRoleMenuOperatorExt;
import com.mopon.util.Page;

public interface RoleService {
	public List<BaseRoleExt> queryRoleList(BaseRoleExt baseRole);
	public List<BaseRoleExt> queryRoleList(Page<BaseRoleExt> page);
	
	/**
	 * 获取角色所具有的模块权限
	 * @param roleID
	 * @return
	 */
	public List<BaseMenuExt> queryRoleModule(Integer roleID);
	
	public BaseRoleExt queryRole(BaseRoleExt role);
	
	/**
	 * 获取角色权限列表
	 * @param role
	 * @return
	 */
	public List<BaseMenuExt> queryRoleAuthorityList(Integer roleID, Integer moduleID);
	
	/**
	 * 查询是否具有这个操作项权限
	 * @param roleID
	 * @param controller
	 * @param action
	 * @return
	 */
	public Boolean queryRoleOperatorAuthority(Integer roleID, String operatorName, String controller, String action);
	
	/**
	 * 根据模块ID获取角色权限列表
	 * @param role
	 * @return
	 */
	public List<BaseMenuExt> queryRoleAuthorityListByModuleID(Integer roleID, Integer moduleID);
	public Boolean  addRole(BaseRoleExt role);
	public Boolean deleteRole(Integer id);
	public Boolean updateRole(BaseRoleExt role);
	
	public Boolean addRoleMenuOperator(Integer roleID, Integer moduleID, List<BaseRoleMenuOperatorExt> menuOperatorList, List<BaseRoleMenuExt> menuList);
}