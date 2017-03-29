/**
 * 项目名称：java
 * 文件包名：com.ly.java.sort
 * 文件名称：AbstractSort.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月29日 下午5:20:07
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.sort;

/**
 * @功能描述：
 * @文件名称：AbstractSort.java
 * @author ly
 */
public abstract class AbstractSort<E> {

	public void sort(E[] numbers) {
		if (numbers == null || numbers.length < 1) {
			System.out.println("数组有错误，不能处理。");
		}

		sort0(numbers);
		print(numbers);
	}

	protected void print(E[] numbers) {
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + "   ");
			if (i % 10 == 9) {
				System.out.println();
			}
		}
	}

	public abstract void sort0(E[] numbers);

	public void swap(E[] array, int x, int y) {
		E temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	public boolean comp(E o1, E o2) {
		return o1.equals(o2);
	}

	public boolean gt(E o1, E o2) {
		return Integer.valueOf(o1.toString()) > Integer.valueOf(o2.toString());
	}

	public boolean lt(E o1, E o2) {
		return Integer.valueOf(o1.toString()) < Integer.valueOf(o2.toString());
	}
}
