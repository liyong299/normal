/**
 * 
 */
package com.dup.test.guava;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;

/**
 * @author hugoyang
 * 
 */
public class JointOrSplit {

	static List<String> list = new ArrayList<String>();
	static {
		list.add("1");
		list.add("w");
		list.add(null);
		list.add("3");

	}

	static String delimiter = ",";

	static void normally() {

		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			if (str != null) {
				sb.append(str).append(delimiter);
			}
		}
		sb.setLength(sb.length() - delimiter.length());
		System.out.println(sb.toString());
	}

	static void guava() {
		String str = Joiner.on(delimiter).skipNulls().join(list);
		System.out.println(str);

		String str2 = Joiner.on("&").withKeyValueSeparator("=")
				.join(ImmutableMap.of("id", "123", "name", "green"));
		System.out.println(str2);

		final Map<String, String> join = Splitter.on("&")
				.withKeyValueSeparator("=").split("id=123&name=green");
		System.out.println(join);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		normally();
		guava();
	}

}
