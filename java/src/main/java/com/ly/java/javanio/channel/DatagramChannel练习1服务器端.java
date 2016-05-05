/**
 * 项目名称：java
 * 文件包名：com.ly.java.javanio.channel
 * 文件名称：DatagramChannel练习.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月5日 下午7:53:50
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.javanio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

/**
 * 功能描述：
 * <p color="red">
 * DatagramChannel的练习
 * </p>
 * 文件名称：DatagramChannel练习.java
 * 
 * @author ly
 */
public class DatagramChannel练习1服务器端 {

    public static void main(String[] args) {
	try {
	    receive();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void receive() throws IOException {
	DatagramChannel channel = DatagramChannel.open();
	channel.socket().bind(new InetSocketAddress(8888));
	ByteBuffer buffer = ByteBuffer.allocate(100);
	while (channel.receive(buffer) == null) {
	    try {
		Thread.sleep(2000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	buffer.flip();
	String recStr = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
	System.out.println(recStr);
	channel.close();
    }
}
