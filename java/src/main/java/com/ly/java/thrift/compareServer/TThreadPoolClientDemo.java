package com.ly.java.thrift.compareServer;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class TThreadPoolClientDemo {

	public static final String SERVER_IP = Constant.SERVER_ADDR;
	public static final int SERVER_PORT = Constant.SERVER_PORT;
	public static final int TIMEOUT = 3000;

	private static AtomicInteger openConnCnt = new AtomicInteger(0);

	public static void main(String[] args) {

		TThreadPoolClientDemo client = new TThreadPoolClientDemo();
		String aa = client.startClient("Michael");
	}

	/**
	 * 
	 * @param userName
	 */
	public String startClient(String userName) {
		TTransport transport = null;
		// 设置传输通道
		transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
		// 协议要和服务端一致
		// 使用二进制协议
		TProtocol protocol = new TBinaryProtocol(transport);
		// 创建Client
		UserService.Client client = new UserService.Client(protocol);
		try {

			transport.open();
			openConnCnt.incrementAndGet();
			String result = client.getUser(userName);
			//			System.out.println(Thread.currentThread().getName() + "  result : " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "--";
		} finally {
			// 关闭资源
			if (transport != null && transport.isOpen()) {
				transport.close();
				openConnCnt.decrementAndGet();
			}
			System.out.println("连接数： " + openConnCnt.intValue());
		}
	}

}