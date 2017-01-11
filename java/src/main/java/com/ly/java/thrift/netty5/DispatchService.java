package com.ly.java.thrift.netty5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.ly.java.thrift.anno.URIBean;
import com.ly.java.thrift.anno.URIProcessor;

public class DispatchService {
	private URIProcessor uriProcessor;
	private static DispatchService service = new DispatchService();

	private DispatchService() {}

	public static DispatchService getInstance() {
		return service;
	}

	public void dispatcher(MyHttpRequest request, MyHttpResponse myResponse) {

		URIBean bean = uriProcessor.URI_OBJECT_MAP.get(request.getUri());
		Object obj = bean.getObj();
		Method method = bean.getMethod();

		try {
			method.invoke(obj, request, myResponse);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
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
