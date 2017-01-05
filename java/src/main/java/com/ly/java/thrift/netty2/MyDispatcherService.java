package com.ly.java.thrift.netty2;

import io.netty.handler.codec.http.HttpRequest;

public class MyDispatcherService implements NettyDispatcherService {

	/**
	 * http消息处理服务分发器
	 * @param request
	 */
	public String service(HttpRequest request) {
		return "test";
	}
}
