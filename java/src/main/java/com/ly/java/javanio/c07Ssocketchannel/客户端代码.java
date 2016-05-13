/**
 * 项目名称：java
 * 文件包名：com.ly.java.javanio.c07Ssocketchannel
 * 文件名称：客户端代码.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月12日 上午11:41:17
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.javanio.c07Ssocketchannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：客户端代码.java
 * @author ly
 */
public class 客户端代码 {

    /**
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
	SocketChannel socketChannel = SocketChannel.open();
	socketChannel.connect(new InetSocketAddress("localhost", 9988));
//	socketChannel.configureBlocking(false);  // 非阻塞式不能同一个连接中既读取又写入
	
	System.out.println(socketChannel.isConnected());
	if (socketChannel.isConnected())
	{
	    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
	    writeBuffer.clear();
	    writeBuffer.put("太阳真美！".getBytes());
	    writeBuffer.flip();
	    while (writeBuffer.hasRemaining())
	        socketChannel.write(writeBuffer);
	    
	    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
	    
	    int count = socketChannel.read(readBuffer);
	    System.out.println(new String(readBuffer.array(), 0, count));	    
	    socketChannel.close();
	}
    }

}
