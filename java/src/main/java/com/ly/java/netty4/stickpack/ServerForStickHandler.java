package com.ly.java.netty4.stickpack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerForStickHandler extends SimpleChannelInboundHandler<String> {
    private final Logger log = LoggerFactory.getLogger(getClass());
	private AtomicInteger counter = new AtomicInteger(0);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
		counter.getAndIncrement();

		// 收到消息直接打印输出
		log.debug("method【channelRead0】  " + ctx.channel().remoteAddress() + " Say : " + msg + counter);


        // 返回客户端消息 - 我已经接收到了你的消息
		ctx.writeAndFlush("Received your message ! [" + msg + "] " + counter
				+ System.getProperty("line.separator"));
    }

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

}