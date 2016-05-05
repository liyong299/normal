/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.mina
 * �ļ����ƣ�����˴���2.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��4��26�� ����2:59:50
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.example.gettingstarted.timeserver.TimeServerHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * ����������
 * <p color="red">
 * ʵ�ּ���
 * </p>
 * �ļ����ƣ�����˴���2.java
 * 
 * @author ly
 */
public class ����˴��� {

    private static final int PORT = 9123;

    public static void main(String[] args) throws IOException {
	// ��������������
	IoAcceptor acceptor = new NioSocketAcceptor();

	// ������־������
	acceptor.getFilterChain().addLast("logger", new LoggingFilter());
	
	// ���ӱ��������
	acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

	// ָ��ҵ���߼�������
	acceptor.setHandler(new �����ҵ����());
	
	// ����buffer�ĳ���
	acceptor.getSessionConfig().setReadBufferSize(2048);
	
	// �������ӳ�ʱʱ��
	acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

	// �󶨶˿�
	acceptor.bind(new InetSocketAddress(PORT));
    }
}
