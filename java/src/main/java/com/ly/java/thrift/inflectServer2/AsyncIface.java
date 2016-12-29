package com.ly.java.thrift.inflectServer2;

public interface AsyncIface {

	public void sayHello(String username, org.apache.thrift.async.AsyncMethodCallback resultHandler)
			throws org.apache.thrift.TException;

}