/**
 * 
 */
package com.dup.test.http;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.mopon.cec.core.util.StringUtil;

/**
 * @author hugoyang
 *
 */
public class JsoupTest2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		String content = FileUtils.readFileToString(new File("cookie.txt"));

		Document res = null;
		try {
			res = Jsoup
					.connect("http://www.iteye.com/")
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36")
					.header("Referer", "http://www.iteye.com/")
					.cookies(getMap(content))
					.get();
			System.out.println(res.data());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Map<String, String> cookies = new HashMap<String, String>();

	static Map<String, String> getMap(String str) {
		String[] arr = str.split(";");
		for (String s : arr) {
			cookies.put(StringUtil.trim(s.split("=")[0]), StringUtil.trim(s.split("=")[1]));
		}
		return cookies;
	}
}
