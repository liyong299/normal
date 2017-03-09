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
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ly.java.netty4.longconnection.entries.BaseMsg;
import com.ly.java.netty4.longconnection.entries.PingMsg;
import com.ly.java.netty4.longconnection.entries.ResponseMsg;

/**
 * @功能描述：
 * @文件名称：ClientForStickHandler.java
 * @author ly
 */
public class ClientForLongConnectionHandler extends SimpleChannelInboundHandler<BaseMsg> {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private PingMsg pingMsg = null;

	public ClientForLongConnectionHandler(PingMsg pingMsg) {
		this.pingMsg = pingMsg;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
		log.debug("method【channelRead0】  " + "Server say : " + msg);
		switch (msg.getType()) {
		case PING: {
			log.debug(" reveice ping from server .....");
			break;
		}
		case REQUEST: {
			log.debug("请不要请求客户端，我不处理业务。");
			break;
		}
		case RESPONSE: {
			ResponseMsg respMsg = (ResponseMsg) msg;
			log.debug("服务端的应答信息： " + respMsg.getContent());
			break;
		}
		case LOGIN: {
			log.debug("receive client msg: " + msg);
			break;
		}
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
			case WRITER_IDLE: {
				//				ctx.writeAndFlush(pingMsg);
				log.debug("send ping to server ..... ");
				break;
			}
			default:
				break;
			}
		}
	}

	/**
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("发生异常：", cause);
	}

	/**
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().attr(AttributeKey.valueOf("clientId"));
	}
}
