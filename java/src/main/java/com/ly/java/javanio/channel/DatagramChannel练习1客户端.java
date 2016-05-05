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
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * ����������
 * <p color="red">
 * DatagramChannel����ϰ
 * </p>
 * �ļ����ƣ�DatagramChannel��ϰ.java
 * 
 * @author ly
 */
public class DatagramChannel��ϰ1�ͻ��� {

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
	ByteBuffer buffer = ByteBuffer.wrap("������첻̫��".getBytes("UTF-8"));
	channel.send(buffer, new InetSocketAddress("localhost", 8888));
	channel.close();
    }
}
