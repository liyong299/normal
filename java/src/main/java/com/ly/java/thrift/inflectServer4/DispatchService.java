package com.ly.java.thrift.inflectServer4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ly.java.thrift.anno.URIBean;
import com.ly.java.thrift.anno.URIProcessor;

/**
 * @功能描述：请求转发器，负责根据URI，调用具体的业务处理实现。
 * @文件名称：DispatchService.java
 * @author ly
 */
public class DispatchService {
	private URIProcessor uriProcessor;
	private static DispatchService service = new DispatchService();

	private DispatchService() {}

	public static DispatchService getInstance() {
		return service;
	}

	public String dispatcher(String uri, String request) {

		URIBean bean = uriProcessor.URI_OBJECT_MAP.get(uri);
		Object obj = bean.getObj();
		Method method = bean.getMethod();

		try {
			Object result = method.invoke(obj, request);
			if (result instanceof String)
				return (String) result;
			else
				return result.toString();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return the uriProcessor
	 */
	public URIProcessor getUriProcessor() {
		return uriProcessor;
	}

	/**
	 * @param uriProcessor the uriProcessor to set
	 */
	public void setUriProcessor(URIProcessor uriProcessor) {
		this.uriProcessor = uriProcessor;
	}
}
