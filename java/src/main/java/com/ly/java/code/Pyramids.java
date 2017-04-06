/**
 * 项目名称：java
 * 文件包名：com.ly.java.code
 * 文件名称：Pyramids.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月6日 下午1:42:11
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.code;

/**
 * @功能描述：金字塔
 * @文件名称：Pyramids.java
 * @author ly
 */
public class Pyramids {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		pyramids(5);
	}

	public static void pyramids(int high){
		if (high < 1) return;
		int mid = high;
		
		for (int i = 0; i < high; i++) {
			for (int j = 1; j < high * 2; j++) {
				if (j <= mid + i && j >= mid - i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
