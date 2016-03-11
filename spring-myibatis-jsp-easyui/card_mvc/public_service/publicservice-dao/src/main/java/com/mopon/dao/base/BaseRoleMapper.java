package com.mopon.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseRole;
import com.mopon.util.Page;

@Repository
public interface BaseRoleMapper {
	public List<BaseRole> queryListOfPage(Page<BaseRole> page);
	public List<BaseRole> queryList(BaseRole baseRole);
	public BaseRole query(BaseRole role);
	public Integer  insert(BaseRole role);
	public Integer delete(Integer id);
	public Integer update(BaseRole role);
}