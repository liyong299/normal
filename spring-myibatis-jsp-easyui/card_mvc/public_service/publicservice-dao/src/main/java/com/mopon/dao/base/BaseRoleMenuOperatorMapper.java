package com.mopon.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseRoleMenuOperator;

@Repository
public interface BaseRoleMenuOperatorMapper {
	public List<BaseRoleMenuOperator> queryList(BaseRoleMenuOperator baseRoleMenuOperator);
	public Integer insert(BaseRoleMenuOperator baseRoleMenuOperator);
	public Integer delete(BaseRoleMenuOperator baseRoleMenuOperator);
}