package com.mopon.service.account;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.entity.BaseAccount;
import com.mopon.entity.common.Result;
import com.mopon.entity.common.ResultT;
import com.mopon.entity.console.base.BaseAccountExt;
import com.mopon.util.Page;

@Service
public interface AccountService {
	/**
	 * 获取账户列表
	 * @param page
	 * @return
	 */
    public List<BaseAccountExt> queryList(Page<BaseAccountExt> page);
	
    /**
     * 根据账户ID获取账户信息
     * @param id
     * @return
     */
    public BaseAccountExt query(Integer id);
    
    /**
     * 根据账户登录名获取账户信息
     * @param name
     * @return
     */
    public BaseAccountExt query(String name);
    
    /**
     * 获取用户信息
     * @param baseAccountExt
     * @return
     */
    public BaseAccountExt query(BaseAccountExt baseAccountExt);
    
    /**
     * 判断用户是否合法
     * @param baseAccountExt
     * @return
     */
    public ResultT<BaseAccountExt> checkAccount(BaseAccountExt baseAccountExt);
	
    /**
     * 判断
     * @param account
     * @return
     */
    public Boolean exist(String name);
	
    /**
     *添加账户信息
     * @param account
     * @return
     */
    public Result  add(BaseAccountExt baseAccountExt);

    /**
     * 根据ID删除账户
     * @param id
     * @return
     */
    public Boolean delete(Integer id);
    
    /**
     * 修改账户信息
     * @param account
     * @return
     */
    public Result update(BaseAccountExt baseAccountExt);
    
    /**
     * 修改账户最后登录时间
     * @param id
     * @return
     */
    public Result updateLastTime(Integer id);
	
	/*
	 *  账户启用
	 * */
	public Boolean enable(Integer id);
	
	/*
	 *  账户禁用
	 * */
	public Boolean disable(Integer id);
}