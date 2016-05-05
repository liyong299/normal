/**
 * 项目名称：java
 * 文件包名：com.ly.java.mina
 * 文件名称：客户端代码.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月26日 下午2:41:18
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
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
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：客户端代码.java
 * 
 * @author ly
 */
public class 客户端代码 {

    public static void main(String[] args) {
	// 创建客户端连接器.
	NioSocketConnector connector = new NioSocketConnector();
	connector.getFilterChain().addLast("logger", new LoggingFilter());
	connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8")))); // 设置编码过滤器
	connector.setConnectTimeout(30);
	connector.setHandler(new 客户端业务类());// 设置事件处理器
	ConnectFuture cf = connector.connect(new InetSocketAddress("127.0.0.1", 9123));// 建立连接
	cf.awaitUninterruptibly();// 等待连接创建完成
	cf.getSession().write("hello");// 发送消息
	cf.getSession().write("quit");// 发送消息
	cf.getSession().getCloseFuture().awaitUninterruptibly();// 等待连接断开
	connector.dispose();
    }
}
