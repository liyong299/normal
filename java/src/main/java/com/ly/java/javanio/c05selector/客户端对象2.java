/**
 * 项目名称：java
 * 文件包名：com.ly.java.javanio.c05selector
 * 文件名称：客户端对象2.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月11日 下午2:30:41
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.javanio.c05selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 功能描述：<p color="red">实现加载</p>
 * 文件名称：客户端对象2.java
 * @author ly
 */
public class 客户端对象2 {

    private Selector selector;
    private Charset charset = Charset.forName("utf-8");
    private SocketChannel sc = null;
    private InetSocketAddress serverAddress = new InetSocketAddress(8888);
    private String userName = "scott";
    private static String MSG_SPLIT = "=", REPLACE_STR = "@##$";
    private Scanner scanner;
    public void init() throws IOException
    {
	selector = Selector.open();
	
	// 连接远程服务器
	sc = SocketChannel.open(serverAddress);
	sc.configureBlocking(false);
	
	sc.register(selector, SelectionKey.OP_READ);
	
//	sc.connect(serverAddress);
	
	new Thread(new ClientWorker(selector, charset)).start();
	
	scanner = new Scanner(System.in);
	
	while (scanner.hasNextLine())
	{
	    String line = scanner.nextLine();
	    if ("".equals(line))
	    {
		System.out.println("空消息不允许！");
	    }
	    if (line.contains(MSG_SPLIT))
	    {
		line = line.replaceAll(MSG_SPLIT, REPLACE_STR);
	    }
	    line = userName + MSG_SPLIT + line;
	    sc.write(charset.encode(line));
	}
    }
    /**
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
	new 客户端对象2().init();
    }

}
