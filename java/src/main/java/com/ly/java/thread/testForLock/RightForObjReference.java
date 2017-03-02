/**
 * 项目名称：java
 * 文件包名：com.ly.java.thread.testForLock
 * 文件名称：ErrorForObjReference.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月1日 下午2:57:41
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.thread.testForLock;

/**
 * @功能描述：
 * @文件名称：ErrorForObjReference.java
 * @author ly
 */
public class RightForObjReference {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SecondObj obj = new SecondObj();
		for (int i = 0; i < 5; i++) {
			new ModifyReferenceObjThreadByLock(obj).start();
		}
	}
}
