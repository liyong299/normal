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
public class 订单调用OC地址验证 {
	// 全局计数器
	static AtomicInteger idx = new AtomicInteger(0);

	private static String loginURL = "";
	private static String userName = "";
	private static String passwd = "";
	private static String syncURL = "";
	private static int timeout = 300000;
	private static int runTimes = 4;
	
	static Logger logger = LogManager.getLogger(订单调用OC地址验证.class);
	
	private static Map<String, String> cookies = new HashMap<String, String>();
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {

		test();
	}
	
	public static void test1() throws IOException
	{
		InputStream is= JsoupTest.class.getResourceAsStream("oc_web_url.txt");   
        List<String> content = IOUtils.readLines(is);
        is.close();
        
		Connection.Response res = null;
		try {
			String url = "http://wtest.piaowutong.cc:9015/api/ticket/v1/queryShows.json?channelCode=0001&cinemaCode=1000008&startDate=2015-11-22&status=1&sign=00003";
			
			res = Jsoup.connect(url )
//					  .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
					  .timeout(timeout)
					  .method(Method.GET)
					  .execute();
			cookies = res.cookies();
			
//			System.out.println("result : " + res.body().toString());
			logger.info("线程：[" + Thread.currentThread().getName() + "], 登录响应成功");
			System.out.println("----------------------------------------------");
			
			String url2 = "http://wtest.piaowutong.cc:9015/api/ticket/v1/queryShows.json?";
			
			res = Jsoup.connect(url2 )
//					  .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
					  .timeout(timeout)
					  .method(Method.POST)
					  .data("channelCode", "168")
					  .data("cinemaCode", "42013901")
					  .data("startDate", "2015-12-28")
					  //.data("status", "1")
					  .data("sign", "00000")
					  .execute();
			cookies = res.cookies();
			
			System.out.println("result : " + res.body().toString());
			
			test();
			
			logger.info("线程：[" + Thread.currentThread().getName() + "], 登录响应成功");
			System.out.println("----------------------------------------------");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void test() throws IOException
	{
		String url2 = "http://172.16.34.20:9015/api/ticket/v1/queryShows.json?";
		
		Connection.Response res = Jsoup.connect(url2 )
//				  .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
				  .timeout(timeout)
				  .method(Method.POST)
				    .data("channelCode", "168")
					  .data("cinemaCode", "42013901")
					  .data("startDate", "2015-12-28")
				  .data("status", "")
				  .data("sign", "00003")
				  .execute();
		cookies = res.cookies();
		
		System.out.println("result : " + res.body().toString());
	}
}
