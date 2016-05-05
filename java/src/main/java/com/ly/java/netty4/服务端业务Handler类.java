package com.ly.java.netty4;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class �����ҵ��Handler�� extends SimpleChannelInboundHandler<String> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        // �յ���Ϣֱ�Ӵ�ӡ���
        log.debug("method��channelRead0��  " + ctx.channel().remoteAddress() + " Say : " + msg);

        // ���ؿͻ�����Ϣ - ���Ѿ����յ��������Ϣ
        ctx.writeAndFlush("Received your message ! [" + msg + "] \n");
    }

    /*
     * 
     * ���� channelActive ���� ��channel�����õ�ʱ�򴥷� (�ڽ������ӵ�ʱ��)
     * 
     * channelActive �� channelInActive �ں���������н����������Ȳ�����ϸ������
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        log.debug("method��channelActive��  RamoteAddress : " + ctx.channel().remoteAddress()
                + " active !");

        ctx.writeAndFlush("Welcome to "
                + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}