package com.ly.java.thrift.inflectServer4;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/** 
 * 客户端调用HelloTSimpleServer,HelloTThreadPoolServer 
 * 阻塞 
 */
public class Client_Invoke {
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 8085;
	public static final int TIMEOUT = 30000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client_Invoke client = new Client_Invoke();
		client.startClient("Michael");

	}

	public void startClient(String userName) {
		// 设置传输通道  
		TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
		// 协议要和服务端一致  
		//使用二进制协议   
		TProtocol protocol = new TBinaryProtocol(transport);
		//创建Client  
		Client client = new Client(protocol);
		try {

			transport.open();
			String request = userName;
			String uri = "/demo/order/add";
			String result = client.invoke(uri, request);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭资源  
			if (transport != null && transport.isOpen())
				transport.close();
		}
	}
}