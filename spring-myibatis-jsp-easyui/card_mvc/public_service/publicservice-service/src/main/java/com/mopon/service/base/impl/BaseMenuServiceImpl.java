package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseMenuMapper;
import com.mopon.entity.BaseMenu;
import com.mopon.service.base.BaseMenuService;

@Service
public class BaseMenuServiceImpl implements BaseMenuService {
	@Autowired
	private BaseMenuMapper baseMenuDao;
	
	@Override
	public List<BaseMenu> queryList(BaseMenu menu){
		return baseMenuDao.queryList(menu);
	}
	
	@Override
	public BaseMenu query(BaseMenu menu){
		return baseMenuDao.query(menu);
	}
}