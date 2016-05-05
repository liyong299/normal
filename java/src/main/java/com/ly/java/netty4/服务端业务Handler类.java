package com.ly.java.netty4;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class 服务端业务Handler类 extends SimpleChannelInboundHandler<String> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        // 收到消息直接打印输出
        log.debug("method【channelRead0】  " + ctx.channel().remoteAddress() + " Say : " + msg);

        // 返回客户端消息 - 我已经接收到了你的消息
        ctx.writeAndFlush("Received your message ! [" + msg + "] \n");
    }

    /*
     * 
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * 
     * channelActive 和 channelInActive 在后面的内容中讲述，这里先不做详细的描述
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.debug("method【channelActive】  RamoteAddress : " + ctx.channel().remoteAddress()
                + " active !");

        ctx.writeAndFlush("Welcome to "
                + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}