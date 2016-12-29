package com.ly.java.thrift.TSimpleServer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.ly.java.thrift.HelloWorld.Client;

/**
 * 客户端调用HelloTSimpleServer,HelloTThreadPoolServer 阻塞
 */
public class HelloClientReflact {
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 8083;
	public static final int TIMEOUT = 30000;

	public static void main(String[] args) throws Exception {
		System.out.println("HelloClientReflact start ..........");
		// 设置传输通道
		TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
		// 协议要和服务端一致
		// 使用二进制协议
		TProtocol protocol = new TBinaryProtocol(transport);
		transport.open();
		// 创建Client
		// HelloWorld.Client client = new HelloWorld.Client(protocol);
		// String result = client.sayHello("jack");
		Class cls = Client.class;

		Constructor constructor = cls.getConstructor(TProtocol.class);
		Object obj = constructor.newInstance(protocol);

		Method method = cls.getMethod("sayHello", String.class);
		Object result = method.invoke(obj, "jack");

		System.out.println("result : " + result);
		// 关闭资源
		transport.close();
	}
}