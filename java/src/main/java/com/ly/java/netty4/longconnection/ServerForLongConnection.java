/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.stickpack
 * 文件名称：ServerForStick.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年2月28日 上午11:27:24
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.longconnection;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @功能描述：
 * @文件名称：ServerForStick.java
 * @author ly
 */
public class ServerForLongConnection {
	private final static Logger log = LoggerFactory.getLogger(ServerForLongConnection.class);

	public static void main(String[] args) throws InterruptedException {
		ServerForLongConnection server = new ServerForLongConnection();
		server.bind();
	}

	private ScheduledExecutorService scheduleService = Executors.newScheduledThreadPool(Constants.CLIENT_NUM);

	private void bind() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.option(ChannelOption.SO_BACKLOG, 128);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new LoggingHandler(LogLevel.DEBUG));

			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
				log.debug("method【initChannel】  " + ch.toString());
				
					ChannelPipeline pipeline = ch.pipeline();

					pipeline.addLast(new ObjectEncoder());
					pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
					pipeline.addLast(new ServerForLongConnectionHandler());
				}
			});

			// 服务器绑定端口监听
			ChannelFuture f = b.bind(Constants.HTTP_PORT).sync();

			if (f.isSuccess()) {
				log.debug("server start---------------");
			}

			checkClient();
			// 监听服务器关闭监听,关闭之后，服务端不能实时检测客户端是否连接，无法接受客户端请求
			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	/**
	 * 发送心跳请求，检测客户端是否正常。
	 */
	private void checkClient() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// TODO 异步定时发消息不能成功为什么？
		Map<String, SocketChannel> pool = SocketChannelPool.getInstance().getPool();
		for (Map.Entry<String, SocketChannel> entry : pool.entrySet()) {
			scheduleService.scheduleAtFixedRate(new ServerCheckClientTask(entry.getValue()), 3, 5,
					TimeUnit.SECONDS);
		}
	}
}
