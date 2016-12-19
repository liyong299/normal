package com.ly.java.thrift.TThreadPoolServer;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.ly.java.thrift.HelloWorld;

/** 
 * 客户端调用HelloTSimpleServer,HelloTThreadPoolServer 
 * 阻塞 
 */
public class HelloClient {
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 8085;
	public static final int TIMEOUT = 30000;

	public void startClient(String userName) {
		// 设置传输通道  
		TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
		// 协议要和服务端一致  
		//使用二进制协议   
		TProtocol protocol = new TBinaryProtocol(transport);
		//创建Client  
		HelloWorld.Client client = new HelloWorld.Client(protocol);
		try {

			transport.open();
			String result = client.sayHello(userName);
			System.out.println(Thread.currentThread().getName() + "  result : " + result);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭资源  
			if (transport != null && transport.isOpen())
				transport.close();
		}

	}
}