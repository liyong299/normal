package com.ly.java.netty4.longconnection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ly.java.netty4.stickpack.ClientForStick;

public class ClientForLongConnection {
	public static String host = "127.0.0.1";
	public static int port = 7878;
	private final static Logger log = LoggerFactory.getLogger(ClientForStick.class);
	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					log.debug("method【initChannel】  " + ch.toString());
					ChannelPipeline pipeline = ch.pipeline();

					/*
					 * 这个地方的 必须和服务端对应上。否则无法正常解码和编码  
					 * 
					 * 解码和编码 我将会在下一章为大家详细的讲解。再此暂时不做详细的描述
					 */
					pipeline.addLast("framer",
							new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter())); //不增加这个解码器 会导致粘包的问题
					pipeline.addLast("decoder", new StringDecoder());
					pipeline.addLast("encoder", new StringEncoder());

					// 客户端的逻辑
					pipeline.addLast("handler", new ClientForLongConnectionHandler());
				}
			});

			// 连接服务端
			Channel ch = b.connect(host, port).sync().channel();

			ch.closeFuture().sync();
		} finally {
			// The connection is closed automatically on shutdown.
			group.shutdownGracefully();
		}
	}
}
