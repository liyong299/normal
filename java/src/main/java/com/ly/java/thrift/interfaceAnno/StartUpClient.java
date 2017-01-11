/**
 * 项目名称：java
 * 文件包名：com.ly.java.thrift.interfaceAnno
 * 文件名称：Test2.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年1月3日 上午11:37:41
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thrift.interfaceAnno;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ly.java.thrift.anno.URIProcessor;
import com.ly.java.thrift.netty5.HelloWorldAction;
import com.ly.java.thrift.netty5.HttpChannelInitializer;
import com.ly.java.thrift.netty5.HttpServer;

/**
 * @功能描述：
 * @文件名称：Test2.java
 * @author ly
 */
public class StartUpClient {
	private static Log log = LogFactory.getLog(HttpServer.class);

	public static void main(String[] args) throws Exception {

		// URIProcessor.getIntance().process(HelloWorldServer.class);
		URIProcessor uriProcessor = new URIProcessor();
		uriProcessor.process(HelloWorldAction.class);
		com.ly.java.thrift.netty5.DispatchService.getInstance().setUriProcessor(uriProcessor);

		HttpServer server = new HttpServer();
		log.info("Http Server listening on 3344 ...");
		server.start(3344);
	}

	public void start(int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();

			HttpChannelInitializer channelHandler = new HttpChannelInitializer();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(channelHandler)
					.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture f = b.bind(port).sync();

			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
}
