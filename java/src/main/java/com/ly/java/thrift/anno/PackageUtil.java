package com.ly.java.thrift.anno;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageUtil {

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(processer());
	}

	public static Map<String, String[]> processer() throws ClassNotFoundException {

		Map<String, String[]> map = new HashMap<>();
		String packageName = "com.ly.java.thrift";

		List<String> classNames = getClassName(packageName);
		for (String className : classNames) {
			Class cls = Class.forName(className);
			SAASServerMap ann = (SAASServerMap) cls.getAnnotation(SAASServerMap.class);
			if (ann != null) {
				System.out.println(className);
				String urlPrefix = ann.uri();
				Method[] methods = cls.getMethods();
				for (Method method : methods) {
					SAASServerMap methodAnn = method.getAnnotation(SAASServerMap.class);
					if (methodAnn != null) {
						String url = urlPrefix + methodAnn.uri();
						map.put(url, new String[] { className, method.getName() });
						System.out.println(url);
					}
				}
			}
			// System.out.println(className);
		}

		return map;
	}

	public static List<String> getClassName(String packageName) {
		String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
		List<String> fileNames = getClassName(filePath, null);
		return fileNames;
	}

	private static List<String> getClassName(String filePath, List<String> className) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				myClassName.addAll(getClassName(childFile.getPath(), myClassName));
			} else {
				String childFilePath = childFile.getPath();
				if (childFilePath.endsWith(".class")) {
					childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9,
							childFilePath.lastIndexOf("."));
					childFilePath = childFilePath.replace("\\", ".");
					myClassName.add(childFilePath);
				}
			}
		}

		return myClassName;
	}
}