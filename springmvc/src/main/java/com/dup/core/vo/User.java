/**
 * 
 */
package com.dup.core.vo;

import com.dup.core.dao.entities.BaseAccount;

/**
 * @author Liyong
 *
 */
public class User {
	{
		System.out.println("-----------当前加载的类 ：：：：" + getClass());
	}

	private String userName;
	private String password;
	private String createTime;
	private java.util.Date updateTime;
	private String updateUser;
	private String createUser;
	
	public User()
	{
		
	}
	public User(BaseAccount baseAccount)
	{
		this.userName = baseAccount.getName();
		this.createTime = baseAccount.getCreateTime().toString();
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
