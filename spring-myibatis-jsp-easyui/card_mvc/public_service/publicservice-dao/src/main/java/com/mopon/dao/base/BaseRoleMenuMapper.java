package com.mopon.dao.base;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseRoleMenu;

@Repository
public interface BaseRoleMenuMapper {
	public List<BaseRoleMenu> queryList(BaseRoleMenu roleMenu);
	public Integer  insert(BaseRoleMenu roleMenu);
	public Integer delete(BaseRoleMenu roleMenu);
}