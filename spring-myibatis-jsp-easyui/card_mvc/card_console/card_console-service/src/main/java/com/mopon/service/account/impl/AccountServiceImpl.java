package com.mopon.service.account.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.entity.BaseAccount;
import com.mopon.entity.common.Result;
import com.mopon.entity.common.ResultT;
import com.mopon.entity.console.base.BaseAccountExt;
import com.mopon.entity.console.base.BaseRoleExt;
import com.mopon.service.account.AccountService;
import com.mopon.service.role.RoleService;
import com.mopon.util.DateUtils;
import com.mopon.util.Page;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private com.mopon.service.base.BaseAccountService baseAccountService;
	
	@Autowired
	private RoleService roleService;
	
	{
		System.out.println("----加载当前类：" + getClass());
	}
	/**
	 * 获取账户列表
	 * @param page
	 * @return
	 */
    public List<BaseAccountExt> queryList(Page<BaseAccountExt> page){
    	Page<BaseAccount> wherePage = new Page<BaseAccount>();
    	wherePage.setParams(page.getParams());
    	wherePage.setPage(page.getPage());
    	wherePage.setRows(page.getRows());
    	
    	List<BaseAccount> list = baseAccountService.queryList(wherePage);
    	List<BaseAccountExt> results = new ArrayList<BaseAccountExt>();
    	List<BaseRoleExt> roleList = roleService.queryRoleList(new BaseRoleExt());
    	page.setTotalRecord(wherePage.getTotalRecord());
    	for(BaseAccount temp: list){
    		BaseAccountExt ext = new BaseAccountExt();
    		ext.setId(temp.getId());
    		ext.setRoleID(temp.getRoleID());
    		ext.setName(temp.getName());
    		ext.setFullName(temp.getFullName());
    		ext.setStatus(temp.getStatus());
    		ext.setLoginTimes(temp.getLoginTimes());
    		ext.setLastTime(temp.getLastTime());
    		ext.setCreateTime(temp.getCreateTime());
    		ext.setCreator(temp.getCreator());
    		ext.setModifiedDate(temp.getModifiedDate());
    		ext.setModifier(temp.getModifier());
    		
    		for(BaseRoleExt role: roleList){
    			if(role.getId() == ext.getRoleID()){
    				ext.setRoleName(role.getName());
    			}
    		}
    		
    		if(temp.getCreateTime() != null){
				ext.setCreateTimeShow(DateUtils.format(temp.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			
			if(temp.getLastTime() != null){
				ext.setLastTimeShow(DateUtils.format(temp.getLastTime(), "yyyy-MM-dd HH:mm:ss"));
			}
    		
    		results.add(ext);
    	}
    	
    	return results;
    }
	
    /**
     * 根据账户ID获取账户信息
     * @param id
     * @return
     */
    public BaseAccountExt query(Integer id){
    	BaseAccountExt where = new BaseAccountExt();
    	where.setId(id);
    	
    	return query(where);
    }
    
    /**
     * 根据账户登录名获取账户信息
     * @param name
     * @return
     */
    public BaseAccountExt query(String name){
    	BaseAccountExt where = new BaseAccountExt();
    	where.setName(name);
    	
    	return query(where);
    }
    
    /**
     * 获取用户信息
     * @param baseAccountExt
     * @return
     */
    public BaseAccountExt query(BaseAccountExt baseAccountExt){
    	BaseAccount where = new BaseAccount();
    	where.setId(baseAccountExt.getId());
    	where.setName(baseAccountExt.getName());
    	
    	BaseAccount entity = baseAccountService.query(where);
    	if(entity == null){
    		return null;
    	}
    	BaseAccountExt result = new BaseAccountExt();
    	
    	result.setId(entity.getId());
    	result.setRoleID(entity.getRoleID());
    	result.setName(entity.getName());
    	result.setPassword(entity.getPassword());
    	result.setFullName(entity.getFullName());
    	result.setStatus(entity.getStatus());
    	result.setLoginTimes(entity.getLoginTimes());
    	result.setLoginTimes(entity.getLoginTimes());
    	result.setLastTime(entity.getLastTime());
    	result.setCreateTime(entity.getCreateTime());
    	result.setCreator(entity.getCreator());
    	result.setModifiedDate(entity.getModifiedDate());
    	result.setModifier(entity.getModifier());
    	
    	if(entity.getCreateTime() != null){
    		result.setCreateTimeShow(DateUtils.format(entity.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		}
		
		if(entity.getLastTime() != null){
			result.setLastTimeShow(DateUtils.format(entity.getLastTime(), "yyyy-MM-dd HH:mm:ss"));
		}
		
    	
    	return result;
    }
    
    /**
     * 判断用户是否合法
     * @param baseAccountExt
     * @return
     */
    public ResultT<BaseAccountExt> checkAccount(BaseAccountExt where){
    	ResultT<BaseAccountExt> result = new ResultT<BaseAccountExt>();
    	
    	BaseAccountExt account = query(where.getName());
    	if(account != null && where.getName().equals(account.getName()) && where.getPassword().equals(account.getPassword()) && account.getStatus()==1){
    		result.setIsSuccess(true);
    		result.setMessage("登录成功");
    		result.setResult(account);
    	}
    	else{
    		result.setIsSuccess(false);
    		result.setMessage("用户名或密码错误");
    		result.setResult(account);
    	}
    	return result;
    }
	
    /**
     * 判断
     * @param account
     * @return
     */
    public Boolean exist(String name){
    	BaseAccount where = new BaseAccount();
    	where.setName(name);
    	
    	return baseAccountService.exist(where);
    }
	
    /**
     *添加账户信息
     * @param account
     * @return
     */
    public Result  add(BaseAccountExt baseAccountExt){
		Result result = new Result(); 
		if(exist(baseAccountExt.getName())){
			result.setIsSuccess(false);
			result.setMessage("用户已存在");
			return result;
		}
    	
    	BaseAccount where = new BaseAccount();
    	where.setRoleID(baseAccountExt.getRoleID());
		where.setName(baseAccountExt.getName());
		where.setPassword(baseAccountExt.getPassword());
		where.setFullName(baseAccountExt.getFullName());
		where.setStatus(baseAccountExt.getStatus());
		where.setCreateTime(new Date());
		
    	result.setIsSuccess(baseAccountService.add(where));
    	return result;
    }

    /**
     * 根据ID删除账户
     * @param id
     * @return
     */
    public Boolean delete(Integer id){
    	return baseAccountService.delete(id);
    }
    
    /**
     * 修改账户信息
     * @param account
     * @return
     */
    public Result update(BaseAccountExt baseAccountExt){
    	Result result = new Result(); 
    	
    	if(baseAccountExt.getId() == null){
    		result.setIsSuccess(false);
    		result.setMessage("id为空");
    		return result;
    	}
    	
    	BaseAccount where = new BaseAccount();
    	where.setId(baseAccountExt.getId());
    	where.setRoleID(baseAccountExt.getRoleID());
		where.setName(baseAccountExt.getName());
		where.setPassword(baseAccountExt.getPassword());
		where.setFullName(baseAccountExt.getFullName());
		where.setStatus(baseAccountExt.getStatus());
		where.setLoginTimes(baseAccountExt.getLoginTimes());
		where.setLastTime(baseAccountExt.getLastTime());
		where.setModifier(baseAccountExt.getModifier());
		where.setModifiedDate(baseAccountExt.getModifiedDate());
    	
    	result.setIsSuccess(baseAccountService.update(where));
    	
    	return result;
    }
    
    /**
     * 修改账户最后登录时间
     * @param id
     * @return
     */
    public Result updateLastTime(Integer id){
    	BaseAccountExt baseAccountExt = new BaseAccountExt();
    	baseAccountExt.setId(id);
    	baseAccountExt.setLastTime(new Date());
    	
    	return update(baseAccountExt);
    }
	
	/*
	 *  账户启用
	 * */
	public Boolean enable(Integer id){
		BaseAccountExt entity = new BaseAccountExt();
		entity.setId(id);
		entity.setStatus(1);
		
		return update(entity).getIsSuccess();
	}
	
	/*
	 *  账户禁用
	 * */
	public Boolean disable(Integer id){
		BaseAccountExt entity = new BaseAccountExt();
		entity.setId(id);
		entity.setStatus(0);
		
		return update(entity).getIsSuccess();
	}
}