/**
 * 项目名称：java
 * 文件包名：com.ly.java.javanio.c04channeldatatrans
 * 文件名称：FileChannel数据传输.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月9日 下午8:22:23
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.javanio.c04channeldatatrans;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import org.apache.commons.io.FileUtils;


/**
 * 功能描述：
 * <p color="red">
 * 在Java NIO中，如果两个通道中有一个是FileChannel，
 * 那你可以直接将数据从一个channel（译者注：channel中文常译作通道）传输到另外一个channel。
 * </p>
 * 文件名称：FileChannel数据传输.java
 * 
 * @author ly
 */
public class FileChannel数据传输 {
    public static void main(String[] args) throws IOException {
//	testFileCopyByChannel();
	testFileCopyByStream();
    }
    
    public static void testFileCopyByStream() throws IOException {
   	long startTime = System.nanoTime();

   	for (int i = 0; i < 1000; i++) {
   	    File fromFile = new File("tttt.log");
   	    File toFile = new File("testFileCopyByStream.log");
   	    FileUtils.copyFile(fromFile, toFile);
   	}

   	long endTime = System.nanoTime();

   	String msg2 = String.format("[Flag2：%s][执行时间： %s 毫秒]", "===", (endTime - startTime) / 1000000);

   	printLog(msg2);
       }

    public static void testFileCopyByChannel() throws IOException {
	long startTime = System.nanoTime();

	for (int i = 0; i < 1000; i++) {
	    RandomAccessFile fromFile = new RandomAccessFile("tttt.log", "rw");
	    FileChannel fromChannel = fromFile.getChannel();

	    RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
	    FileChannel toChannel = toFile.getChannel();

	    long position = 0;
	    long count = fromChannel.size();

	    toChannel.transferFrom(fromChannel, position, count);
	    toFile.close();
	    toChannel.close();
	    fromFile.close();
	    fromChannel.close();
	}

	long endTime = System.nanoTime();

	String msg2 = String.format("[Flag2：%s][执行时间： %s 毫秒]", "===", (endTime - startTime) / 1000000);

	printLog(msg2);
    }

    private static void printLog(String str) {
	System.out.println(str);
    }
}
