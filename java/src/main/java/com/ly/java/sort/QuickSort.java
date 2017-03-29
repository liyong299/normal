/**
 * 项目名称：java
 * 文件包名：com.ly.java.sort
 * 文件名称：QuickSort.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月29日 下午5:09:14
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.sort;


/**
 * @功能描述：快速排序（Quicksort）是对冒泡排序的一种改进。
 * 快速排序由C. A. R. Hoare在1962年提出。
 * 它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * @文件名称：QuickSort.java
 * @author ly
 */
public class QuickSort<E> extends AbstractSort {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new QuickSort<Integer>().sort(NumberUtil.genIntArray(6));
	}

	public void sort0(Object[] array) {
		quickSort(array, 0, array.length - 1);
	}

	@SuppressWarnings("unchecked")
	private void quickSort(Object[] array, int startIdx, int endIdx) {
		if (startIdx >= endIdx) {
			System.out.println("不用比较，  startIdx  :  " + startIdx + ", endIdx  : " + endIdx);
			return;
		}
		Object midVal = array[startIdx];
		int tmpIdx = startIdx;
		System.out.println("startIdx  :  " + startIdx + ", endIdx  : " + endIdx);
		while (startIdx < endIdx) {
			while (lt(array[endIdx], midVal) && startIdx < endIdx) {
				startIdx++;
			}
			while (gt(array[endIdx], midVal) && startIdx < endIdx) {
				endIdx--;
			}
			swap(array, startIdx, endIdx);
		}
		swap(array, tmpIdx, startIdx);

		print(array);

		quickSort(array, tmpIdx, startIdx - 1);
		quickSort(array, startIdx + 1, endIdx);
	}
}
