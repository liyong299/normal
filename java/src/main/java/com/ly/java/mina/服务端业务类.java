/**
 * ��Ŀ���ƣ�java
 * �ļ�������com.ly.java.mina
 * �ļ����ƣ�����˴���.java
 * �汾��Ϣ��SCEC_Branches
 * �������ڣ�2016��4��26�� ����2:41:28
 * Copyright (c) 2015-2015������̩����Ϣϵͳ�ɷ����޹�˾
 * 
 */
package com.ly.java.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * ����������
 * <p color="red">
 * ʵ�ּ���
 * </p>
 * �ļ����ƣ�����˴���.java
 * 
 * @author ly
 */
public class �����ҵ���� extends IoHandlerAdapter {

    // �����쳣ʱ������
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	System.out.println("exceptionCaught method was called!");
	cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
	System.out.println("messageReceived method was called!");
	String str = message.toString();
	System.out.println("���յ����ַ�����" + str);
	if (str.trim().equalsIgnoreCase("quit")) {
//	    session.close(true);
	    session.closeNow();
	    return;
	}
	Date date = new Date();
	session.write(date.toString());
	System.out.println("Message written...");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	System.out.println("sessionIdle method was called!");
	System.out.println("IDLE " + session.getIdleCount(status));
    }

}
