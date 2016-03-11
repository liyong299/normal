package com.mopon.service.base;

import java.util.List;

import com.mopon.entity.BaseRoleMenuOperator;

public interface BaseRoleMenuOperatorService {
	public List<BaseRoleMenuOperator> queryList(BaseRoleMenuOperator baseRoleMenuOperator);
	public Boolean add(BaseRoleMenuOperator baseRoleMenuOperator);
	public Boolean delete(BaseRoleMenuOperator baseRoleMenuOperator);
}