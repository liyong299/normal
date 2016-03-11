package com.mopon.service.user.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.system.SysUserMapper;
import com.mopon.entity.system.SysUser;
import com.mopon.service.user.ISysUserService;

@Service
public class SysUserService implements ISysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Override
	public SysUser selectByPrimaryKey(Long uid) {
		SysUser sysUser =null;
		try{
			sysUser = this.sysUserMapper.selectByPrimaryKey(uid);
		}catch(Exception e){
			throw e;
		}
		return sysUser;
	}

	
}
