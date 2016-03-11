package com.mopon.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseMenuOperator;

@Repository
public interface BaseMenuOperatorMapper {
	public List<BaseMenuOperator> queryList();
}