/**
 * 项目名称：java
 * 文件包名：com.ly.java.mina
 * 文件名称：服务端代码2.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月26日 下午2:59:50
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
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
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：服务端代码2.java
 * 
 * @author ly
 */
public class 服务端代码 {

    private static final int PORT = 9123;

    public static void main(String[] args) throws IOException {
	// 创建服务器监听
	IoAcceptor acceptor = new NioSocketAcceptor();

	// 增加日志过滤器
	acceptor.getFilterChain().addLast("logger", new LoggingFilter());
	
	// 增加编码过滤器
	acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

	// 指定业务逻辑处理器
	acceptor.setHandler(new 服务端业务类());
	
	// 设置buffer的长度
	acceptor.getSessionConfig().setReadBufferSize(2048);
	
	// 设置连接超时时间
	acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

	// 绑定端口
	acceptor.bind(new InetSocketAddress(PORT));
    }
}
