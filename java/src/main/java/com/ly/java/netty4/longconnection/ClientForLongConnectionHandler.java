/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.stickpack
 * 文件名称：ClientForStickHandler.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年2月28日 上午10:55:58
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.longconnection;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @功能描述：
 * @文件名称：ClientForStickHandler.java
 * @author ly
 */
public class ClientForLongConnectionHandler extends SimpleChannelInboundHandler<String> {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private int counter;
	private byte[] req;

	public ClientForLongConnectionHandler() {
		//这里介绍一下System.getProperty("line.separator") // 直线分隔符  
		//		req = ("Query Time Order" + System.getProperty("line.separator")).getBytes();
		req = ("Query Time Order").getBytes();
	}
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

		log.debug("method【channelRead0】  " + "Server say : " + msg);
		/**
		 *  客户端会记录服务器发过来的消息数量，我们预期应改收到100条数据。
		 *  但是实际上客户端只收到1条数据，这很正常，因为我们的服务器端只返回了2条数据，
		 *  只所以客户端只收到1条数据，是因为服务器发过来的2条数据被粘包了。
		 */
		System.out.println("Now is:" + msg + "; the counter is:" + (++counter));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.debug("method【channelActive】  " + "Client channelActive ");
		ByteBuf message = null;
		//客户端发送了100次数据,理论上服务器端应该收到100条数据。但实际上服务器只收到2条，很明显发生了粘包。
		for (int i = 0; i < 100; i++) {
			if (i % 10 == 9) {
				req = ("Query Time Order" + System.getProperty("line.separator")).getBytes();
			} else {
				req = ("Query Time Order").getBytes();
			}
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.debug("method【channelInactive】  " + "Client close ");
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.warn("Unexpected exception from downstream:" + cause.getMessage());
	}
}
