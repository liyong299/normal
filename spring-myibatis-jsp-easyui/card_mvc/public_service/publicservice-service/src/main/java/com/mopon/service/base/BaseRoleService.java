package com.mopon.service.base;

import java.util.List;

import com.mopon.entity.BaseRole;
import com.mopon.util.Page;

public interface BaseRoleService {
	public List<BaseRole> queryList(BaseRole baseRole);
	public List<BaseRole> queryList(Page<BaseRole> page);
	public BaseRole query(BaseRole role);
	public Boolean  add(BaseRole role);
	public Boolean delete(Integer id);
	public Boolean update(BaseRole role);
}