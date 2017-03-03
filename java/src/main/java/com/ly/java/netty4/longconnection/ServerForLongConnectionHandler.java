package com.ly.java.netty4.longconnection;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ly.java.netty4.longconnection.entries.BaseMsg;
import com.ly.java.netty4.longconnection.entries.LoginMsg;
import com.ly.java.netty4.longconnection.entries.MsgType;
import com.ly.java.netty4.longconnection.entries.PingMsg;
import com.ly.java.netty4.longconnection.entries.RequestMsg;
import com.ly.java.netty4.longconnection.entries.ResponseMsg;

public class ServerForLongConnectionHandler extends SimpleChannelInboundHandler<BaseMsg> {
    private final Logger log = LoggerFactory.getLogger(getClass());

	private SocketChannelPool pool = SocketChannelPool.getInstance();
    /*
     * 
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * 
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("method【channelActive】  RamoteAddress : " + ctx.channel().remoteAddress()
                + " active !");
		ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");
    }

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		//channel失效，从Map中移除
		pool.remove((SocketChannel) ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
		messageReceived(ctx, msg);
	}

	protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg)
			throws Exception {

		if (MsgType.LOGIN.equals(baseMsg.getType())) {
			LoginMsg loginMsg = (LoginMsg) baseMsg;
			if ("lilei".equals(loginMsg.getUserName()) && "123456".equals(loginMsg.getPassword())) {
				//登录成功,把channel存到服务端的map中
				pool.add(loginMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
				System.out.println("client" + loginMsg.getClientId() + " 登录成功");
			}
		} else {
			if (pool.get(baseMsg.getClientId()) == null) {
				//说明未登录，或者连接断了，服务器向客户端发起登录请求，让客户端重新登录
				LoginMsg loginMsg = new LoginMsg();
				channelHandlerContext.channel().writeAndFlush(loginMsg);
			}
		}
		switch (baseMsg.getType()) {
		case PING: {
			PingMsg pingMsg = (PingMsg) baseMsg;
			pool.get(pingMsg.getClientId()).writeAndFlush(PingMsg.getInstance());
		}
			break;
		case REQUEST: {
			//收到客户端的请求
			RequestMsg reqMsg = (RequestMsg) baseMsg;
			if ("time".equals(reqMsg.getParams().get("command"))) {
				ResponseMsg replyBody = new ResponseMsg().genDemo(reqMsg);
				replyBody.setContent("current time is : " + System.currentTimeMillis());
				pool.get(reqMsg.getClientId()).writeAndFlush(replyBody);
			}
		}
			break;
		case RESPONSE: {
			//收到客户端回复
			ResponseMsg replyMsg = (ResponseMsg) baseMsg;
			System.out.println("receive client msg: " + replyMsg.getContent());
		}
			break;
		default:
			break;
		}
		ReferenceCountUtil.release(baseMsg);
	}
}