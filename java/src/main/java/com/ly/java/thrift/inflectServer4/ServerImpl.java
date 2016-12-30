package com.ly.java.thrift.inflectServer4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.thrift.TException;

import com.ly.java.thrift.anno.URIBean;
import com.ly.java.thrift.anno.URIProcessor;

/** 
 * 服务入口类 接口实现类 
 * 
 */
public class ServerImpl implements Iface {

	@Override
	public String invoke(String url, String request) throws TException {

		URIBean bean = URIProcessor.URI_OBJECT_MAP.get(url);
		Object obj = bean.getObj();
		Method method = bean.getMethod();

		Object restult = null;
		try {
			restult = method.invoke(obj, request);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return restult.toString();
	}
}