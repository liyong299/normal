package com.mopon.service.base;

import java.util.List;

import com.mopon.entity.BaseOperator;

public interface BaseOperatorService {
	public List<BaseOperator> queryList();
	public BaseOperator query(BaseOperator baseOperator);
}