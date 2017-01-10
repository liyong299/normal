package com.ly.java.thrift.netty5;


public class MyServiceImpl implements NettyServiceInfer {

	/**
	 * http消息处理服务分发器
	 * @param request
	 */
	public String service(MyHttpRequest request) {
		System.out.println(request.getParameterValues());
		return "test";
	}
}
