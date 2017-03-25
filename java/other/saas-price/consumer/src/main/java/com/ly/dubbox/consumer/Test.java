/**
 * 
 */
package com.ly.dubbox.consumer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hugoyang
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcurrentHashMap<String, String> names = new ConcurrentHashMap<String, String>();
		names.put("李安", "导演");
		System.out.println(names.keySet());
	}

}
