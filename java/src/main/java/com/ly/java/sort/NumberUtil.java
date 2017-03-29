/**
 * 项目名称：java
 * 文件包名：com.ly.java.sort
 * 文件名称：NumberUtil.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月29日 下午5:09:55
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.sort;

/**
 * @功能描述：辅助工具
 * @文件名称：NumberUtil.java
 * @author ly
 */
public class NumberUtil {

	/**
	 * 随机产生一批数据
	 * @param length
	 * @return
	 */
	public static Object[] genIntArray(int length, int min, int max) {
		if (length < 1) {
			System.out.println("长度有误：" + length);
			return null;
		}
		Object[] randoms = new Object[length];
		int range = max - min;
		for (int i = 0; i < length; i++){
			randoms[i] = (int) (Math.random() * range + min);
		}
		return randoms;
	}

	/**
	 * 随机产生一批数据,最大值是100000
	 * @param length
	 * @return
	 */
	public static Object[] genIntArray(int length) {
		return genIntArray(length, 0, 100000);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
