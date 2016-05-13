/**
 * 项目名称：java
 * 文件包名：com.ly.java.mina
 * 文件名称：服务端代码.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月26日 下午2:41:28
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.mina;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：服务端代码.java
 * 
 * @author ly
 */
public class 服务端业务类 extends IoHandlerAdapter {

    // 连接异常时处理方法
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
	System.out.println("exceptionCaught method was called!");
	cause.printStackTrace();
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
	System.out.println("messageReceived method was called!");
	String str = message.toString();
	System.out.println("接收到的字符串：" + str);
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
