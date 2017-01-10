package com.ly.java.thrift.netty5;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.COOKIE;
import static io.netty.handler.codec.http.HttpHeaders.Names.SET_COOKIE;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.EndOfDataDecoderException;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

public class HttpSimpleChannelInboundHandler extends SimpleChannelInboundHandler<HttpObject> {

	/**
	 * 具体的业务分发处理
	 */
	private NettyServiceInfer dispatcherService;

	public HttpSimpleChannelInboundHandler(NettyServiceInfer nettyDispatcherService) {
		this.dispatcherService = nettyDispatcherService;
	}

	private final Logger logger = Logger.getLogger(getClass());

	private MyHttpRequest myRequest;

	private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); //Disk

	private HttpPostRequestDecoder decoder;

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		if (decoder != null) {
			if (decoder.hasNext())
				decoder.cleanFiles();
		}
		System.out.println("==========channelInactive========当前值：  " + count.incrementAndGet());
	}

	public void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		System.out.println("==========messageReceived========当前值：  " + count.incrementAndGet()
				+ ", msg's class " + msg.getClass().getName());

		// 服务端第一次读取客户端内容
		if (msg instanceof HttpRequest) {
			HttpRequest orgRequest = (HttpRequest) msg;
			URI uri = new URI(orgRequest.getUri());

			orgRequest.headers().add("orgRequest", "*");

			if (uri.getPath().equals("/favicon.ico")) {
				return;
			}

			this.myRequest = new MyHttpRequest(orgRequest);

			decoder = new HttpPostRequestDecoder(factory, orgRequest);

			logRequest(orgRequest);
		}

		// 服务端第二次读取客户端内容
		if (msg instanceof HttpContent) {
			HttpContent content = (HttpContent) msg;
			decoder.offer(content); // Initialized the internals from a new chunk

			// 服务端第三次读取客户端内容
			if (content instanceof LastHttpContent) {

				parseParam(content);

				String respContent = this.dispatcherService.service(this.myRequest);

				writeResponse(ctx.channel(), respContent);

				reset();
				return;
			}
		}
	}

	/**
	 * 参数处理，post和get请求处理方式不同
	 * @param messageOfClient
	 */
	private void parseParam(HttpContent content) {
		if (HttpMethod.GET.equals(this.myRequest.getMethod())) {
			// 解析请求参数  
			QueryStringDecoder queryStringDecoder = new QueryStringDecoder(this.myRequest.getUri());
			Map<String, List<String>> params = queryStringDecoder.parameters();
			if (!params.isEmpty()) {
				for (Entry<String, List<String>> p : params.entrySet()) {
					String key = p.getKey();
					List<String> vals = p.getValue();
					for (String val : vals) {
						System.out.println("PARAM: " + key + " = " + val + "\r\n");
					}
				}
			}
		} else if (HttpMethod.POST.equals(this.myRequest.getMethod())) {
			try {
				List<InterfaceHttpData> datas = decoder.getBodyHttpDatas();
				for (InterfaceHttpData data : datas) {
					if (data != null) {
						try {
							writeHttpData(data);
						} finally {
							data.release(); // 释放资源
						}
					}
				}
			} catch (EndOfDataDecoderException e1) {
				logger.debug("END OF CONTENT CHUNK BY CHUNK");
			}
		}
	}

	/**
	 * 仅仅用来打印Reques中信息
	 * @param orgRequest
	 */
	private void logRequest(HttpRequest orgRequest) {
		/**
		 * 在服务器端打印请求信息
		 */
		//		System.out.println("VERSION: " + orgRequest.getProtocolVersion().text() + "\r\n");
		//		System.out.println("REQUEST_URI: " + orgRequest.getUri() + "\r\n\r\n");
		//		System.out.println("\r\n\r\n");
		//		for (Map.Entry<String, String> entry : orgRequest.headers()) {
		//			System.out.println("HEADER: " + entry.getKey() + '=' + entry.getValue() + "\r\n");
		//		}
	}

	private void reset() {
		System.out.println("==========reset========当前值：  " + count.incrementAndGet());
		this.myRequest = null;
		if (decoder != null) {
			decoder = null;
		}
	}

	private void writeHttpData(InterfaceHttpData data) {
		System.out.println("==========writeHttpData========当前值：  " + count.incrementAndGet());

		/**
		 * HttpDataType有三种类型
		 * Attribute, FileUpload, InternalAttribute
		 */
		if (data.getHttpDataType() == HttpDataType.Attribute) {
			Attribute attribute = (Attribute) data;
			String value;
			try {
				value = attribute.getValue();
				System.out.println("PARAM: " + attribute.getName() + " = " + value + "\r\n");
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error("\r\nBODY Attribute: " + attribute.getHttpDataType().name() + ":"
						+ attribute.getName() + " Error while reading value: " + e1.getMessage() + "\r\n");
				return;
			}
		}
	}

	/**
	 * http返回响应数据
	 *
	 * @param channel
	 * @param content 
	 */
	private void writeResponse(Channel channel, String content) {
		System.out.println("==========writeResponse========当前值：  " + count.incrementAndGet());

		// Convert the response content to a ChannelBuffer.
		ByteBuf buf = Unpooled.copiedBuffer(content, CharsetUtil.UTF_8);

		// Decide whether to close the connection or not.
		boolean close = this.myRequest.headers().contains(CONNECTION, HttpHeaders.Values.CLOSE, true)
				|| this.myRequest.getProtocolVersion().equals(HttpVersion.HTTP_1_0)
				&& !this.myRequest.headers().contains(CONNECTION, HttpHeaders.Values.KEEP_ALIVE, true);

		// Build the response object.
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
				buf);

		// 不设置Access-Control-Allow-Origin，则localhost请求无返回。
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8")
				.set("Access-Control-Allow-Origin", "*");

		if (!close) {
			// There's no need to add 'Content-Length' header
			// if this is the last response.
			response.headers().set(CONTENT_LENGTH, buf.readableBytes());
		}

		Set<Cookie> cookies;
		String value = this.myRequest.headers().get(COOKIE);
		if (value == null) {
			cookies = Collections.emptySet();
		} else {
			cookies = ServerCookieDecoder.LAX.decode(value);
		}
		if (!cookies.isEmpty()) {
			// Reset the cookies if necessary.
			for (Cookie cookie : cookies) {
				response.headers().add(SET_COOKIE, ServerCookieEncoder.LAX.encode(cookie));
			}
		}
		// Write the response.
		ChannelFuture future = channel.writeAndFlush(response);
		// Close the connection after the write operation is done if necessary.
		if (close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("==========exceptionCaught========当前值：  " + count.incrementAndGet());
		logger.warn("发生异常：", cause);
		ctx.channel().close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		System.out.println("==========channelRead0========当前值：  " + count.incrementAndGet());
		messageReceived(ctx, msg);
	}

	private AtomicInteger count;
	/**
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRegistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		count = new AtomicInteger(0);
		System.out.println("==========channelRegistered========当前值：  " + count.incrementAndGet());
	}

	/**
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelUnregistered(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
		System.out.println("==========channelUnregistered========当前值：  " + count.incrementAndGet());
	}

	/**
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println("==========channelActive========当前值：  " + count.incrementAndGet());
	}

	/**
	 * 
	 * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
		System.out.println("==========channelReadComplete========当前值：  " + count.incrementAndGet());
	}
	
}
