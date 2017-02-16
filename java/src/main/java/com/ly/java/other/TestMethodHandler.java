/**
 * 项目名称：java
 * 文件包名：com.ly.java.other
 * 文件名称：Test方法句柄.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年2月16日 下午4:24:38
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.other;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @功能描述：方法句柄只能调用有native的方法。
 * @文件名称：TestMethodHandler.java
 * @author ly
 */
public class TestMethodHandler {
	public MethodHandle getHandler() {
		MethodHandle mh = null;
		MethodType mt = MethodType.methodType(StringBuilder.class, String.class);
		MethodHandles.Lookup lk = MethodHandles.lookup();

		try {
			mh = lk.findVirtual(StringBuilder.class, "append", mt);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return mh;
	}

	public static void main(String[] args) throws Throwable {
		StringBuilder strB = new StringBuilder("Test_");
		MethodHandle mh = new TestMethodHandler().getHandler();

		System.out.println((StringBuilder) mh.invokeExact(strB, " | TestMethodHandler "));
	}

}


class Test2 {
	public String test2(String p1, int p2) {
		return p1 + "____" + p2;
	}
}