package com.mopon.card.api.facade.v1;

import com.mopon.card.api.ApiReply;
import com.mopon.card.api.facade.v1.vo.UserVo;
import com.mopon.entity.console.base.BaseAccountExt;

/**
 * 查询影院响应对象。
 */
public class UserReply extends ApiReply 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8594685865647883472L;
	private UserVo user;

	/**
	 * 构造方法。
	 */
	public UserReply(BaseAccountExt user) {
		this.user = new UserVo(user);
	}

	public UserReply() {
	}

	/**
	 * @return the user
	 */
	public UserVo getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserVo user)
	{
		this.user = user;
	}

	
}