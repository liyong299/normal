package com.mopon.dao.base;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mopon.entity.BaseAccount;
import com.mopon.util.Page;

@Repository
public interface BaseAccountMapper {
	public List<BaseAccount> queryList(Page<BaseAccount> page);
	public BaseAccount query(BaseAccount account);
	public Integer exist(BaseAccount account);
	public Integer  insert(BaseAccount account);
	public Integer delete(Integer id);
	public Integer update(BaseAccount account);
}