package com.mopon.service.base;

import java.util.List;

import com.mopon.entity.BaseAccount;
import com.mopon.util.Page;

public interface BaseAccountService {
	public List<BaseAccount> queryList(Page<BaseAccount> page);
	public BaseAccount query(BaseAccount account);
	public Boolean exist(BaseAccount account);
	public Boolean  add(BaseAccount account);
	public Boolean delete(Integer id);
	public Boolean update(BaseAccount account);
}