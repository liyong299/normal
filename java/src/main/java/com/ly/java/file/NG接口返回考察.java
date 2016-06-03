/**
 * 项目名称：java
 * 文件包名：com.ly.java.file
 * 文件名称：NG接口返回考察.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年6月1日 下午2:11:39
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：NG接口返回考察.java
 * 
 * @author ly
 */
public class NG接口返回考察 {

    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	String ngResult = FileUtils.readFileToString(new File("C:/Users/hugoyang/Desktop/api_result.log"));
	String apiResult = FileUtils.readFileToString(new File("C:/Users/hugoyang/Desktop/ng_result.log"));

	String[] ngCodes = ngResult.split("groupCode");
	List<String> ngList = new ArrayList<String>();
	int idx = 0;
	for (String code : ngCodes) {
	    if (code.contains("code\":\"0300")) {
		idx++;
		ngList.add(code.substring(code.indexOf("code\":\"0300")).substring(7, 23));
		// System.out.println(code.substring(code.indexOf("code\":\"0300")).substring(7,
		// 23));
	    }
	}

	String[] apiCodes = apiResult.split("&lt;/Code>");
	List<String> apiList = new ArrayList<String>();
	idx = 0;
	for (String code : apiCodes) {
	    if (code.contains("&lt;Code")) {
		idx++;
		String tmp = code.substring(code.indexOf("&lt;Code")).substring(9);
		if (tmp.contains("gt;")) {
		    tmp = tmp.substring(3);
		}
//		System.out.println(tmp);
		apiList.add(tmp);

	    }
	}
	System.out.println(idx);

	judge(ngList, apiList);
	judge(apiList, ngList);
    }

    /**
     * 判断list1中所有元素，是否在 list2中
     * 
     * @param list1
     * @param list2
     * @return
     */
    public static boolean judge(List<String> list1, List<String> list2) {
	for (String str : list1) {
	    if (!judge(list2, str)) {
		System.out.println(str);
		return false;
	    }
	}
	return true;
    }

    public static boolean judge(List<String> allContent, String code) {
	return allContent.contains(code);
    }
}
