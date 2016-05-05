/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.mina
 * �ļ����ƣ��ͻ��˴���.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��4��26�� ����2:41:18
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * ����������
 * <p color="red">
 * ʵ�ּ���
 * </p>
 * �ļ����ƣ��ͻ��˴���.java
 * 
 * @author ly
 */
public class �ͻ��˴��� {

    public static void main(String[] args) {
	// �����ͻ���������.
	NioSocketConnector connector = new NioSocketConnector();
	connector.getFilterChain().addLast("logger", new LoggingFilter());
	connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); // ���ñ��������
	connector.setConnectTimeout(30);
	connector.setHandler(new �ͻ���ҵ����());// �����¼�������
	ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9123));// ��������
	cf.awaitUninterruptibly();// �ȴ����Ӵ������
	cf.getSession().write("hello");// ������Ϣ
	cf.getSession().write("quit");// ������Ϣ
	cf.getSession().getCloseFuture().awaitUninterruptibly();// �ȴ����ӶϿ�
	connector.dispose();
    }
}
