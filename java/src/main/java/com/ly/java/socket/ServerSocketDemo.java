package com.ly.java.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.FileUtils;

public class ServerSocketDemo {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket =new ServerSocket(10086);//1024-65535的某个端口
		//2、调用accept()方法开始监听，等待客户端的连接
		Socket socket = serverSocket.accept();
		//3、获取输入流，并读取客户端信息
		InputStream is = socket.getInputStream();
		InputStreamReader isr =new InputStreamReader(is);
		BufferedReader br =new BufferedReader(isr);
		String info =null;
		while((info=br.readLine())!=null){
			System.out.println("Hello,我是服务器，客户端说：" + info);
		}

		socket.shutdownInput();//关闭输入流
		//4、获取输出流，响应客户端的请求
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);
		String content = FileUtils.readFileToString(new File(
				"E:\\01_work\\02_cec\\03_需求\\10_tcp问题、\\2017032121\\tcp_monitor20170321210000.log"));
		//pw.write("Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！Hello World！");
		pw.write(content);
		pw.flush();
		  
		 
		//5、关闭资源
		pw.close();
		os.close();
		br.close();
		isr.close();
		is.close();
		socket.close();
		serverSocket.close(); 
	}

}
