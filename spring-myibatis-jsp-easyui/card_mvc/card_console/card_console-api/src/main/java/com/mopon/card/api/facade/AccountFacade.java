package com.mopon.card.api.facade;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mopon.card.api.facade.v1.UserQuery;
import com.mopon.card.api.facade.v1.UserReply;
import com.mopon.entity.console.base.BaseAccountExt;
import com.mopon.service.account.AccountService;

/**
 * 票务接口面板。
 */
@Service
public class AccountFacade
{

	@Resource
	private AccountService accountService;

	/**
	 * 新增用户
	 */
	@Transactional
	public void addUser(UserQuery query)
	{
		System.out.println("---------方法未实现，只是测试用----------");
		accountService.add(null);
	}

	/**
	 * 查询用户
	 */
	public UserReply queryUser(UserQuery query)
	{
		BaseAccountExt user = accountService.query(query.getAppCode());

		return new UserReply(user);
	}

	/**
	 * 查询用户
	 */
	public UserReply queryUser(String appCode)
	{
		BaseAccountExt user = accountService.query(appCode);

		return new UserReply(user);
	}
}