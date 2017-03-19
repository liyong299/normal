package com.ly.java.thrift.compareServer;

import org.apache.thrift.TException;

import com.ly.java.thrift.compareServer.UserService.Iface;

public class UserServiceImpl implements Iface {

	@Override
	public String addUser(String username) throws TException {
		// System.out.println("--------addUser---------");
		return "addUser:" + username + ",time:" + System.currentTimeMillis();
	}

	@Override
	public String getUser(String username) throws TException {
		// System.out.println("--------getUser---------");
		return "getUser:" + username + ",time:" + System.currentTimeMillis();
	}

	@Override
	public String updateUser(String username) throws TException {
		// System.out.println("--------updateUser---------");
		return "updateUser:" + username + ",time:" + System.currentTimeMillis();
	}

	@Override
	public String deleteUser(String username) throws TException {
		// System.out.println("--------deleteUser---------");
		return "deleteUser:" + username + ",time:" + System.currentTimeMillis();
	}

}
