package com.ly.java.thrift.netty5;

public class MyServiceImpl implements NettyServiceInfer {

	/**
	 * http消息处理服务分发器
	 * 
	 * @param request
	 */
	public String service(MyHttpRequest request, MyHttpResponse myResponse) {
		System.out.println(request);
		System.out.println(request.getParameterValues());
		myResponse.setContent("test");
		return "test";
	}
}
