package com.mopon.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * <p>Description:HTTP CLIENT类用于发送HTTP请求 </p>
 * @date 2014年12月5日
 * @author Aaron
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class HttpProcess {

	private Logger log = LoggerFactory.getLogger(HttpProcess.class);

	/**
	 * UTF-8编码
	 */
	private final String CONTENT_CHARSET_UTF8 = "UTF-8";

	/**
	 * 方法用途: 发送GET请求
	 * 实现步骤: 用于发送GET请求，分为RESTUF和KV方式<br>
	 * @param:String url发送的请求地址 Map params 参数MAP boolean type 发送类型
	 * @return byte[] 得到响应的byte数据数组
	 */
	public byte[] doGet(String url, Map<String, String> params, boolean type) {
		url = url.replaceAll("\\\\", "/");
		HttpClient httpClient = new HttpClient();
		// httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, CONTENT_CHARSET_GBK);
		List<Header> headers = new ArrayList<Header>();
		headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
		httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
		StringBuffer queryString = new StringBuffer("");
		if (type) {
			url += "/";
		} else {
			url += "?";
		}
		if (params != null) {
			try {
				for (String key : params.keySet()) {
					String param = "";
					if (type) {
						param = URLEncoder.encode(params.get(key), CONTENT_CHARSET_UTF8) + "/";
					} else {
						param = key + "=" + URLEncoder.encode(params.get(key), CONTENT_CHARSET_UTF8) + "&";
					}
					queryString.append(param);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			queryString.delete(queryString.length() - 1, queryString.length());
		}
		GetMethod getMethod = new GetMethod(url + queryString.toString());
		int statusCode;
		try {
			statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed:" + getMethod.getStatusLine());
			}
			// 读取内容
			return getMethod.getResponseBody();
			// 处理内容
			// System.out.println(new String(responseBody));
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 方法用途: 发送POST请求
	 * 实现步骤: 用于发送POST请求<br>
	 * @param:String url发送的请求地址 Map params 参数MAP
	 * @return byte[] 得到响应的byte数据数组
	 * @throws IOException
	 */
	public byte[] doPost(String url, Map<String, String> params, String encode) throws IOException {
		url = url.replaceAll("\\\\", "/");
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);//请求超时 60秒
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encode);
		NameValuePair[] nameValuePairs = new NameValuePair[params.size()];
		if (params != null) {
			// try {
			int index = 0;
			for (String key : params.keySet()) {
				NameValuePair nameValuePair = new NameValuePair(key, params.get(key));
				nameValuePairs[index] = nameValuePair;
				index++;
			}
			/*
			 * } catch (UnsupportedEncodingException e) { e.printStackTrace(); }
			 */
		}
		try {

			postMethod.setRequestBody(nameValuePairs);

			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed:" + postMethod.getStatusLine());
			}
			return postMethod.getResponseBody();
		} catch (HttpException e) {
			e.printStackTrace();
		}finally {
			postMethod.releaseConnection();
		}
		return null;
	}

	/**
	 * 方法用途: 发送POST请求
	 * 实现步骤: 用于发送POST请求<br>
	 * @param:String url发送的请求地址 JSON params 参数JSON
	 * @return byte[] 得到响应的byte数据数组
	 * @throws IOException
	 */
	public static byte[] doPostJSON(String url, Object param, String encode) throws IOException {
			url = url.replaceAll("\\\\", "/");
			HttpClient httpClient = new HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(6000);//请求超时 6秒
			PostMethod postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encode);
			String str="zip=false&param=";
			try {
				// 接收参数json列表
				JSONObject obj   = JSONObject.fromObject(param);
				RequestEntity requestEntity = new StringRequestEntity(str+obj.toString(),"text/xml","UTF-8");
				postMethod.setRequestEntity(requestEntity);
				postMethod.releaseConnection();
				//postMethod.setRequestBody(str+obj.toString());
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed:" + postMethod.getStatusLine());
			}
			return postMethod.getResponseBody();
		} catch (HttpException e) {
			e.printStackTrace();
		}finally {
			postMethod.releaseConnection();
		}
		return null;
	}


	 /**
	      * 获取二进制的报文体，并转换成字符串
	      */
	 public String getContext(HttpServletRequest request) throws ServletException, IOException {

	    	 String resp = "";
	    	 try{
	    	   // 获取页面内容
	    	   java.io.InputStream in = request.getInputStream();
	    	   java.io.BufferedReader breader = new BufferedReader(
	    	     new InputStreamReader(in, "UTF-8"));
	    	   String str = breader.readLine();
	    	   while (str != null) {
		    	    resp +=str;
		    	    str = breader.readLine();
		    	   }
	    	  } catch (Exception e) {
	    		  e.printStackTrace();
	    	  } finally {
	    	   }

	    	 return resp;


	 }

	public static void main(String[] args) throws IOException {

	}



}
