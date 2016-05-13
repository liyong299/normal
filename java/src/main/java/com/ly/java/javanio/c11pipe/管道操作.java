/**
 * 项目名称：java
 * 文件包名：com.ly.java.javanio.c11pipe
 * 文件名称：管道操作.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月12日 下午8:20:39
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.javanio.c11pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：管道操作.java
 * 
 * @author ly
 */
public class 管道操作 {

    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	Pipe pipe = Pipe.open();
	new SendThread(pipe.sink()).start();
	new ReadThread(pipe.source()).start();
    }
}

class SendThread extends Thread {
    private Pipe.SinkChannel skin;

    public SendThread(Pipe.SinkChannel skin) {
	this.skin = skin;
    }

    public void run() {
	ByteBuffer read = ByteBuffer.allocate(48);
	int cnt = 0;
	while (true) {
	    read.clear();
	    read.put(("写入次数： " + ++cnt).getBytes());
	    try {
		read.flip();
		skin.write(read);
		System.out.println(cnt);
		Thread.currentThread().sleep(2000l);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }
}

class ReadThread extends Thread {
    private Pipe.SourceChannel source;

    public ReadThread(Pipe.SourceChannel source) {
	this.source = source;
    }

    public void run() {
	ByteBuffer read = ByteBuffer.allocate(48);
	while (true) {
	    read.clear();
	    try {
		int count = 0;
		if ((count = source.read(read)) > 0) {
		    System.out.println(new String(read.array()));
		}

		Thread.currentThread().sleep(1000l);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }
}