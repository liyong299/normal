/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.stickpack
 * 文件名称：ServerForStick.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年2月28日 上午11:27:24
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.stickpack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @功能描述：
 * @文件名称：ServerForStick.java
 * @author ly
 */
public class ServerForStick {
	private final static Logger log = LoggerFactory.getLogger(ServerForStick.class);
	/**
	 * 服务端监听的端口地址
	 */
	private static final int portNumber = 7878;

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
				log.debug("method【initChannel】  " + ch.toString());
				
					ChannelPipeline pipeline = ch.pipeline();

					// 以("\n")为结尾分割的 解码器   不增加这个解码器 会导致粘包的问题
					//					pipeline.addLast("framer",
					//							new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

					// 字符串解码 和 编码
					pipeline.addLast("decoder", new StringDecoder());
					pipeline.addLast("encoder", new StringEncoder());

					// 自己的逻辑Handler
					pipeline.addLast("handler", new ServerForStickHandler());
				}

			});

			// 服务器绑定端口监听
			ChannelFuture f = b.bind(portNumber).sync();
			// 监听服务器关闭监听
			f.channel().closeFuture().sync();

			// 可以简写为
			/* b.bind(portNumber).sync().channel().closeFuture().sync(); */
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
