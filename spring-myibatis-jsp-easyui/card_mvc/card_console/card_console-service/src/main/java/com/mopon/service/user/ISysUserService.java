package com.mopon.service.user;


import org.springframework.dao.DataAccessException;

import com.mopon.entity.system.SysUser;


public interface ISysUserService {
	public SysUser selectByPrimaryKey(Long uid) throws DataAccessException;
}