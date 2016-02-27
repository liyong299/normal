/**
 * 
 */
package com.dup.core.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.dup.core.model.UserModel;
import com.dup.core.vo.User;

/**
 * @author Liyong
 *
 */
@Service
public class UserService {

	public User findUserByByCondition(UserModel userModel) {
		User user = new User()	;
		user.setCreateTime(new Date().toString());
		user.setUpdateTime(new Date());
		user.setUserName(userModel.getUserName());
		user.setPassword(userModel.getPassword());
		return user;
	}

	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public User getUserById(String string) {
		User user = new User()	;
		user.setCreateTime(new Date().toString());
		user.setUpdateTime(new Date());
		user.setUserName("default");
		user.setPassword("default");
		return user;
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
