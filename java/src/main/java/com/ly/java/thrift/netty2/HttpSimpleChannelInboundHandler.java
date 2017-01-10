package com.ly.java.thrift.netty2;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class HttpSimpleChannelInboundHandler extends SimpleChannelInboundHandler<HttpObject> {

	/**
	 * 具体的业务分发处理
	 */
	private NettyDispatcherService dispatcherService;

	public HttpSimpleChannelInboundHandler(NettyDispatcherService nettyDispatcherService) {
		this.dispatcherService = nettyDispatcherService;
	}

	private final Logger logger = Logger.getLogger(getClass());

	private HttpRequest orgRequest;

	private MyHttpRequest myHttpRequest;

	private boolean readingChunks;

	private final StringBuilder responseContent = new StringBuilder();

	private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); // Disk

	private HttpPostRequestDecoder decoder;

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		if (decoder != null) {
			decoder.cleanFiles();
		}
	}

	public void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		if (msg instanceof HttpRequest) {
			this.orgRequest = (HttpRequest) msg;
			decoder = new HttpPostRequestDecoder(factory, orgRequest);
			URI uri = new URI(orgRequest.getUri());

			if (uri.getPath().equals("/favicon.ico")) {
				return;
			}

			if (myHttpRequest == null) {
				myHttpRequest = new MyHttpRequest(HttpVersion.HTTP_1_1, orgRequest.getMethod(), uri.toString());
			}
		}

		/**
		 * 在服务器端打印请求信息
		 */
		System.out.println("VERSION: " + orgRequest.getProtocolVersion().text() + "\r\n");
		System.out.println("REQUEST_URI: " + orgRequest.getUri() + "\r\n\r\n");
		System.out.println("\r\n\r\n");
		for (Map.Entry<String, String> entry : orgRequest.headers()) {
			System.out.println("HEADER: " + entry.getKey() + '=' + entry.getValue() + "\r\n");
		}

		if (orgRequest.getMethod().equals(HttpMethod.GET)) {
			// get请求
		} else if (orgRequest.getMethod().equals(HttpMethod.POST)) {
			// post请求

			readingChunks = HttpHeaders.isTransferEncodingChunked(orgRequest);

			try {
				while (decoder.hasNext()) {
					InterfaceHttpData data = decoder.next();
					if (data != null) {
						try {
							writeHttpData(data);
						} finally {
							data.release();
						}
					}
				}
			} catch (EndOfDataDecoderException e1) {
				responseContent.append("\r\n\r\nEND OF POST CONTENT\r\n\r\n");
			}
		}

		if (decoder != null && msg instanceof HttpContent) {
			HttpContent chunk = (HttpContent) msg;
			try {
				this.decoder.offer(chunk);
			} catch (HttpPostRequestDecoder.ErrorDataDecoderException e1) {
				this.responseContent.append(e1.getMessage());
				ctx.channel().close();
				return;
			}

			try {
				while (this.decoder.hasNext()) {
					InterfaceHttpData data = this.decoder.next();
					if (data != null) {
						try {
							bindRequestParamer(data);
						} finally {
							data.release();
						}
					}
				}
			} catch (HttpPostRequestDecoder.EndOfDataDecoderException e1) {
			}
			if ((chunk instanceof LastHttpContent)) {
				QueryStringDecoder decoderQuery = new QueryStringDecoder(this.orgRequest.getUri());
				Map<String, List<String>> uriAttributes = decoderQuery.parameters();
				for (String key : uriAttributes.keySet()) {
					this.myHttpRequest.resolveMultipart(key, (List) uriAttributes.get(key));
				}

				invoke(ctx);

				reset();
			}
		}
	}

	private void bindRequestParamer(InterfaceHttpData data) {
		if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
			Attribute attribute = (Attribute) data;
			String value = null;
			try {
				value = attribute.getValue();
			} catch (IOException e1) {
				System.err.println("解析参数异常：" + attribute.getHttpDataType().name());
				e1.printStackTrace();
				return;
			}
			List<String> attValueList = this.myHttpRequest.getParameterValues(attribute.getName());
			if (null != attValueList) {
				this.myHttpRequest.getParameterValues(attribute.getName()).add(value);
			} else {
				List<String> attValue = new ArrayList();
				attValue.add(value);
				this.myHttpRequest.resolveMultipart(attribute.getName(), attValue);
			}
		} else if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.FileUpload) {

		} else if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.InternalAttribute) {
			System.err.println("待完成：" + InterfaceHttpData.HttpDataType.InternalAttribute);
		}
	}

	/**
	 * GET POST 方法的具体调用
	 * 
	 * @param ctx
	 */
	private void invoke(ChannelHandlerContext ctx) {

		QueryStringDecoder decoderQuery = new QueryStringDecoder(this.orgRequest.getUri());
		Map<String, List<String>> uriAttributes = decoderQuery.parameters();
		for (String key : uriAttributes.keySet()) {
			this.myHttpRequest.resolveMultipart(key, (List) uriAttributes.get(key));
		}

		System.out.println(myHttpRequest.getMultipartParameters());

		String content = this.dispatcherService.service(myHttpRequest);

		System.out.println("------------------------");

		writeResponse(ctx.channel(), content);

		System.out.println("-------------2-----------");

		reset();
	}

	private void reset() {
		orgRequest = null;
		if (myHttpRequest != null)
			myHttpRequest = null;
		if (decoder != null) {
			decoder.destroy();
			decoder = null;
		}
	}

	private void writeHttpData(InterfaceHttpData data) {

		/**
		 * HttpDataType有三种类型 Attribute, FileUpload, InternalAttribute
		 */
		if (data.getHttpDataType() == HttpDataType.Attribute) {
			Attribute attribute = (Attribute) data;
			String value;
			try {
				value = attribute.getValue();
			} catch (IOException e1) {
				e1.printStackTrace();
				responseContent.append("\r\nBODY Attribute: " + attribute.getHttpDataType().name() + ":"
						+ attribute.getName() + " Error while reading value: " + e1.getMessage() + "\r\n");
				return;
			}
			if (value.length() > 100) {
				responseContent.append("\r\nBODY Attribute: " + attribute.getHttpDataType().name() + ":"
						+ attribute.getName() + " data too long\r\n");
			} else {
				responseContent.append("\r\nBODY Attribute: " + attribute.getHttpDataType().name() + ":"
						+ attribute.toString() + "\r\n");
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
		// Convert the response content to a ChannelBuffer.
		ByteBuf buf = Unpooled.copiedBuffer(content, CharsetUtil.UTF_8);

		// Decide whether to close the connection or not.
		boolean close = orgRequest.headers().contains(CONNECTION, HttpHeaders.Values.CLOSE, true)
				|| orgRequest.getProtocolVersion().equals(HttpVersion.HTTP_1_0)
				&& !orgRequest.headers().contains(CONNECTION, HttpHeaders.Values.KEEP_ALIVE, true);

		// Build the response object.
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);

		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");

		if (!close) {
			// There's no need to add 'Content-Length' header
			// if this is the last response.
			response.headers().set(CONTENT_LENGTH, buf.readableBytes());
		}

		Set<Cookie> cookies;
		String value = orgRequest.headers().get(COOKIE);
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
		logger.warn(responseContent.toString(), cause);
		ctx.channel().close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		messageReceived(ctx, msg);
	}
}
