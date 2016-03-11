package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseRoleMenuMapper;
import com.mopon.entity.BaseRoleMenu;
import com.mopon.service.base.BaseRoleMenuService;

@Service
public class BaseRoleMenuServiceImpl implements BaseRoleMenuService{
	@Autowired
	private BaseRoleMenuMapper baseRoleMenuDao;
	
	@Override
	public List<BaseRoleMenu> queryList(BaseRoleMenu baseRoleMenu){
		return baseRoleMenuDao.queryList(baseRoleMenu);
	}
	
	@Override
	public Boolean add(BaseRoleMenu baseRoleMenu){
		return baseRoleMenuDao.insert(baseRoleMenu) > 0;
	}
	
	@Override
	public Boolean delete(BaseRoleMenu baseRoleMenu){
		return baseRoleMenuDao.delete(baseRoleMenu) > 0;
	}
}