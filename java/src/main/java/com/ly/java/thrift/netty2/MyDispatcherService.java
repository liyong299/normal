package com.ly.java.thrift.netty2;

public class MyDispatcherService implements NettyDispatcherService {

	/**
	 * http消息处理服务分发器
	 * 
	 * @param request
	 */
	public String service(MyHttpRequest request) {
		return "test";
	}
}
