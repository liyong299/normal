package com.dup.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dup.base.model.Page;
import com.dup.core.dao.entities.BaseAccount;

@Repository
public interface BaseAccountMapper {
	public List<BaseAccount> queryList(Page<BaseAccount> page);
	public BaseAccount query(BaseAccount account);
	public Integer exist(BaseAccount account);
	public Integer  insert(BaseAccount account);
	public Integer delete(Integer id);
	public Integer update(BaseAccount account);
}