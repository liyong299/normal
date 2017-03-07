/**
 * 项目名称：core
 * 文件包名：com.dup.test.http
 * 文件名称：投票信息.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月5日 下午1:57:53
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.http;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：投票信息.java
 * 
 * @author ly
 */
public class 投票信息 {

    /**
     * <p>
     * 功能描述：
     * <p>
     * 方法功能
     * </p>
     * </p>
     * <p>
     * 实现逻辑：
     * <p>
     * 实现步骤
     * </p>
     * </p>
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException {
	for (int i = 0; i < 100; i++)
	{
	    vote();
	    Thread.currentThread().sleep(100 * 1000);
	}
	
    }

    public static synchronized void vote() {
	Connection.Response res = null;
	String url = "http://m.tripvivid.com/vote_items/66/vote_counts";
	try {
	    res = Jsoup
		    .connect(url)
		    .header("User-Agent",
			    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
		    .header("Referer", "http://m.tripvivid.com/votes/1")
		    .header("Cookie", "webKey=a26e152a0c40c10add9a91016074bf10; _gat=1; XSRF-TOKEN=eyJpdiI6IjRCYVB2U2VRekxmckhMYk01Q00zYVE9PSIsInZhbHVlIjoiSjh0aEZ6YVlJRkZaTEY1blFpS0ZWcmd3VkN0XC9ZQW1Ta3pCSXNFNFhGNkhvMHBLdkNxTVJmN0g2WVEwTEtiREpjMWhLTWN0Y1VmWldjWjZcLzNZM1kwZz09IiwibWFjIjoiOWQ3N2RkMjk0Njg1ZTBmOTdiYjJhNDc2YjQ3MzI1YzQ3YTE3OTNlZTAzZWRiNzE1MWIzM2JkZmZiNmI3NmM0MSJ9; laravel_session=ebbb30ac0898cc3cf4e6ae345c3bd47ff9ca1102; Hm_lvt_704d41b2218e604b1ecc97fc9507812b=1459835673; Hm_lpvt_704d41b2218e604b1ecc97fc9507812b=1459836153; _ga=GA1.2.1696066472.1459835673")
		    .header("X-CSRF-TOKEN", "4JdnVEApvSFgJLBiBkxzpKuA7aHyqKaAXEgAN0cp")
			    .method(Method.POST).execute();
	   
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
