package com.ly.java.thrift.compareServer;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * 使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式 TNonblockingServer 服务模型
 * 
 * @author ly
 * 
 */
public class TThreadedSelectorServerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TThreadedSelectorServerDemo server = new TThreadedSelectorServerDemo();
		server.startServer();
	}

	public void startServer() {
		try {
			TProcessor tprocessor = new UserService.Processor<UserService.Iface>(new UserServiceImpl());

			TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(Constant.SERVER_PORT);

			TThreadedSelectorServer.Args tnbArgs = new TThreadedSelectorServer.Args(tnbSocketTransport);

			tnbArgs.processor(tprocessor);
			// tnbArgs.transportFactory(new TFramedTransport.Factory());
			tnbArgs.protocolFactory(new TCompactProtocol.Factory());

			TServer server = new TNonblockingServer(tnbArgs);
			System.out.println("TThreadedSelectorServerDemo start....,端口：" + Constant.SERVER_PORT);
			server.serve();
		} catch (Exception e) {
			System.out.println("Server start error!!!");
			e.printStackTrace();
		}
	}

}