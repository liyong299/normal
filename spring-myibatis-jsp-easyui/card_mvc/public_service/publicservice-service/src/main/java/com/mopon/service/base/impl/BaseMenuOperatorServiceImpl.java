package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseMenuOperatorMapper;
import com.mopon.entity.BaseMenuOperator;
import com.mopon.service.base.BaseMenuOperatorService;

@Service
public class BaseMenuOperatorServiceImpl implements BaseMenuOperatorService {
	@Autowired
	private BaseMenuOperatorMapper baseMenuOperatorDao;
	
	@Override
	public List<BaseMenuOperator> queryList(){
		return baseMenuOperatorDao.queryList();
	}
}