package com.ly.java.thrift.inflectServer4;

public interface AsyncIface {

	public void invoke(String uri, String request, org.apache.thrift.async.AsyncMethodCallback resultHandler)
			throws org.apache.thrift.TException;

}