package com.ly.java.thrift.inflectServer;

import com.ly.java.thrift.inflectServer.HelloWorld.Iface;

public class Client extends org.apache.thrift.TServiceClient implements Iface {
	public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
		public Factory() {}

		public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
			return new Client(prot);
		}

		public Client getClient(org.apache.thrift.protocol.TProtocol iprot,
				org.apache.thrift.protocol.TProtocol oprot) {
			return new Client(iprot, oprot);
		}
	}

	public Client(org.apache.thrift.protocol.TProtocol prot) {
		super(prot, prot);
	}

	public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
		super(iprot, oprot);
	}

	public String sayHello(String username) throws org.apache.thrift.TException {
		send_sayHello(username);
		return recv_sayHello();
	}

	public void send_sayHello(String username) throws org.apache.thrift.TException {
		sayHello_args args = new sayHello_args();
		args.setUsername(username);
		sendBase("sayHello", args);
	}

	public String recv_sayHello() throws org.apache.thrift.TException {
		sayHello_result result = new sayHello_result();
		receiveBase(result, "sayHello");
		if (result.isSetSuccess()) {
			return result.success;
		}
		throw new org.apache.thrift.TApplicationException(
				org.apache.thrift.TApplicationException.MISSING_RESULT, "sayHello failed: unknown result");
	}

}