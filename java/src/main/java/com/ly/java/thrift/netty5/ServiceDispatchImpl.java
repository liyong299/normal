package com.ly.java.thrift.netty5;


public class ServiceDispatchImpl implements ServiceDispatchInfer {



	/**
	 * http消息处理服务分发器，负责根据URI调用不同的服务
	 * 
	 * @param request
	 */
	public String service(MyHttpRequest request, MyHttpResponse myResponse) {
		System.out.println(request);
		System.out.println(request.getParameterValues());

		DispatchService.getInstance().dispatcher(request, myResponse);

		return "test";
	}

}
