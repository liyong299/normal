package com.ly.java.netty4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class �����ҵ��Channel�� extends ChannelInitializer<SocketChannel> {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
	log.debug("method��initChannel��  " + ch.toString());
	
        ChannelPipeline pipeline = ch.pipeline();

        // ��("\n")Ϊ��β�ָ�� ������
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
                Delimiters.lineDelimiter()));

        // �ַ������� �� ����
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // �Լ����߼�Handler
        pipeline.addLast("handler", new �����ҵ��Handler��());
    }
}