package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseRoleMenuOperatorMapper;
import com.mopon.entity.BaseRoleMenuOperator;
import com.mopon.service.base.BaseRoleMenuOperatorService;

@Service
public class BaseRoleMenuOperatorServiceImpl implements BaseRoleMenuOperatorService {
	@Autowired
	private BaseRoleMenuOperatorMapper baseRoleMenuOperatorDao;
	
	@Override
	public List<BaseRoleMenuOperator> queryList(BaseRoleMenuOperator baseRoleMenuOperator){
		return baseRoleMenuOperatorDao.queryList(baseRoleMenuOperator);
	}
	
	@Override
	public Boolean add(BaseRoleMenuOperator baseRoleMenuOperator){
		return baseRoleMenuOperatorDao.insert(baseRoleMenuOperator) > 0;
	}
	
	@Override
	public Boolean delete(BaseRoleMenuOperator baseRoleMenuOperator){
		return baseRoleMenuOperatorDao.delete(baseRoleMenuOperator) > 0;
	}
}