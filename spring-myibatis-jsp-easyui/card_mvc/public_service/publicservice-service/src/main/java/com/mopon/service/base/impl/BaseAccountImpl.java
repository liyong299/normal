package com.mopon.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.base.BaseAccountMapper;
import com.mopon.entity.BaseAccount;
import com.mopon.service.base.BaseAccountService;
import com.mopon.util.Page;

@Service
public class BaseAccountImpl implements BaseAccountService {
	@Autowired
	private BaseAccountMapper baseAccountDao;
	
	/**
	 * 判断账户列表
	 */
	@Override
	public List<BaseAccount> queryList(Page<BaseAccount> page){
		return baseAccountDao.queryList(page);
	}
	
	/**
	 * 查询账户信息
	 */
	@Override
	public BaseAccount query(BaseAccount baseAccount){
		return baseAccountDao.query(baseAccount);
	}
	
	/**
	 * 判断账户是否存在
	 */
	@Override
	public Boolean exist(BaseAccount baseAccount){
		return baseAccountDao.exist(baseAccount) > 0;
	}
	
	/**
	 * 添加账户
	 */
	@Override
	public Boolean  add(BaseAccount baseAccount){
		return baseAccountDao.insert(baseAccount) > 0;
	}

	/**
	 * 删除账户
	 */
	@Override
	public Boolean delete(Integer id){
		return baseAccountDao.delete(id) > 0;
	}

	/**
	 * 修改账户信息
	 */
	@Override
	public Boolean update(BaseAccount account){
		return baseAccountDao.update(account) > 0;
	}
}