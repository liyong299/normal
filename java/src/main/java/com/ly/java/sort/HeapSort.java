/**
 * 项目名称：java
 * 文件包名：com.ly.java.sort
 * 文件名称：HeapSort.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月30日 上午11:33:09
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.sort;

/**
 * @功能描述：堆排序(Heapsort)是指利用堆积树（堆）这种数据结构所设计的一种排序算法，它是选择排序的一种。可以利用数组的特点快速定位指定索引的元素。
 * @堆分为大根堆和小根堆，是完全二叉树。大根堆的要求是每个节点的值都不大于其父节点的值，即A[PARENT[i]] >= A[i]。
 * 在数组的非降序排序中，需要使用的就是大根堆，因为根据大根堆的要求可知，最大的值一定在堆顶。
 * @文件名称：HeapSort.java
 * @author ly
 */
public class HeapSort<E> extends AbstractSort<E> {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new HeapSort<>().sort(NumberUtil.genIntArray(6));
	}


	@Override
	public void sort0(E[] numbers) {
		// 构造初始堆
		createInitHeap(numbers);
	}

	private void createInitHeap(E[] numbers) {

	}
}
