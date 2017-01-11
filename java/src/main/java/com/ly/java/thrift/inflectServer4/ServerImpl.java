package com.ly.java.thrift.inflectServer4;

import org.apache.thrift.TException;

/** 
 * 服务入口类 接口实现类,调用转发器，将请求转发到具体的操作类上
 * 
 */
public class ServerImpl implements Iface {

	@Override
	public String invoke(String uri, String request) throws TException {

		return DispatchService.getInstance().dispatcher(uri, request);
	}
}