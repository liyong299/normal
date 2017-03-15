package com.ly.java.thrift.compareServer;

import org.apache.thrift.TException;

import com.ly.java.thrift.compareServer.UserService.Iface;

public class UserServiceImpl implements Iface {

	@Override
	public String addUser(String username) throws TException {
		return "addUser:" + username + ",time:" + System.currentTimeMillis();
	}

	@Override
	public String getUser(String username) throws TException {
		return "getUser:" + username + ",time:" + System.currentTimeMillis();
	}

	@Override
	public String updateUser(String username) throws TException {
		return "updateUser:" + username + ",time:" + System.currentTimeMillis();
	}

	@Override
	public String deleteUser(String username) throws TException {
		return "deleteUser:" + username + ",time:" + System.currentTimeMillis();
	}

}
