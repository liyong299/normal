package com.ly.java.thrift.inflectServer;

import com.ly.java.thrift.inflectServer.HelloWorld.Iface;

public class sayHello<I extends Iface> extends org.apache.thrift.ProcessFunction<I, sayHello_args> {
	public sayHello() {
		super("sayHello");
	}

	public sayHello_args getEmptyArgsInstance() {
		return new sayHello_args();
	}

	protected boolean isOneway() {
		return false;
}

	public sayHello_result getResult(I iface, sayHello_args args) throws org.apache.thrift.TException {
		sayHello_result result = new sayHello_result();
		result.success = iface.sayHello(args.username);
		return result;
	}
}