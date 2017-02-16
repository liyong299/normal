/**
 * 项目名称：java
 * 文件包名：com.ly.java.other
 * 文件名称：StringOOM.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年1月3日 下午2:25:39
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.other;

/**
 * @功能描述：String 对象造成的OOM
 * @文件名称：StringOOM.java
 * @author ly
 */
public class StringOOM {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StringOOM obj1 = new StringOOM();
		int i = 0;
		while (++i > 0) {
			obj1.test1(i);
		}
	}

	public void test1(int i) {
		String str = "abcdefghijklmnopqrstuvwxyz" + i;
		for (int j = 0; j < 10; j++) {
			str = str + str;
		}
		//		str = null;
	}
}
