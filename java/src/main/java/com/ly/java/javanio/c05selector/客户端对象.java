/**
 * 项目名称：java
 * 文件包名：com.ly.java.javanio.c05selector
 * 文件名称：客户端对象.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月11日 上午9:13:19
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.javanio.c05selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：客户端对象.java
 * 
 * @author ly
 */
public class 客户端对象 {

    /* 标识数字 */
    private static int flag = 0;
    /* 缓冲区大小 */
    private static int BLOCK = 4096;
    /* 接受数据缓冲区 */
    private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /* 发送数据缓冲区 */
    private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    /* 服务器端地址 */
    private final static InetSocketAddress SERVER_ADDRESS = new InetSocketAddress("localhost", 8888);

    private static AtomicInteger sendTimes = new AtomicInteger();

    /**
     * 
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
	// 打开通道
	SocketChannel socketChannel = SocketChannel.open();

	// 设置为非阻塞
	socketChannel.configureBlocking(false);

	// 打开选择器
	Selector selector = Selector.open();

	// 将客户端套接字通道注册到选择器
	socketChannel.register(selector, SelectionKey.OP_CONNECT);

	// 客户端连接到服务端
	socketChannel.connect(SERVER_ADDRESS);

	Set<SelectionKey> selectionKeys;
	Iterator<SelectionKey> iterator;
	SelectionKey selectionKey;

	while (true) {
	    // 选择一组键，其相应的通道已为IO操作已经准备就绪
	    // 此方法执行处于阻塞
	    selector.select();
	    // 返回此选择器已选择键集
	    selectionKeys = selector.selectedKeys();
	    iterator = selectionKeys.iterator();
	    while (iterator.hasNext()) {
		selectionKey = iterator.next();
		handler(selector, selectionKey);
		Thread.currentThread().sleep(1000l);
		int currentCnt = sendTimes.get();
		if (currentCnt > 3) {
		    return;
		}
	    }
	    selectionKeys.clear();
	}
    }

    private static void handler(Selector selector, SelectionKey selectionKey) throws IOException {
	SocketChannel client;
	String receiveText;
	String sendText;
	int count = 0;
	if (selectionKey.isConnectable()) {
	    System.out.println("客户端已经连接");
	    client = (SocketChannel) selectionKey.channel();
	    // 判断此套接字通道是否已经连接上，完成连接过程
	    if (client.isConnectionPending()) {
		client.finishConnect();
		System.out.println("客户端连接完成");
		sendbuffer.clear();
		sendbuffer.put("服务端我来了".getBytes());
		sendbuffer.flip();
		client.write(sendbuffer);
	    }
	    client.register(selector, SelectionKey.OP_READ);
	} else if (selectionKey.isReadable()) {
	    client = (SocketChannel) selectionKey.channel();
	    // 将缓冲区清空以备下次读取
	    receivebuffer.clear();
	    // 读取服务器发送来的数据到缓冲区
	    count = client.read(receivebuffer);

	    if (count > 0) {
		receiveText = new String(receivebuffer.array(), 0, count);
		System.out.println("服务端说： " + receiveText);
		client.register(selector, SelectionKey.OP_WRITE);
	    }
	} else if (selectionKey.isWritable()) {
	    sendbuffer.clear();
	    client = (SocketChannel) selectionKey.channel();
	    sendText = "我来自客户端：  " + flag++;
	    sendbuffer.put(sendText.getBytes());

	    // 将缓冲区各标志位复位，因为向里面put了数据，标志位被改变，想要读取数据，发送到服务端，需要复位。
	    sendbuffer.flip();

	    client.write(sendbuffer);
	    System.out.println("客户端向服务端发送了： " + sendText);
	    client.register(selector, SelectionKey.OP_READ);

	    int currentCnt = sendTimes.incrementAndGet();
	    if (currentCnt > 3) {
		client.close();
		return;
	    }
	}
    }

}
