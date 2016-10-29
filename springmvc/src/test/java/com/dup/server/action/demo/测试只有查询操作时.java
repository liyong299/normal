/**
 * 项目名称：mvc.core
 * 文件包名：com.dup.server.action.demo
 * 文件名称：测试只有查询操作时.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月9日 下午2:10:01
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.server.action.demo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

import com.dup.core.util.udp.StatsdClient;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：测试只有查询操作时.java
 * 
 * @author ly
 */
public class 测试只有查询操作时 {

    /**
     * <p>
     * 功能描述：
     * <p>
     * 只有查询操作时，是否有时间突然变长的问题
     * </p>
     * </p>
     * 
     * @param args
     */
    public static void main(String[] args) {
	String url = "http://localhost:8080/api/v1/demo/queryCinemasTest.json?channelCode=0001&sign=0002";
	ExecutorService threadPool = Executors.newFixedThreadPool(50);
	final Semaphore semp = new Semaphore(50); // 许可个数100
	List<Worker2> workers = new ArrayList<Worker2>(100);
	for (int i = 0; i < 100; i++) {
	    workers.add(new Worker2(url, semp));
	}
	int idx = 0;
	long maxInterval = 1 * 60 * 1000;
	long startTime = System.currentTimeMillis();
	long endTime = System.currentTimeMillis();

	long interval = endTime - startTime;
	
	while (interval < maxInterval) {
//	    threadPool.execute(new Worker2(url, semp));
	    idx = idx > 99 ? idx % 100 : idx++;
	    threadPool.execute(workers.get(idx));
	    endTime = System.currentTimeMillis();
	    interval = endTime - startTime;
	    System.out.println(interval);
	}
    }
}

class Worker3 implements Runnable {

    private String url = "";
    private Semaphore semp;

    public Worker3(String url, Semaphore semp) {
	this.url = url;
	this.semp = semp;
    }

    @Override
    public void run() {

	CloseableHttpClient closeableHttpClient = null;
	try {
	    semp.acquire(); // 获得许可
	    StatsdClient.getInstance("localhost", "8080").send("");
	} catch (Throwable e) {
	    e.printStackTrace();
	} finally {
	    try {// 关闭流并释放资源
		closeableHttpClient.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    semp.release(); // 释放许可
	}
    }
}

class Worker2 implements Runnable {

    private String url = "";
    private Semaphore semp;

    public Worker2(String url, Semaphore semp) {
	this.url = url;
	this.semp = semp;
    }

    @Override
    public void run() {

	CloseableHttpClient closeableHttpClient = null;
	try {
	    semp.acquire(); // 获得许可
	    // 创建HttpClientBuilder
	    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	    // HttpClient
	    closeableHttpClient = httpClientBuilder.build();
	    HttpGet httpget = new HttpGet(url);
	    RequestConfig config = RequestConfig.custom()
			.setSocketTimeout(30 * 1000)
			.setConnectTimeout(30 * 1000)
			.build();
	    
	    httpget.setConfig(config);
	    closeableHttpClient.execute(httpget);
//	    HttpResponse response = closeableHttpClient.execute(httpget);
//	    HttpEntity entity = response.getEntity();
//	    String resp = EntityUtils.toString(entity, Charset.forName("UTF-8"));
//	    System.out.println("线程：[" + Thread.currentThread().getName() + "], 登录响应成功, 返回报文[" + resp + "]");
	} catch (Throwable e) {
	    e.printStackTrace();
	} finally {
	    try {// 关闭流并释放资源
		closeableHttpClient.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    semp.release(); // 释放许可
	}
    }
}

class Worker implements Runnable {

    private String url = "";
    private Semaphore semp;

    public Worker(String url, Semaphore semp) {
	this.url = url;
	this.semp = semp;
    }

    @Override
    public void run() {

	Connection.Response res = null;
	try {
	    semp.acquire(); // 获得许可
	    res = Jsoup.connect(url).ignoreContentType(true).timeout(1800000).method(Method.GET).execute();
	    String resp = res.body();
	    System.out.println("线程：[" + Thread.currentThread().getName() + "], 登录响应成功, 返回报文[" + resp + "]");
	} catch (Throwable e) {
	    e.printStackTrace();
	} finally {
	    semp.release(); // 释放许可
	}
    }
}
