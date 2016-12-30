package com.ly.java.thrift.inflectServer4;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import com.ly.java.thrift.anno.URIProcessor;

public class Test {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException {
		Enumeration<URL> resourceUrls = Test.class.getClassLoader().getResources(
				"com/ly/java/thrift/inflectServer4");

		while (resourceUrls.hasMoreElements()) {
			@SuppressWarnings("unused")
			URL url = resourceUrls.nextElement();
			File file = new File(url.getFile());
			if (file.isDirectory()) {

				System.out.println(file.getAbsolutePath());
			}
			System.out.println(url);
		}

		System.out.println(Test.class.getResource(""));

		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
			//			System.out.println(urls[i].toExternalForm());
		}

		URIProcessor.getIntance().process(Test.class);
		;
	}
}
