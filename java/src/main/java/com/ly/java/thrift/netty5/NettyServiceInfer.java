package com.ly.java.thrift.netty5;

public interface NettyServiceInfer {

	/**
	 * http消息处理服务分发器
	 * 
	 * @param request
	 * @param myResponse
	 */
	String service(MyHttpRequest request, MyHttpResponse myResponse);
}
