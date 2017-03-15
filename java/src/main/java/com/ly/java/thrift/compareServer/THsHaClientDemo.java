package com.ly.java.thrift.compareServer;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class THsHaClientDemo {

	public static final String SERVER_IP = Constant.SERVER_ADDR;
	public static final int SERVER_PORT = Constant.SERVER_PORT;
	public static final int TIMEOUT = 3000;

	public static void main(String[] args) {
		THsHaClientDemo client = new THsHaClientDemo();
		String aa = client.startClient("Michael");
	}

	public String startClient(String userName) {
		// 设置传输通道
		TTransport transport = null;
		try {
			// 协议要和服务端一致
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			TProtocol protocol = new TBinaryProtocol(transport);

			// 创建Client
			UserService.Client client = new UserService.Client(protocol);
			transport.open();
			String result = client.getUser(userName);
			System.out.println(Thread.currentThread().getName() + "  result : " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			// 关闭资源
			if (transport != null && transport.isOpen())
				transport.close();
		}
	}
}