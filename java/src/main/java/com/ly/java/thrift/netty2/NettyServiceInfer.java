package com.ly.java.thrift.netty2;

import io.netty.handler.codec.http.HttpRequest;

public interface NettyServiceInfer {

	/**
	 * http消息处理服务分发器
	 * @param request
	 */
	String service(HttpRequest request);
}
