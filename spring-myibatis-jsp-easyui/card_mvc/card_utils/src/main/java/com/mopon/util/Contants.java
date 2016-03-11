package com.mopon.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * <p>Description:系统常量 </p>
 * @date 2014年3月5日
 * @author tongbiao
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Contants {
	
	private static String XML_VERSION_UTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	public  static final String JSONDATA = "jsonData";
	/**
	 * 分页标识
	 */
	public static final String PAGES="pages";
	
	public static final String ISADMIN="admin";
	/**
	 * 待付款
	 */
	public static final String ZERO="0";
	/**
	 * 创建订单
	 */
	public static final String ONE="1";
	/**
	 * 锁座成功
	 */
	public static final String TWO="2";
	/**
	 * 地面出票成功
	 */
	public static final String THREE="3";
	/**
	 * 凭证成功	
	 */
	public static final String FOUR="4";
	/**
	 * 锁座失败 
	 */
	public static final String FIVE="5";
	/**
	 * 地面出票失败
	 */
	public static final String SIX="6";
	/**
	 * 凭证失败
	 */
	public static final String SEVEN="7";
	/**
	 * 已撤销
	 */
	public static final String EIGHT="8";
	/**
	 * 已关闭
	 */
	public static final String NINE="9";
	/**
	 * 查询影院列表接口
	 */
	public static final String QUERYCINEMAS = "queryCinemas.json";
	/**
	 * 查询影厅列表接口
	 */
	public static final String QUERYHALLS = "queryHalls.json";
	/**
	 * 查询影厅的座位列表
	 */
	public static final String QUERYSEATS = "querySeats.json";
	/**
	 * 查询影片列表
	 */
	public static final String QUERYFILMS = "queryFilms.json";
	/**
	 * 分销渠道列表
	 */
	public static final String QUERYCHANNELS = "queryChannels.json";
	/**
	 * 场次列表
	 */
	public static final String QUERYSHOWS = "queryShows.json";
	
	/**
	 * SCES 返回正确code
	 */
	public static final String SUCCESS_CODE = "001";
	/**
	 * 影院列表
	 */
	public static final String SCES_CINEMAS = "SCES_CINEMAS";
	
	public static String SUFFIX_JSON = "json";
	public static String SUFFIX_XML = "xml";
	/**
	 * 返回xml定义
	 */
	private static String RETURN_REPLY = "reply";
	/**
	 * 
	 * purpose:签名时用到,对参数的签名 ,参数值为空则不进行加密<br>
	 * step: <br>
	 * @param params 需要签名的参数
	 * @param scec_key 密钥
	 * @return 最后的签名数据
	 */
	public static String genSignForParams(Map<String,String> params,String scec_key){
		if (params == null || params.isEmpty()) {
			return null;
		}
		String sign = "";
		// 对key进行ASCII码升序排列
		Object[] keys = params.keySet().toArray();
		Arrays.sort(keys);
		
		List<String> paramPairs = new ArrayList<String>();
		for (int i = 0; i < params.size(); i++) {
//			String str = params.get(keys[i]);
//			if(!StringUtils.isBlank(str) && !"null".equals(str)){
				paramPairs.add(keys[i]+"="+params.get(keys[i]));
//			}
		}
		
		//用‘&’符将参数拼接成字符串。
	    String paramSignStr = StringUtils.join(paramPairs, "&");
	    try{
		    //按UTF_8进行编码。
		    paramSignStr = URLEncoder.encode(paramSignStr,"UTF-8");
		  		
			String mdsParam = scec_key + paramSignStr + scec_key;
			
			System.out.println("签名参数："+mdsParam);
			//对参数加密
			sign = DigestUtils.md5Hex(mdsParam);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return sign;
	}
	
	/**
	 * 
	  * purpose:对没有类的转换<br>
	  * step: <br>
	  * @param class1
	  * @param o
	  * @param suffix json 或 xml
	  * @param tradTag 报文主标签 == null 默认<reply>
	  * @return
	  * String
	 */
	public static String replaceBackVo(Class class1,Object o, String suffix,String tradTag) {
		String back = "";
		StringBuffer sb = new StringBuffer();
		sb.append(XML_VERSION_UTF8);
		
		if(StringUtils.isBlank(tradTag)){
			tradTag = RETURN_REPLY;
		}
		if(SUFFIX_XML.equals(suffix)){
			//转换xml
			XStream stream = new XStream(new DomDriver());
			stream.alias(tradTag , class1);
			String content = stream.toXML(o);
			sb.append(content);
			back = sb.toString();
		}else if(SUFFIX_JSON.equals(suffix)){
			//转换json
			JSONObject jo = JSONObject.fromObject(o);
			back = jo.toString();
		}
		
		return back;
	}
	
}
