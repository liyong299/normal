package com.ly.java.thrift.compareServer;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class HelloClientDemo {

	public static final String SERVER_IP = Constant.SERVER_ADDR;
	public static final int SERVER_PORT = Constant.SERVER_PORT;
	public static final int TIMEOUT = 3000;

	/**
	 * 
	 * @param userName
	 */
	public String startClient(String userName) {
		// 设置传输通道
		TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
		// 协议要和服务端一致
		// 使用二进制协议
		TProtocol protocol = new TBinaryProtocol(transport);
		// 创建Client
		UserService.Client client = new UserService.Client(protocol);
		try {

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloClientDemo client = new HelloClientDemo();
		String aa = client.startClient("Michael");

	}

}