package com.ly.java.netty4.longconnection;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ly.java.netty4.longconnection.entries.BaseMsg;
import com.ly.java.netty4.longconnection.entries.LoginMsg;
import com.ly.java.netty4.longconnection.entries.MsgType;
import com.ly.java.netty4.longconnection.entries.RequestMsg;
import com.ly.java.netty4.longconnection.entries.ResponseMsg;

public class ServerForLongConnectionHandler extends SimpleChannelInboundHandler<BaseMsg> {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private SocketChannelPool pool = SocketChannelPool.getInstance();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, BaseMsg baseMsg) throws Exception {
		log.debug("服务端接受到消息，消息内容： " + baseMsg.getType());

		if (MsgType.LOGIN.equals(baseMsg.getType())) {
			LoginMsg loginMsg = (LoginMsg) baseMsg;
			if ("lilei".equals(loginMsg.getUserName()) && "123456".equals(loginMsg.getPassword())) {
				//登录成功,把channel存到服务端的map中
				pool.add(loginMsg.getClientId(), (SocketChannel) ctx.channel());
				log.debug("client " + loginMsg.getClientId() + " 登录成功");
			}
		} else {
			if (baseMsg.getClientId() != null)
			if (pool.get(baseMsg.getClientId()) == null) {
				//说明未登录，或者连接断了，服务器向客户端发起登录请求，让客户端重新登录
				LoginMsg loginMsg = new LoginMsg();
				ctx.channel().writeAndFlush(loginMsg);
			}
		}

		switch (baseMsg.getType()) {
		case REQUEST: {
			RequestMsg reqMsg = (RequestMsg) baseMsg;
			if ("time".equals(reqMsg.getParams().get("command"))) {
				ResponseMsg replyBody = new ResponseMsg().genDemo(reqMsg);
				replyBody.setContent("current time is : " + System.currentTimeMillis());
				pool.get(reqMsg.getClientId()).writeAndFlush(replyBody);
			} else {
				ResponseMsg replyBody = new ResponseMsg().genDemo(reqMsg);
				replyBody.setContent(" 请求内容有误，请重新发送，谢谢！ ");
				pool.get(reqMsg.getClientId()).writeAndFlush(replyBody);
			}
			break;
		}
		case PING: {
			log.debug("客户端不需要发ping消息给服务端: " + baseMsg.getClientId());
			break;
		}
		case RESPONSE: {
			ResponseMsg replyMsg = (ResponseMsg) baseMsg;
			log.debug("receive client msg: " + replyMsg.getContent());
			break;
		}
		case LOGIN: {
			log.debug("receive client msg: " + baseMsg);
			break;
		}
		default:
			break;
		}
		ReferenceCountUtil.release(baseMsg);
	}

	/**
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("发生异常：", cause);
	}

}