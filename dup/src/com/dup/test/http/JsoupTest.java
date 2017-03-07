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
public class JsoupTest {
	// 全局计数器
	static AtomicInteger idx = new AtomicInteger(0);

	private static String loginURL = "";
	private static String userName = "";
	private static String passwd = "";
	private static String syncURL = "";
	private static int timeout = 300000;
	private static int runTimes = 4;
	
	static Logger logger = LogManager.getLogger(JsoupTest.class);
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		// 加载需要处理的ID
		InputStream is= JsoupTest.class.getResourceAsStream("CEC_Cinema.txt");   
        List<String> content = IOUtils.readLines(is);
        is.close();
//		System.out.println(content);
		
		// 加载配置项
		InputStream is2= JsoupTest.class.getResourceAsStream("url.txt");  
		List<String> content2 = IOUtils.readLines(is2);
		is2.close();
		
//		System.out.println(content2);

		
		// 初始化公共参数，公共参数动态修改不起作用。
		syncURL = content2.get(0);
		loginURL = content2.get(1);
		userName = content2.get(2);
		passwd = content2.get(3);
		timeout = Integer.valueOf(content2.get(4));
		runTimes = Integer.valueOf(content2.get(5));
		
		login(loginURL, userName, passwd);
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(64, 80, 3000000,
				TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5000));

		for (int i = 0; i < runTimes; i++)
		{
			for (String str : content)
			{
				MyTask myTask = new MyTask(str);
				executor.execute(myTask);
				System.out.println("线程池中线程数目：" + executor.getPoolSize()
						+ "，队列中等待执行的任务数目：" + executor.getQueue().size()
						+ "，已执行完的任务数目：" + executor.getCompletedTaskCount());
				
				logger.info("线程池中线程数目：" + executor.getPoolSize()
						+ "，队列中等待执行的任务数目：" + executor.getQueue().size()
						+ "，已执行完的任务数目：" + executor.getCompletedTaskCount());
				
//				if (idx.get() > 10) break;
//				Thread.sleep(10000);
			}
		}
	}
	
	private static Map<String, String> cookies = new HashMap<String, String>();
	public static synchronized void login(String url, String username, String passwd)
	{
		Connection.Response res = null;
		try {
			res = Jsoup.connect(url)
					  .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
					  .data("username",username)
					  .data("password",passwd)
					  .timeout(timeout)
					  .method(Method.GET)
					  .execute();
			cookies = res.cookies();
			logger.info("线程：[" + Thread.currentThread().getName() + "], 登录响应成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	static class MyTask implements Runnable{

		private String cid;
		public MyTask(String cid)
		{
			this.cid = cid;
		}
		
		public void run() {
			Connection.Response res = null;
			int curnum = idx.incrementAndGet();
			if (curnum % 10 == 9)
			{
				login(loginURL, userName, passwd);
			}
			long perStartTime = System.currentTimeMillis();
			try
			{
				res = Jsoup.connect(syncURL)
						  .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
						  .data("cinemaId", cid)
						  .timeout(timeout)
						  .cookies(cookies)
						  .method(Method.GET)
						  .execute();
				logger.info("线程：[" + Thread.currentThread().getName() + "], 同步排期正常：" + res.body());
				logger.info("线程：[" + Thread.currentThread().getName() + "], 本次[" + idx.get() + "]耗时： " + (System.currentTimeMillis() - perStartTime) + ", url:" + res.url());
				System.out.println("线程：[" + Thread.currentThread().getName() + "], 本次[" + idx.get() + "]耗时： " + (System.currentTimeMillis() - perStartTime) + ", url:" + res.url());
				
			}
			catch(Throwable ex)
			{
				logger.info("线程：[" + Thread.currentThread().getName() + "], 同步排期异常1：" + ex.getMessage());
				ex.printStackTrace();
				try {
					login(loginURL, userName, passwd);
					res = Jsoup.connect(syncURL)
							  .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
							  .data("cinemaId",cid)
							  .timeout(timeout * 3)
							  .cookies(cookies)
							  .method(Method.GET)
							  .execute();
					
					logger.info("线程：[" + Thread.currentThread().getName() + "], 同步排期重试：" + res.body());
					logger.info("本次[" + idx.get() + "]耗时： " + (System.currentTimeMillis() - perStartTime) + ", url:" + res.url());
					System.out.println("本次[" + idx.get() + "]耗时： " + (System.currentTimeMillis() - perStartTime) + ", url:" + res.url());
				} catch (IOException e) {
					logger.info("线程：[" + Thread.currentThread().getName() + "], 同步排期异常2：" + e.getMessage());
					e.printStackTrace();
					
				}
			}
		}
	}
}
