package com.ly.java.netty4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class 客户端业务Handler类 extends SimpleChannelInboundHandler<String> {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        
        log.debug("method【channelRead0】  " + "Server say : " + msg);
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("method【channelActive】  " + "Client channelActive ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.debug("method【channelInactive】  " + "Client close ");
        super.channelInactive(ctx);
    }
}