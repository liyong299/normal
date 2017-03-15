package com.ly.java.thrift.compareServer;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

import com.ly.java.thrift.TNonblockingServer.HelloServerDemo;

public class HelloTHsHaServerDemo {

	public void startServer() {
		try {
			System.out.println("HelloWorld THsHaServer start ....");

			TProcessor tprocessor = new UserService.Processor<UserService.Iface>(new UserServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(Constant.SERVER_PORT);
			THsHaServer.Args thhsArgs = new THsHaServer.Args(tnbSocketTransport);
			thhsArgs.processor(tprocessor);
			thhsArgs.transportFactory(new TFramedTransport.Factory());
			thhsArgs.protocolFactory(new TBinaryProtocol.Factory());

			// 半同步半异步的服务模型
			TServer server = new THsHaServer(thhsArgs);
			System.out.println("HelloTHsHaServerDemo start....,端口：" + Constant.SERVER_PORT);
			server.serve();

		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServerDemo server = new HelloServerDemo();
		server.startServer();
	}

}