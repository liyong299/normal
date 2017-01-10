package com.ly.java.thrift.netty2;


public interface NettyDispatcherService {

	/**
	 * http消息处理服务分发器
	 * 
	 * @param request
	 */
	String service(MyHttpRequest request);
}
