/**
 * 项目名称：java
 * 文件包名：com.ly.java.javanio.c07Ssocketchannel
 * 文件名称：服务器端代码.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月12日 上午11:55:17
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.javanio.c07Ssocketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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

    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
	serverSocketChannel.bind(new InetSocketAddress(9988));

	while (true) {
	    SocketChannel socketChannel = serverSocketChannel.accept();
	    if (socketChannel.isConnected()) {
		ByteBuffer readBuffer = ByteBuffer.allocate(2048);
		int count = -1;
		
		if ((count = socketChannel.read(readBuffer)) > 0) {
		    System.out.println(new String(readBuffer.array(), 0, count));
		} else {
		    socketChannel.close();
		}

		ByteBuffer sendBuffer = ByteBuffer.allocate(2048);
		sendBuffer.clear();
		sendBuffer.put("laiba,haizi!".getBytes());
		sendBuffer.flip();
		while (sendBuffer.hasRemaining())
		    socketChannel.write(sendBuffer);
	    }
	}

    }

}
