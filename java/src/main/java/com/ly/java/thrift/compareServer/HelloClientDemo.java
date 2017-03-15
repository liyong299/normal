package com.ly.java.thrift.compareServer;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.ly.java.thrift.HelloWorld;

public class HelloClientDemo {

	public static final String SERVER_IP = Constant.SERVER_ADDR;
	public static final int SERVER_PORT = Constant.SERVER_PORT;
	public static final int TIMEOUT = 30000;


	/**
	 *
	 * @param userName
	 */
	public String startClient(String userName) {
		TTransport transport = null;
		try {
			System.out.println(" 开始请求：  " + System.currentTimeMillis());
			transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT));
			// 协议要和服务端一致
			TProtocol protocol = new TCompactProtocol(transport);
			HelloWorld.Client client = new HelloWorld.Client(protocol);
			transport.open();
			String result = client.sayHello(userName);
			System.out.println("Thrify client result =: " + result);
			return result;
		} catch (TException e) {
			e.printStackTrace();
			return "";

		} finally {
			if (null != transport) {
				transport.close();
			}
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