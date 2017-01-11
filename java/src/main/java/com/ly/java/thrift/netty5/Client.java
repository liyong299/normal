package com.ly.java.thrift.netty5;

import com.ly.java.thrift.inflectServer4.Iface;
import com.ly.java.thrift.inflectServer4.invoke_args;
import com.ly.java.thrift.inflectServer4.invoke_result;

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

	public String invoke(String uri, String request) throws org.apache.thrift.TException {
		send_invoke(uri, request);
		return recv_invoke();
	}

	public void send_invoke(String uri, String request) throws org.apache.thrift.TException {
		invoke_args args = new invoke_args();
		args.setUri(uri);
		args.setRequest(request);
		sendBase("invoke", args);
	}

	public String recv_invoke() throws org.apache.thrift.TException {
		invoke_result result = new invoke_result();
		receiveBase(result, "invoke");
		if (result.isSetSuccess()) {
			return result.success;
		}
		throw new org.apache.thrift.TApplicationException(
				org.apache.thrift.TApplicationException.MISSING_RESULT, "invoke failed: unknown result");
	}

}