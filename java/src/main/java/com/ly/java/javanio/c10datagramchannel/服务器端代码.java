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
public class 服务器端代码 {

    public static void main(String[] args) throws IOException {
	DatagramChannel server = DatagramChannel.open();
	server.bind(new InetSocketAddress(7777));

	new ReadWorker(server).start();
	// new SendWorker(new InetSocketAddress("localhost", 8888),
	// server).start();
    }
}

class ReadWorker extends Thread { // 接收到数据之后，立即返回。
    public DatagramChannel server;

    public ReadWorker(DatagramChannel server) {
	this.server = server;
    }

    public void run() {
	ByteBuffer readBuffer = ByteBuffer.allocate(2048);
	ByteBuffer sendBuffer = ByteBuffer.allocate(2048);
	int cnt = 0;
	while (true) {
	    readBuffer.clear();
	    StringBuilder content = new StringBuilder();
	    try {
		SocketAddress client = server.receive(readBuffer);
		content.append(new String(readBuffer.array()));
		System.out.println(content);

		// Thread.currentThread().sleep(1000l);
		sendBuffer.clear();
		sendBuffer.put(("来自服务端当前次数： " + cnt++).getBytes());
		sendBuffer.flip();
		try {
		    this.server.send(sendBuffer, client);
		    Thread.currentThread().sleep(1000l);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }
}

class SendWorker extends Thread {
    public SocketAddress client;
    public DatagramChannel server;

    public SendWorker(SocketAddress client, DatagramChannel server) {
	this.client = client;
	this.server = server;
    }

    public void run() {
	ByteBuffer sendBuffer = ByteBuffer.allocate(2048);
	int cnt = 0;
	while (true) {
	    sendBuffer.clear();
	    sendBuffer.put(("当前次数： " + cnt++).getBytes());
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
