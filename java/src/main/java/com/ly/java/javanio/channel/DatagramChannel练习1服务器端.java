/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.javanio.channel
 * �ļ����ƣ�DatagramChannel��ϰ.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��5��5�� ����7:53:50
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.javanio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

/**
 * ����������
 * <p color="red">
 * DatagramChannel����ϰ
 * </p>
 * �ļ����ƣ�DatagramChannel��ϰ.java
 * 
 * @author ly
 */
public class DatagramChannel��ϰ1�������� {

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
