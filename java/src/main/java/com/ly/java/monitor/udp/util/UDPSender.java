package com.ly.java.monitor.udp.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 * 通用的UDP报文发送类,
 * 
 */
public class UDPSender {

	/* StatsD服务器的IP地址 */
	private InetAddress address;

	/* StatsD服务器的UDP端口 */
	private int port;

	/* UDP套接字 */
	private DatagramSocket socket;

	/**
	 * 构造函数, 准备UDP发送的准备工作
	 * 
	 * @param host
	 *            StatsD服务器的IP地址
	 * @param port
	 *            StatsD服务器的UDP端口
	 * @throws UnknownHostException
	 *             StatsD服务器的IP地址解析失败或者非法
	 * @throws SocketException
	 *             套接字建立异常
	 */
	protected UDPSender(String host, int port) throws UnknownHostException,
			SocketException {
		address = InetAddress.getByName(host);
		this.port = port;
		this.socket = new DatagramSocket();
	}

	/**
	 * 发送指定缓冲区的数据到构造函数指定的StatsD服务器端口
	 * 
	 * @param buf
	 *            UDP报文缓冲区
	 * @param offset
	 *            待发送的UDP报文的起始偏移量
	 * @param length
	 *            待发送的UDP报文的长度
	 * @throws IOException
	 *             报文发送失败
	 */
	protected void send(byte[] buf, int offset, int length) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, offset, length);
		packet.setAddress(address);
		packet.setPort(port);
		socket.send(packet);
	}
}
