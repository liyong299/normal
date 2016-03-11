package com.mopon.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 簽名認證類
  * @ClassName: OAuthUtils
  * @Description: TODO
  * @author AL
  * @date 2015年5月9日 上午11:29:18
  *
 */
public class OAuthUtils {
	
	
	/**
	  * getSign(對字符串進行簽名)
	  * @Title: getSign
	  * @Description: TODO
	  * @param @param bodyStr
	  * @param @param appSecret
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String getSign(String bodyStr , String appSecret)
	{
		return MD5.md5(appSecret + bodyStr + appSecret) .toUpperCase();
	}
	
	/**
	  * getSign(驗證接口調用的簽名方式)
	  * @Title: getSign
	  * @Description: TODO
	  * @param @param map
	  * @param @param appSecret
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String getSign(Map<String,String> map,String appSecret)
	{
		Map<String, String> newMap = getSortMap(map);
		String signStr = "";
		Iterator<Map.Entry<String, String>> entries = newMap.entrySet().iterator();  
		while (entries.hasNext()) {  		  
		    Map.Entry<String, String> entry = entries.next();  
		    signStr += entry.getKey() + entry.getValue();
		}
		return MD5.md5(appSecret + signStr + appSecret).toUpperCase();
	}
	
	/**
	 * 分销商接口调用的签名方式
	 * @param map
	 * @param appSecret
	 * @return
	 */
	public static String getSignDisInterface(Map<String,String> params,String app_key){
		if (params == null || params.isEmpty()) {
			return null;
		}
		String sign = "";
		// 对key进行ASCII码升序排列
		Object[] keys = params.keySet().toArray();
		Arrays.sort(keys);
		
		List<String> paramPairs = new ArrayList<String>();
		for (int i = 0; i < params.size(); i++) {
			String method =keys[i].toString();
			if(!"method".equals(method)){
				paramPairs.add(keys[i]+"="+params.get(keys[i]));
			}
		}
		
		//用‘&’符将参数拼接成字符串。
	    String paramSignStr = StringUtils.join(paramPairs, "&");
	    try{
		    //按UTF_8进行编码。
		    paramSignStr = URLEncoder.encode(paramSignStr,"UTF-8");
		  		
			String mdsParam = app_key + paramSignStr + app_key;
			
			System.out.println("签名参数："+mdsParam);
			//对参数加密
			sign = DigestUtils.md5Hex(mdsParam);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return sign;
	}
	
	/**
	  * getSortMap(將MAP參數，除sign參數外排序，返回按KEY值字母升序的MAP)
	  * @Title: getSortMap
	  * @Description: TODO
	  * @param @param map
	  * @param @return    设定文件
	  * @return Map<String,String>    返回类型
	  * @throws
	 */
	private static Map<String, String> getSortMap(Map<String,String> map)
	{
		Map<String, String> sortMap = new TreeMap<String, String>();
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();  
		while (entries.hasNext()) {  		  
		    Map.Entry<String, String> entry = entries.next();  
		    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		    if(StringUtils.isBlank(entry.getValue())){ // 如果为空不参与签名
		    	continue;
		    }
		    if(!entry.getKey().equals("sign")){
		    	sortMap.put(entry.getKey(), entry.getValue());
		    }
		}
		return sortMap;
	}
}
