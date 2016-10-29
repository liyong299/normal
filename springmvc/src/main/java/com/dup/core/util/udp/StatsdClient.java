package com.dup.core.util.udp;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * 发送监控数据的客户端, 数据会以UDP报文发给指定的influxdb, 并且最终存储到Graphite中,
 * 以Grafana的图表形式展现.一般在一个程序中，都是以单例形式出现，即该类是线程安全的。
 * 
 * @author ruilu.li
 * @version [SCEC V1.6.2, 2015年12月30日]
 * @备注：
 */
public class StatsdClient {

	private static final String CHARSET_NAME = "utf-8";

	private UDPSender sender;

	private static StatsdClient statsd;

	/**
	 * 
	 * @author LRL
	 * @param host
	 * @param port
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 * @time 上午9:12:35
	 */
	public static StatsdClient getInstance(String host, String port)
			throws UnknownHostException, SocketException {
		if (null == statsd) {
			synchronized (StatsdClient.class) {
				if (null == statsd) {
					statsd = new StatsdClient(host, Integer.parseInt(port));
				}
			}
		}
		return statsd;
	}

	private StatsdClient(String host, int port) throws UnknownHostException,
			SocketException {
		sender = new UDPSender(host, port);
	}

	public void send(String content) throws IOException
	{	    
	    send(content, Charset.forName(CHARSET_NAME));
	}
	
	public void send(String content, Charset charSet) throws IOException
	{
	    byte[] buf = content.getBytes(charSet);
	    
	    send(buf, 0, buf.length);
	}
	
	public void send(byte[] buf, int offset, int length) throws IOException
	{
	    sender.send(buf, offset, length);
	}
}
