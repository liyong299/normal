/**
 * 项目名称：java
 * 文件包名：com.ly.java.thrift.netty2
 * 文件名称：HttpChannelHandler.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年1月5日 下午2:40:24
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thrift.netty2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @功能描述：
 * @文件名称：HttpChannelHandler.java
 * @author ly
 */
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {

	/**
	 * 任务分发服务
	 */
	private NettyDispatcherService nettyDispatcherService = new MyDispatcherService();

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline = ch.pipeline();

		// server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
		channelPipeline.addLast(new HttpResponseEncoder());
		// server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
		channelPipeline.addLast(new HttpRequestDecoder());

		/*
		* 压缩
		* Compresses an HttpMessage and an HttpContent in gzip or deflate encoding
		* while respecting the "Accept-Encoding" header.
		* If there is no matching encoding, no compression is done.
		*/
		channelPipeline.addLast("deflater", new HttpContentCompressor());
		/*
		 * 支持异步发送大的 码流（大文件等）
		 */
		channelPipeline.addLast("chunkedWriter", new ChunkedWriteHandler());

		/*
		 * 绑定消息处理器 
		 */
		channelPipeline.addLast("handler", new HttpSimpleChannelInboundHandler(nettyDispatcherService));

	}
}
