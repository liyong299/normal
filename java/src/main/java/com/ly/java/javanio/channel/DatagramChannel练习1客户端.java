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
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 功能描述：
 * <p color="red">
 * DatagramChannel的练习
 * </p>
 * 文件名称：DatagramChannel练习.java
 * 
 * @author ly
 */
public class DatagramChannel练习1客户端 {

    /**
     * 
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) {
	try {
	    send();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void send() throws IOException {
	DatagramChannel channel = DatagramChannel.open();
	ByteBuffer buffer = ByteBuffer.wrap("这个夏天不太热".getBytes("UTF-8"));
	channel.send(buffer, new InetSocketAddress("localhost", 8888));
	channel.close();
    }
}
