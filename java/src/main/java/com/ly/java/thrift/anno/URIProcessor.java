package com.ly.java.thrift.anno;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @功能描述：加载类和注解
 * @文件名称：URIProcessor.java
 * @author ly
 */
public class URIProcessor {

	public static Map<String, URIBean> URI_OBJECT_MAP = new HashMap<>();

	private static URIProcessor processor = new URIProcessor();

	private URIProcessor() {}

	public static URIProcessor getIntance() {
		return processor;
	}
	@SuppressWarnings("rawtypes")
	public void process(Class cls) throws ClassNotFoundException, IOException,
			NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException {
		
		List<Class<?>> classList = ClassUtil.getClasses(cls);
		for (Class clazz : classList){
			SAASServerMap ann = (SAASServerMap) clazz.getAnnotation(SAASServerMap.class);
			if (ann != null){
				Constructor constructor = clazz.getConstructor(); // 必须包含无参构造函数
				if (constructor == null) {
					System.out.println("不包含无参构造函数！！！");
					continue;
				}

				Object obj = clazz.newInstance();
				String serverURI = ann.value();
				for (Method method : clazz.getMethods()) {
					SAASServerMap methodAnn = method.getAnnotation(SAASServerMap.class);
					if (methodAnn != null) {
						serverURI = serverURI + methodAnn.value();
						System.out.println("url : " + serverURI + " | method : " + clazz.getName() + "."
								+ method.getName());
						URI_OBJECT_MAP.put(serverURI, new URIBean(obj, method));
					}
				}
			}
		}
	}
}
