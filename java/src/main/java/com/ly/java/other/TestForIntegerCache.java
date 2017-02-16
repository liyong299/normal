package com.ly.java.other;

import java.lang.reflect.Field;

/**
 * @功能描述：通过反射修改Intger中的value值
 * @文件名称：TestForIntegerCache.java
 * @author ly
 */
public class TestForIntegerCache {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		Integer a = 129, b = 200;

		System.out.println("转换前：a=" + a + "  b=" + b);

		wrap(a, b);

		System.out.println("转换后：a=" + a + "  b=" + b);

		System.out.println(Integer.valueOf(1));
	}

	private static void wrap(Integer a, Integer b) throws ClassNotFoundException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		int t1 = a.intValue();
		int t2 = b.intValue();

		Class cls1 = a.getClass();
		Field value1 = cls1.getDeclaredField("value");
		value1.setAccessible(true);
		value1.setInt(a, t2);

		Class cls2 = b.getClass();
		Field value2 = cls2.getDeclaredField("value");
		value2.setAccessible(true);
		value2.setInt(b, t1);
	}

}
