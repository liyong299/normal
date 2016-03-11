package com.mopon.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>Description:解析project.xml </p>
 * @date 2015年4月13日
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public final class ProjectXml {
	
	private static Logger logger = LoggerFactory.getLogger(ProjectXml.class);
	
	private final static String bodyReg = new StringBuilder("<").append("project")
            .append(">(.*?)</").append("project").append(">").toString() ;
	private final static Pattern bodyPattern = Pattern.compile(bodyReg, Pattern.DOTALL);
	//取标签
	private final static String reg = new StringBuilder("<(.*?)>(.*?)</.*?>").toString() ;
	private final static Pattern pattern = Pattern.compile(reg, Pattern.DOTALL);
	/**
	 * 缓存容器
	 */
	public static Map<String,Object> cacheContext = new HashMap<String,Object>();
	
	public static void innit() {
		logger.info("加载ProjextXml start......");
		cacheContext.clear();
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("UTF-8");
		Document document = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("/config/project.xml");//兼容weblogic
			if(null == is){
				logger.error(" 配置文件project.xml 没有正常加载 InputStream="+is);
			}
			document = saxReader.read(is);
			Element root = document.getRootElement();
			Iterator it = root.element("project").elementIterator();
			while (it.hasNext()) {
				Element filed = (Element) it.next();
				cacheContext.put(filed.getName(), (filed.getData()+"").trim());
			}
		} catch (Exception e) {
			logger.error("项目启动配置文件缓存加载异常...",e);
		}finally{
			try {
				if(null !=is) is.close();
			} catch (IOException e) {
				logger.error("关闭流异常",e);
			}
		}
		logger.info("cacheContext="+cacheContext.toString());
		logger.info("加载ProjextXml end......");
	}
	
	public  static  String getValue(String propertyName) {
		String propertyVal = cacheContext.get(propertyName) == null ? null : cacheContext.get(propertyName).toString();
		return propertyVal;
	}
	
	/**
	 * 
	 * 功能：解析xml
	 * 1. 文件格式 <message><result>S</result></message>
	 * @param str xml文件
	 * @return
	 */
	public static Map<String,String> getContentFromXMLForTag(String str){
		
		
		SAXReader saxReader = new SAXReader();
		Map<String,String> map = new HashMap<String, String>();
		try {
			saxReader.setEncoding("UTF-8");
			 
			Document document = saxReader.read(new ByteArrayInputStream(str.getBytes()));
			
			// 获取根元素
			Element root = document.getRootElement();
			
			Iterator it = root.elementIterator();
			while (it.hasNext()) {
				Element filed = (Element) it.next();
				map.put(filed.getName(), (filed.getData()+"").trim());
			}
		} catch (Exception e) {
			logger.info(" ParseXml.getContentFromXMLForTag 解析 xml 错误:",e);
		}
		return map;
	}
	
	/**
	 * 
	 * 功能：解析xml
	 * 1. 文件格式 <message><result>S</result></message>
	 * @param str xml文件
	 * @return
	 */
	public static Map<String,String> getContentFromXMLForTagGB2312(String str){
		
		
		SAXReader saxReader = new SAXReader();
		Map<String,String> map = new HashMap<String, String>();
		try {
			saxReader.setEncoding("GB2312");
			 
			Document document = saxReader.read(new ByteArrayInputStream(str.getBytes()));
			
			// 获取根元素
			Element root = document.getRootElement();
			
			Iterator it = root.elementIterator();
			while (it.hasNext()) {
				Element filed = (Element) it.next();
				map.put(filed.getName(), (filed.getData()+"").trim());
			}
		} catch (Exception e) {
			logger.info(" ParseXml.getContentFromXMLForTagGB2312 解析 xml 错误:",e);
		}
		return map;
	}
	
	
	/**
	 * 
	 * 功能：解析xml
	 * 格式：
		  <sign></sign>  
		  <body> 
		    <errorcode>0</errorcode>  
		    <description></description>  
		  </body> 
	 * @param str xml文件
	 * @return
	 */
	public static Map<String,String> getContentBody(String str){
		SAXReader saxReader = new SAXReader();
		Map<String,String> map = new HashMap<String, String>();
		try {
			saxReader.setEncoding("GB2312");
			Document document = saxReader.read(new ByteArrayInputStream(str.getBytes()));
			
			// 获取根元素 
			Element root = document.getRootElement();
			 
			Iterator it = root.elementIterator();
			while (it.hasNext()) {
				Element filed = (Element) it.next();
				map.put(filed.getName(), (filed.getData()+"").trim());
			}
			 it = root.element("body").elementIterator();
			while (it.hasNext()) {
				Element filed = (Element) it.next();
				map.put(filed.getName(), (filed.getData()+"").trim());
			}
		} catch (Exception e) {
			logger.info(" ParseXml.getContentBody 解析 xml 错误:",e);
		}
		return map;
		
	}
	/**
	 * 
	 * 功能：解析xml
	 * 格式：
		  <sign></sign>  
		  <body> 
		    <errorcode>0</errorcode>  
		    <description></description>  
		  </body> 
	 * @param str xml文件
	 * @return
	 */
	public static Map<String,String> getContentBodyUTF8(String str){
		SAXReader saxReader = new SAXReader();
		Map<String,String> map = new HashMap<String, String>();
		try {
			saxReader.setEncoding("UTF-8");
			Document document = saxReader.read(new ByteArrayInputStream(str.getBytes()));
			
			// 获取根元素 
			Element root = document.getRootElement();
			 
			Iterator it = root.elementIterator();
			while (it.hasNext()) {
				Element filed = (Element) it.next();
				map.put(filed.getName(), (filed.getData()+"").trim());
			}
			 it = root.element("body").elementIterator();
			while (it.hasNext()) {
				Element filed = (Element) it.next();
				map.put(filed.getName(), (filed.getData()+"").trim());
			}
		} catch (Exception e) {
			logger.info(" ParseXml.getContentBody 解析 xml 错误:",e);
		}
		return map;
		
	}
	public static void main(String[] args) throws DocumentException {
//		System.out.println(getValue("voucher_webservice_url"));
		String path = "";
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("gb2312");
//		Document document = saxReader.read(new File(path));
		Document document = saxReader.read(ProjectXml.class.getResourceAsStream("project.xml"));
	}
}
