/**
 * 项目名称：java
 * 文件包名：com.ly.java.code
 * 文件名称：杨辉三角.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年4月6日 下午2:20:19
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.code;

/**
 * @功能描述：
 * @文件名称：杨辉三角.java
 * 1
 * 1 1
 * 1 2 1
 * 1 3 3 1
 * @author ly
 */
public class 杨辉三角 {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		杨辉三角(10);
	}

	public static void 杨辉三角(int high) {
		if (high < 1)
			return;
		int triangle[][] = new int[high][high];
		for (int i = 0; i < high; i++) {
			triangle[i][0] = 1; 
			triangle[i][i] = 1; 
		}
		
		for (int i = 2; i < high; i++) {
			for (int j = 1; j < i; j++) {
				triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
			}
		}

		for (int i = 0; i < high; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.printf("%3d ", triangle[i][j]);
			}
			System.out.println();
		}
	}

}
