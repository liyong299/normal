package com.ly.java.javanio.c10datagramchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：服务器端代码.java
 * 
 * @author ly
 */
public class 客户端端代码 {

    public static void main(String[] args) throws IOException {
	DatagramChannel server = DatagramChannel.open();
	server.bind(new InetSocketAddress(8888));

	// 用两个线程分别负责接收和发送
	new ClientReadWorker(server).start();
	new ClientSendWorker(new InetSocketAddress("localhost", 7777), server).start();
    }
}

class ClientReadWorker extends Thread {
    public DatagramChannel server;

    public ClientReadWorker(DatagramChannel server) {
	this.server = server;
    }

    public void run() {
	ByteBuffer readBuffer = ByteBuffer.allocate(2048);
	while (true) {
	    readBuffer.clear();
	    StringBuilder content = new StringBuilder();
	    try {
		SocketAddress client = server.receive(readBuffer);
		content.append(new String(readBuffer.array()));
		System.out.println(content);

		Thread.currentThread().sleep(1000l);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }
}

class ClientSendWorker extends Thread {
    public SocketAddress client;
    public DatagramChannel server;

    public ClientSendWorker(SocketAddress client, DatagramChannel server) {
	this.client = client;
	this.server = server;
    }

    public void run() {
	ByteBuffer sendBuffer = ByteBuffer.allocate(2048);
	int cnt = 0;
	while (true) {
	    sendBuffer.clear();
	    sendBuffer.put(("来自客户端当前次数： " + cnt++).getBytes());
	    sendBuffer.flip();
	    try {
		this.server.send(sendBuffer, client);
		Thread.currentThread().sleep(1000l);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }
}
