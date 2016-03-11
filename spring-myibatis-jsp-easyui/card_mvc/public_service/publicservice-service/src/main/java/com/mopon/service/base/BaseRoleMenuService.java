package com.mopon.service.base;

import java.util.List;

import com.mopon.entity.BaseRoleMenu;

public interface BaseRoleMenuService {
	public List<BaseRoleMenu> queryList(BaseRoleMenu baseRoleMenu);
	public Boolean add(BaseRoleMenu baseRoleMenu);
	public Boolean delete(BaseRoleMenu baseRoleMenu);
}
