/**
 * 项目名称：java
 * 文件包名：com.ly.java.javanio.c05selector
 * 文件名称：服务器端代码.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月10日 下午8:09:15
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.javanio.c05selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 功能描述：
 * <p color="red">
 * 启动一个服务的通道
 * </p>
 * 文件名称：服务器端代码.java
 * 
 * @author ly
 */
public class 服务器端对象 {

    private Selector selector;
    /* 标识数字 */
    private int flag = 0;
    /* 缓冲区大小 */
    private int BLOCK = 4096;
    /* 接受数据缓冲区 */
    private ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /* 发送数据缓冲区 */
    private ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);

    public 服务器端对象(int port) {
	try {
	    // 开启一个选择器
	    selector = Selector.open();

	    // 开启服务端套接字通道
	    ServerSocketChannel ssc = ServerSocketChannel.open();

	    // 服务端套接字设置为非阻塞的。
	    ssc.configureBlocking(false);

	    // 检索通道的套接字
	    ServerSocket ss = ssc.socket();

	    ss.bind(new InetSocketAddress(port));

	    // 将套接字通道注册到选择器上
	    ssc.register(selector, SelectionKey.OP_ACCEPT);

	    System.out.println("-----服务端启动完成-----");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * 监听
     * 
     * @throws IOException
     */
    public void listen() throws IOException {
	while (true) {
	    // 选择一组键，并且相应的通道已经打开。 // TODO
	    selector.select();

	    // 返回此选择器的已选择键集
	    Set<SelectionKey> selectionKeys = selector.selectedKeys();
	    System.out.println("当前通道数：  " + selectionKeys.size());

	    Iterator<SelectionKey> iterator = selectionKeys.iterator();
	    while (iterator.hasNext()) {
		SelectionKey selectionKey = iterator.next();
		iterator.remove();
		try {
		    handlerKey(selectionKey);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }
	}
    }

    /**
     * 处理了当前请求
     * 
     * @param selectionKey
     * @throws IOException
     */
    private void handlerKey(SelectionKey selectionKey) throws IOException {

	SocketChannel client = null;
	String receiveText;
	String sendText;
	int count = 0;

	if (selectionKey.isAcceptable()) // 检测此通道是否已经准备接受新的套接字请求
	{
	    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel(); // 获取此键的通道

	    // 接受到此通道套接字的连接。
	    // 此方法返回的套接字通道（如果有）将处于阻塞模式。
	    client = ssc.accept();

	    client.configureBlocking(false); // 设置为非阻塞

	    // 注册选择器，并设置为读取模式，收到一个连接请求，然后起一个SocketChannel，并注册到selector上，之后这个连接的数据，就由这个SocketChannel处理
	    client.register(selector, SelectionKey.OP_READ);
	} else if (selectionKey.isReadable()) {
	    // 返回为之创建此键的通道。
	    client = (SocketChannel) selectionKey.channel();

	    // 将缓冲区清空以备下次读取
	    receivebuffer.clear();

	    // 读取服务器发送来的数据到缓冲区中
	    try {  // 服务端在客户端不能已关闭时，要自动关闭自己的和客户端的连接。
		count = client.read(receivebuffer);
		if (count > 0) {
		    receiveText = new String(receivebuffer.array(), 0, count);
		    System.out.println("客户端说: " + receiveText);
		    client.register(selector, SelectionKey.OP_WRITE);
		}
		else
		{
		    System.out.println("客户端已经关闭，服务端也关闭当前连接" + selectionKey);
		    client.close();
		}
	    } catch (Exception ex) {
		System.out.println("发生异常，关闭当前连接。" + selectionKey);
		client.close();
	    }

	} else if (selectionKey.isWritable()) {

	    // 将缓冲区清空以备下次写入
	    sendbuffer.clear();

	    // 返回为之创建此键的通道。
	    client = (SocketChannel) selectionKey.channel();
	    sendText = "message from server--" + flag++;
	    // 向缓冲区中输入数据

	    sendbuffer.put(sendText.getBytes());
	    // 将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位

	    sendbuffer.flip();

	    // 输出到通道
	    client.write(sendbuffer);
	    System.out.println("服务器端向客户端发送数据 ：" + sendText);
	    client.register(selector, SelectionKey.OP_READ);
	}
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
	int port = 8888;
	服务器端对象 server = new 服务器端对象(port);
	server.listen();
    }
}
