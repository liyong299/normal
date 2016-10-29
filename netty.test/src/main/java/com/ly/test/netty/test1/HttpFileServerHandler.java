package com.ly.test.netty.test1;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * 模拟服务端处理器
 * 
 * @author lh
 * @date 2015-08-11 14:33
 * @version 1.0
 * 
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
	System.out.println("messageReceived method was called!");
	if (!request.getDecoderResult().isSuccess()) {
	    sendError(ctx, BAD_REQUEST);
	    return;
	}

	ByteBuf buf = request.content();
	byte[] req = new byte[buf.readableBytes()];
	buf.readBytes(req);
	String xml = new String(req, "UTF-8");
	resp(ctx, xml);
    }

    /**
     * 
     * @param xml
     */
    private void resp(ChannelHandlerContext ctx, String xml) {
	String transCode = Dom4JUtil.header(xml, "transcode");
	String retUrl = "NewFile.xml";
	String retCtt = null;
	if (equal(transCode, Constants.TC_DZZH)) {// 电子账户

	    retUrl = "resp.xml";

	} else if (equal(transCode, Constants.TC_YHKBD)) {// 银行卡绑定

	}

	try {
	    retCtt = FileUtils.readFileToString(new File(retUrl));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.FOUND, Unpooled.copiedBuffer(retCtt, CharsetUtil.UTF_8));
	response.headers().set(CONTENT_TYPE, "text/xml; charset=UTF-8");
	ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

    public static boolean equal(String var, String cons) {
	return isNotEmpty(var) && cons.equals(var);
    }

    private static boolean isNotEmpty(String s) {
	return (null != s && !"".equals(s));
    }

    /**
     * 错误处理
     * 
     * @param ctx
     * @param status
     */
    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
	String ret = null;
	FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, Unpooled.copiedBuffer(ret, CharsetUtil.UTF_8));
	response.headers().set(CONTENT_TYPE, "text/xml; charset=UTF-8");
	ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	System.out.println("exceptionCaught method was called!");
	ctx.close();
	cause.printStackTrace();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext arg0, FullHttpRequest arg1) throws Exception {
	System.out.println("channelRead0 method was called!");
    }

}
