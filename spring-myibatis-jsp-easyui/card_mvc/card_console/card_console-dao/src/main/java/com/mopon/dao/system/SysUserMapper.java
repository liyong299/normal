package com.mopon.dao.system;

import org.springframework.stereotype.Repository;

import com.mopon.entity.system.SysUser;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long uid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}