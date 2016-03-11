package com.mopon.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseOperator;

@Repository
public interface BaseOperatorMapper {
	public List<BaseOperator> queryList();
	public BaseOperator query(BaseOperator baseOperator);
}