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
 * @功能描述：快速排序（Quicksort）是对冒泡排序的一种改进。 O(nlogn)
 * @快速排序由C. A. R. Hoare在1962年提出。它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分
 *                                   ，其中一部分的所有数据都比另外一部分的所有数据都要小，
 *                                   然后再按此方法对这两部分数据分别进行快速排序
 *                                   ，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * @文件名称：QuickSort.java
 * @author ly
 */
public class QuickSort2<E> extends AbstractSort<E> {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		QuickSort2 sort2 = new QuickSort2<Integer>();
		sort2.checkProgram();
	}

	public void sort0(E[] array) {
		print(array);
		quickSort(array, 0, array.length - 1);
	}

	private void quickSort(E[] array, int startIdx, int endIdx) {
		if (startIdx >= endIdx) {
			System.out.println("不用比较，  startIdx  :  " + startIdx + ", endIdx  : " + endIdx);
			return;
		}
		int tmpStartIdx = startIdx;
		int tmpEndIdx = endIdx;
		E midVal = array[startIdx];
		tmpStartIdx = tmpStartIdx + 1;

		System.out.println("startIdx  :  " + startIdx + ", endIdx  : " + endIdx + ", midVal  : " + midVal);

		while (tmpStartIdx < tmpEndIdx) {
			while (lt(array[tmpStartIdx], midVal) && tmpStartIdx < tmpEndIdx) {
				tmpStartIdx++;
			}
			while (gt(array[tmpEndIdx], midVal) && tmpStartIdx < tmpEndIdx) {
				tmpEndIdx--;
			}

			System.out.println("||__  startIdx  :  " + tmpStartIdx + ", endIdx  : " + tmpEndIdx);
			if (tmpStartIdx < tmpEndIdx) {
				swap(array, tmpStartIdx, tmpEndIdx);
			}
			System.out.println();
			print(array);
		}

		if (lt(array[tmpStartIdx], midVal)) {
			swap(array, startIdx, tmpStartIdx);
		}
		System.out.println();
		print(array);

		if (tmpStartIdx > startIdx)
			quickSort(array, startIdx, tmpStartIdx - 1);
		if (tmpEndIdx < endIdx)
			quickSort(array, tmpStartIdx, endIdx);
	}
}
