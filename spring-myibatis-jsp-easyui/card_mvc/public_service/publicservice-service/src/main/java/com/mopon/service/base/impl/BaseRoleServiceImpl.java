package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseRoleMapper;
import com.mopon.entity.BaseRole;
import com.mopon.service.base.BaseRoleService;
import com.mopon.util.Page;

@Service
public class BaseRoleServiceImpl implements BaseRoleService {
	@Autowired
	private BaseRoleMapper baseRoleDao;
	
	@Override
	public List<BaseRole> queryList(BaseRole baseRole){
		return baseRoleDao.queryList(baseRole);
	}
	
	@Override
	public List<BaseRole> queryList(Page<BaseRole> page){
		return baseRoleDao.queryListOfPage(page);
	}
	
	@Override
	public BaseRole query(BaseRole role){
		return baseRoleDao.query(role);
	}
	
	@Override
	public Boolean  add(BaseRole role){
		return baseRoleDao.insert(role) > 0;
	}
	
	@Override
	public Boolean delete(Integer id){
		return baseRoleDao.delete(id) > 0;
	}
	
	@Override
	public Boolean update(BaseRole role){
		return baseRoleDao.update(role) > 0;
	}
}