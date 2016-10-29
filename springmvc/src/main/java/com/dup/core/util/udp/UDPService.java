/**
 * 项   目  名：SCEC
 * 包          名：cn.mopon.cec.core.monitor
 * 文   件  名：UDPService.java
 * 版本信息：SCEC_Branches
 * 日          期：2015年9月2日-上午10:47:01
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.core.util.udp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author LRL
 * @version [SCEC_Branches, 2015年9月2日]
 * @备注：
 */
@Service("udpService")
public class UDPService {
	private static Logger log = LoggerFactory.getLogger(UDPService.class);

	/** 服务器IP **/
	@Value("${grafana.ip:211.147.2.173}")
	protected String statsd_ip = "211.147.2.173";
	/** 服务器IP **/
	@Value("${grafana.port:8125}")
	protected String statsd_port = "8125";

	/** 地面预警阀值 **/
	@Value("${requestGround.max.alarmTime:5000}")
	public String maxAlarmTime = "5000";


	public void sender(String content)
	{
	    try {
		StatsdClient.getInstance(statsd_ip, statsd_port).send(content);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
}
