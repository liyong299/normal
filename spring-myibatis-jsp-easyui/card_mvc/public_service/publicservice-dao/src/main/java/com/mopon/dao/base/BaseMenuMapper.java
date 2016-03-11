package com.mopon.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseMenu;

@Repository
public interface BaseMenuMapper {
	public List<BaseMenu> queryList(BaseMenu menu);
	public BaseMenu query(BaseMenu menu);
}