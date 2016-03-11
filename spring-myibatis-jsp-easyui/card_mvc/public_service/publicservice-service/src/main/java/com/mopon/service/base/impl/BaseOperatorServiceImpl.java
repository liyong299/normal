package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseOperatorMapper;
import com.mopon.entity.BaseOperator;
import com.mopon.service.base.BaseOperatorService;

@Service
public class BaseOperatorServiceImpl implements BaseOperatorService {
	@Autowired
	private BaseOperatorMapper baseOperatorDao;
	
	@Override
	public List<BaseOperator> queryList(){
		return baseOperatorDao.queryList();
	}
	
	@Override
	public BaseOperator query(BaseOperator baseOperator){
		return baseOperatorDao.query(baseOperator);
	}
}