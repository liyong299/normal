package com.mopon.service.base;

import java.util.List;

import com.mopon.entity.BaseMenu;

public interface BaseMenuService {
	public List<BaseMenu> queryList(BaseMenu menu);
	public BaseMenu query(BaseMenu menu);
}