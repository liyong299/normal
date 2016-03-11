/**
 * 
 */
package com.dup.core.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dup.core.dao.BaseAccountMapper;
import com.dup.core.dao.entities.BaseAccount;
import com.dup.core.model.UserModel;
import com.dup.core.vo.User;

/**
 * @author Liyong
 *
 */
@Service
public class UserService {

	@Autowired
	BaseAccountMapper baseAccountMapper;
	
	public User findUserByByCondition(UserModel userModel) {
		User user = new User()	;
		user.setCreateTime(new Date().toString());
		user.setUpdateTime(new Date());
		user.setUserName(userModel.getUserName());
		user.setPassword(userModel.getPassword());
		return user;
	}

	public void saveUser(User user) {
		
	}

	public User getUserById(String string) {
		
		BaseAccount account = new BaseAccount();
		account.setName(string);
		BaseAccount dbAccount = this.baseAccountMapper.query(account);
		User user = new User(dbAccount)	;
		return user;
	}

	public void deleteUser(User user) 
	{
		// TODO Auto-generated method stub
		
	}

	public void updateUser(User user) 
	{
		
	}

}
