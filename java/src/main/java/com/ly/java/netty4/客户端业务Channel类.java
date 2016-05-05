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

public class 客户端业务Channel类 extends ChannelInitializer<SocketChannel> {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
	log.debug("method【initChannel】  " + ch.toString());
        ChannelPipeline pipeline = ch.pipeline();

        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         * 
         * 解码和编码 我将会在下一章为大家详细的讲解。再此暂时不做详细的描述
         */
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
                Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // 客户端的逻辑
        pipeline.addLast("handler", new 客户端业务Handler类());
    }
}