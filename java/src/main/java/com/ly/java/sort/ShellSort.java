/**
 * 项目名称：java
 * 文件包名：com.ly.java.sort
 * 文件名称：ShellSort.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月30日 下午4:09:13
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.sort;

/**
 * @功能描述：希尔排序(Shell Sort)是插入排序的一种。也称缩小增量排序，是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法。该方法因DL．Shell于1959年提出而得名。
 * @希尔排序,是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * @第一步：先取一个小于n的整数d1作为第一个增量，把文件的全部记录分组。所有距离为d1的倍数的记录放在同一个组中。先在各组内进行直接插入排序；
 * @第二步：然后，取第二个增量d2<d1重复上述的分组和排序，直至所取的增量  =1(  <  …<d2<d1)，即所有记录放在同一组中进行直接插入排序为止。
 * @文件名称：ShellSort.java
 * @author ly
 */
public class ShellSort<E> extends AbstractSort<E> {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ShellSort<>().checkProgram();
	}

	@Override
	public void sort0(E[] numbers) {
		int len = numbers.length;
		int d = len / 2;
		print(numbers);
		shellSort(numbers, d);
	}

	private void shellSort(E[] numbers, int d) {
		int steps = numbers.length / d;
		for (int i = 0; i < numbers.length; i = i + d) {
			for (int j = steps * d - 1; j > i; j = j - d) {
				System.out.println("i  :  " + i + ", j  : " + j);
				if (gt(numbers[i], numbers[j])) {
					swap(numbers, i, j);
				}
				print(numbers);
			}
		}
		if (d > 1) {
			shellSort(numbers, d / 2);
		}
	}
}
