/**
 * 
 */
package com.dup.test.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

/**
 * @author hugoyang
 * 
 */
public class 漏单处理 {
	// 全局计数器
	static AtomicInteger idx = new AtomicInteger(0);

	static Logger logger = LogManager.getLogger(漏单处理.class);

	private static Map<String, String> cookies = new HashMap<String, String>();

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException,
			InterruptedException {

		InputStream is = JsoupTest.class.getResourceAsStream("loudan.txt");
		List<String> content = IOUtils.readLines(is);
		is.close();

		try {
			StringBuilder sBuilder = new StringBuilder("(");
			for (String string : content) {
				sBuilder.append("'").append(string).append("',");
				System.out.println(string);
			}
			sBuilder.append(")");
			System.out.println(sBuilder.toString());
			logger.info("线程：[" + Thread.currentThread().getName() + "], 登录响应成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
