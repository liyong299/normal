package com.ly.java.thrift.netty5;

import org.apache.thrift.TException;

import com.ly.java.thrift.anno.SAASServerMap;

/**
 * HelloWord 接口实现类
 * 
 */
@SAASServerMap("/demo/order")
public class HelloWorldAction {

	private HelloWorldService service = new HelloWorldService();
	@SAASServerMap("/add")
	public String add(MyHttpRequest request, MyHttpResponse myResponse) throws TException {
		System.out.println(Thread.currentThread().getName() + " HelloWorldImpl.sayHello ----  " + System.currentTimeMillis());

		// 用请求参数的前五位作为租户信息。TODO 正式使用需要加一个租户字段。
		String result = service.invoke("/demo/order/add", getTenantKey("")
				+ request.getParameterValues().toString());

		myResponse.setContent(result);

		return "hello world, -" + System.currentTimeMillis();
	}

	/**
	 * 
	 * @param userAcount
	 * @return
	 */
	public String getTenantKey(String userAcount) {
		return String.format("%5s", "ZY");
	}
}