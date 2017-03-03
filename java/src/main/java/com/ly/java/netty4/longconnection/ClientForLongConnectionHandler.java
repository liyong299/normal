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

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ly.java.netty4.longconnection.entries.BaseMsg;
import com.ly.java.netty4.longconnection.entries.LoginMsg;
import com.ly.java.netty4.longconnection.entries.PingMsg;
import com.ly.java.netty4.longconnection.entries.ResponseMsg;

/**
 * @功能描述：
 * @文件名称：ClientForStickHandler.java
 * @author ly
 */
public class ClientForLongConnectionHandler extends SimpleChannelInboundHandler<BaseMsg> {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public ClientForLongConnectionHandler() {

	}
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
		log.debug("method【channelRead0】  " + "Server say : " + msg);
		messageReceived(ctx, msg);
	}

	private void messageReceived(ChannelHandlerContext ctx, BaseMsg msg) {
		switch (msg.getType()) {
		case LOGIN:
			LoginMsg loginMsg = new LoginMsg();
			loginMsg.setUserName("t1");
			loginMsg.setPassword("t2");
			ctx.writeAndFlush(loginMsg);
			break;
		case PING:
			System.out.println(" reveice ping from server .....");
			break;
		case REQUEST:
			//			ResponseMsg replyBody = new ResponseMsg().genDemo(msg);
			//			replyBody.setContent("reveice the server msg ");
			//			ctx.writeAndFlush(replyBody);
			System.out.println("请不要请求客户端，我不处理业务。");
			break;
		case RESPONSE:
			ResponseMsg respMsg = (ResponseMsg) msg;
			System.out.println("服务端的应答信息： " + respMsg.getContent());
			break;
		default:
			break;
		}
		ReferenceCountUtil.release(msg);
	}
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent statEvt = (IdleStateEvent) evt;
			switch (statEvt.state()) {
			case WRITER_IDLE:
				ctx.writeAndFlush(PingMsg.getInstance());
				System.out.println("send ping to server ..... ");
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.debug("method【channelActive】  " + "Client channelActive ");
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
