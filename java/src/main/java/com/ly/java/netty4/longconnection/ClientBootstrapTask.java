/**
 * 项目名称：java
 * 文件包名：com.ly.java.netty4.longconnection
 * 文件名称：CheckClientTask.java
 * 版本信息：SCEC_Branches
 * 生成日期：2017年3月4日 下午4:55:41
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.netty4.longconnection;


/**
 * @功能描述：向客户端发送心跳请求
 * @文件名称：CheckClientTask.java
 * @author ly
 */
public class ClientBootstrapTask implements Runnable {

	private String clientSeq;

	public ClientBootstrapTask(String clientSeq) {
		this.clientSeq = clientSeq;
	}
	@Override
	public void run() {
		ClientForLongConnection bootstrap = new ClientForLongConnection(clientSeq);
		try {
			bootstrap.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
