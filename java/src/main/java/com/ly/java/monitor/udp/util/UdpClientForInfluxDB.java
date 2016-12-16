package com.ly.java.monitor.udp.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台查询地址：http://172.16.10.16:8083/ Database:monitor 测试地址：172.16.10.16 8100
 * 查询方式：select * from "oct-youban1"
 * 
 * influxdb查询操作语法：
 * 查询所有表：SHOW MEASUREMENTS
 * 查询表中tag field:SHOW TAG KEYS FROM "measurement_name"  --measurement_name:表明
 * 查看一个field value: SHOW TAG VALUES FROM "measurement_name" WITH KEY = "tag_key" --measurement_name:表明 tag_key: tag field的名称
 * [[udp]] <code>Ip:172.16.10.16</code>
 * <code>enabled = true bind-address = ":8200" database = "otm01"</code>
 * <code>[[udp]]enabled = true bind-address = ":8300" database = "otm02"</code>
 * <p>
 * Description:
 * </p>
 * 
 * Spring集成配置 
 * <bean id="udpSendClientUtil" class="com.mopon.monitor.udp.util.UdpClientForInflusDB"> 
 * 	<constructor-arg name="host" value="${warn.udp.host}" /> 
 * 	<constructor-arg name="port" value="${warn.udp.port}" /> 
 * </bean>
 * 
 * @date 2016年9月23日
 * @author 王方威
 * @version 1.0
 * <p> Company:Mopon </p>
 * <p> Copyright:Copyright(c)2013 </p>
 */
public class UdpClientForInfluxDB {

	private static final String CHARSET_NAME = "utf-8";

	/**
	 * 逗号字符
	 */
	private static String COMMA_CHAR = ",";

	/**
	 * 空格字符
	 */
	private static String SPACE_CHAR = " ";

	/**
	 * 等于号字符
	 */
	private static String EQUAL_CHAR = "=";

	private static String QUOTATION_MARKS = "\"";
	
	/**
	 * Udp发送客户端
	 */
	private UDPSender sender;

	private UdpClientForInfluxDB(String host, int port) throws UnknownHostException, SocketException {
		sender = new UDPSender(host, port);
	}

	public static void main(String[] args) throws IOException {
		UdpClientForInfluxDB sc = new UdpClientForInfluxDB("172.16.10.16", 8200);
		// select * from "SC"
		String measurement = "SC10";

		Map<String, String> tagFieldMap = new HashMap<String, String>();
		tagFieldMap.put("Module", "OTA");
		tagFieldMap.put("bussiness", "placeOrder");
		tagFieldMap.put("scenic", "S0001");

		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("refTransactionId", "00002");
		fieldMap.put("message", "订单已存在");
		fieldMap.put("extfield", "订单已11在");
		sc.sendUdpMessage(measurement, tagFieldMap, fieldMap);
	}

	/**
	 * 通过UDP发送报文到influxdb的监控发送方法,该方法不用发送预警服务的IP地址，改方法会自动添加IP信息到tarField中
	 * field
	 * 
	 * influxdb的协议规范:
	 * <code>weather,location=us-midwest temperature=82 1465839830100400200</code>
	 * <code>|    -------------------- --------------  |</code>
	 * <code>|             |             |             |</code>
	 * <code>|             |             |             |</code>
	 * <code>+-----------+--------+-+---------+-+---------+</code>
	 * <code>|measurement|,tag_set| |field_set| |timestamp|</code>
	 * <code>+-----------+--------+-+---------+-+---------+</code>
	 * 
	 * 报文中会转译的特殊字符 : 
	 * 	<code>></code>:&lt; <code><</code>:&gt; <code>&</code>
	 * :&amp; <code>‘</code>:&apos; <code> “</code>:&quot; <code>,</code>:&#44;
	 * <code>=</code>:&#61; <code>Space</code>:&nbsp;
	 * 
	 * @param measurement
	 *            : influxdb的表名，业务平台调用时候一般传送项目名，eg:NG,SCEC,SC……,该字段不能为空，
	 *            不发送到influxdb
	 * @param tagField
	 *            :该表下的索引字段，主要是将来会用于统计分组字段eg:院线号，子项目，业务编码(下单，验票……)
	 *            <code>map.key</code>:列明或者属性名 (column,field)
	 *            <code>map.value</code>:列值或者属性值 (cloumnValue,fieldValue)
	 *            <b>tagField中不能包含特殊字符：> < & ‘ “ , = space</b>
	 * @param commonField
	 *            :该表下的没索引的字段，主要是信息字段，提供定位问题的辅助字段 <code>map.key</code>:列明或者属性名
	 *            (column,field) <code>map.value</code>:列值或者属性值
	 *            (cloumnValue,fieldValue)
	 *            <b>tagField中不能包含特殊字符：> < & ‘ “ , = space 将会别替换</b>
	 * @throws IOException
	 */
	public void sendUdpMessage(String measurement, Map<String, String> tagFieldMap, Map<String, String> commonField)
			throws IOException {
		if (measurement != null || !"".equals(measurement)) {
			StringBuilder message = new StringBuilder();
			message.append(measurement).append(COMMA_CHAR);
			message.append("ip").append(EQUAL_CHAR).append(getLocalIpStr()).append(COMMA_CHAR);

			for (Map.Entry<String, String> entry : tagFieldMap.entrySet()) {
				message.append(entry.getKey()).append(EQUAL_CHAR).append(entry.getValue()).append(COMMA_CHAR);
			}
			message.deleteCharAt(message.length() - 1);
			message.append(SPACE_CHAR);

			for (Map.Entry<String, String> entry : commonField.entrySet()) {
				message.append(entry.getKey()).append(EQUAL_CHAR).append(QUOTATION_MARKS)
						.append(fiterString(entry.getValue())).append(QUOTATION_MARKS).append(COMMA_CHAR);
			}
			message.deleteCharAt(message.length() - 1);
			System.out.println(message);

			byte[] buf = message.toString().getBytes(CHARSET_NAME);
			sender.send(buf, 0, buf.length);
		}
	}

	private String fiterString(String message) {
		String res = message.replaceAll(">", "&lt;").replaceAll("<", "&gt;").replaceAll("&", "&amp")
				.replaceAll("‘", "&apos;").replaceAll("“", "&quot;").replaceAll("=", "&#61").replaceAll(" ", "&nbsp;")
				.replaceAll("\"", "&quot;").replaceAll(",", "&#44;");

		return res;
	}

	/**
	 * win下获取机器ip
	 * 
	 * @author LRL
	 * @return
	 * @time 下午12:07:00
	 */
	private String getWindows() {
		String winip = null;
		try {
			InetAddress ia = InetAddress.getLocalHost();
			winip = ia.getHostAddress().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return winip;
	}

	/**
	 * linux 下获取机器ip
	 * 
	 * @author LRL
	 * @return
	 * @time 下午12:07:18
	 */
	private String getLinux() {
		String linuxip = null;
		InetAddress ip = null;
		try {
			boolean findFlag = false;
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				if (findFlag) {
					break;
				}
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				// 遍历所有ip
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					ip = (InetAddress) ips.nextElement();
					// 127.开头的都是lookback地址
					if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
						findFlag = true;
						break;
					}
				}
			}
			if (null != ip) {
				linuxip = ip.getHostAddress();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linuxip;
	}

	private String getLocalIpStr() {
		String ip;
		try {
			if (System.getProperties().getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
				ip = getWindows();
			} else {
				ip = getLinux();
			}

			if (ip != null && !"".equals(ip)) {
				ip = ip.replace(".", "-");
			}
		} catch (Exception e) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	// public void sendUdpMessage(String message) throws IOException {
	// byte[] buf = message.getBytes(CHARSET_NAME);
	// sender.send(buf, 0, buf.length);
	// }

}
