/**
 * 
 */
package com.mopon.service.contant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.mopon.util.BusinessException;
import com.mopon.util.ConvertSeatsUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * <p>Description: 接口的帮助类</p>
 * @date 17 Apr 2015
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class ApiConstant {

	private static String XML_VERSION_UTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	private static String TRADE_SIGN = "sign";
	/**
	 * apiPowerSerive 传递给 apiService 的参数
	 */
	public static final String RESULT_CODE = "RESULTCODE";
	public static final String RESULT_MESSAGE = "RESULTMSG";
	public static final String RESULT_GOODSTYPE = "RESULTGOODSTYPE";
	public static final String RESULT_OBJECT = "RESULTOBJECT";
	
	private static String METHOD = "method";
	public static String REPLY_SUCCESS = "001";
	public static String REPLY_SUCCESS_NAME = "操作成功";
	public static final String REPLY_SEATS = "REPLY_SEATS";
	/**
	 * 接口返回参数，用于返回给业务平台
	 */
	public static final String REPLY_SHOWS = "REPLY_SHOWS";
	/**
	 * 影片
	 */
	public static final String REQUEST_CODE_QUERYFILMS = "queryFilms";
	/**
	 * 地区获取影院列表
	 */
	public static final String REQUEST_CODE_QUERYCINEMAS = "queryCinemas";
	/**
	 * 影院
	 */
	public static final String REQUEST_CODE_QUERYCINEMA = "queryCinema";
	/**
	 * 排期
	 */
	public static final String REQUEST_CODE_QUERYSHOWS = "queryShows";
	/**
	 * 询指定场次的座位列表
	 */
	public static final String REQUEST_CODE_QUERYSHOWSEATS = "queryShowSeats";
	/**
	 * 锁定指定场次的座位
	 */
	public static final String REQUEST_CODE_LOCKSEATS = "lockSeats";
	/**
	 * 
	 */
	public static final String REQUEST_CODE_QUERYCOMMTICKETS = "queryCommTickets";
	/**
	 * 查询通兑票使用范围
	 */
	public static final String REQUEST_CODE_QUERYCOMMUSERANGES = "queryCommUseRanges";
	/**
	 * 生成通兑票订单
	 */
	public static final String REQUEST_CODE_CREATECOMMORDER = "createCommOrder";
	/**
	 * 释放座位
	 */
	public static final String REQUEST_CODE_RELEASESEATS = "releaseSeats";
	/**
	 * 锁座后确认订单
	 */
	public static final String REQUEST_CODE_SUBMITORDER = "submitOrder";
	/**
	 * 锁座后确认订单
	 */
	public static final String REQUEST_CODE_QUERYORDER = "queryOrder";
	/**
	 * code查询影片
	 */
	public static final String REQUEST_CODE_QUERYFILM = "queryFilm";
	/**
	 * 查询第三方排期
	 */
	public static final String REQUEST_CODE_QUERYTHIRDSHOWS = "queryThirdShows";
	/**
	 * 影厅列表
	 */
	public static final String REQUEST_CODE_QUERYHALLS= "queryHalls";
	/**
	 * 影厅座位
	 */
	public static final String REQUEST_CODE_QUERYSEATS = "querySeats";
	
	
	
	/**
	 * 请求的所以接口名称
	 */
	public static List<String> list = new ArrayList<String>();
	
	public static String SUFFIX_JSON = "json";
	public static String SUFFIX_XML = "xml";
	/**
	 * 返回xml定义
	 */
	private static String RETURN_REPLY = "reply";
	static{
		list.add("queryFilms.json");
		list.add("queryFilms.xml");
		list.add("queryCinemas.json");
		list.add("queryCinemas.xml");
		list.add("queryCinema.json");
		list.add("queryCinema.xml");
		list.add("queryShows.json");
		list.add("queryShows.xml");
		list.add("queryShowSeats.json");
		list.add("queryShowSeats.xml");
		list.add("queryCommTickets.json");
		list.add("queryCommTickets.xml");
		list.add("queryCommUseRanges.json");
		list.add("queryCommUseRanges.xml");
		list.add("createCommOrder.json");
		list.add("createCommOrder.xml");		
		list.add("releaseSeats.json");
		list.add("releaseSeats.xml");	
		list.add("lockSeats.json");
		list.add("lockSeats.xml");
		list.add("submitOrder.json");
		list.add("submitOrder.xml");
		list.add("queryOrder.json");
		list.add("queryOrder.xml");
		list.add("queryFilm.json");
		list.add("queryFilm.xml");
		list.add("queryVoucherUseRanges.json");
		list.add("queryVoucherUseRanges.xml");
		list.add("queryThirdShows.json");
		list.add("queryThirdShows.xml");
		list.add("queryHalls.json");
		list.add("queryHalls.xml");
		list.add("querySeats.json");
		list.add("querySeats.xml");
	}
	
	/**
	 * 
	  * purpose:查询请求的接口名称<br>
	  * step: <br>
	  * @param url
	  * @return
	  * String
	 */
	public static String getUrlToMethod(String url){
		
		if(StringUtils.isBlank(url)){
			return null;
		}
		for(String item : list){
			if(url.contains(item)){
				return item;
			}
		}
		return null;
	}
	/**
	 * 
	 * purpose:校验报文是否正确填写 <br>
	 * step: <br>
	 * @param errorSystem
	 * @return
	 */
	public static String checkSystemParam(Map<String,String> map) {
		
		 String sign = map.get(TRADE_SIGN);
		 String method = map.get(METHOD);
//		 String suffix = map.get("suffix");
		 //sign 验证失败
		 if(StringUtils.isBlank(sign)){
			 BusinessException.raise(ErrorCodeEnum.E_SIGN_N.getCodeName());
		 }
		 
		 if(method == null || !list.contains(method)){
			 BusinessException.raise(ErrorCodeEnum.E_METHOD.getCodeName());
		 }
		 return null;
	}
	 public static String replaceBack500(String code, String msg,String suffix) {
		
		StringBuffer sb = new StringBuffer();
		 String back = "";
		 if(ApiConstant.SUFFIX_XML.equals(suffix)){
			 sb.append(XML_VERSION_UTF8);
			 XStream stream = new XStream(new DomDriver());
			 stream.alias("reply", Message.class);
			 String content = stream.toXML(new Message(code,msg));
			 sb.append(content);
			 back = sb.toString();
		 }else if(ApiConstant.SUFFIX_JSON.equals(suffix)){
				//转换json并且替换items属性名称为当前list名称
				JSONObject jo = JSONObject.fromObject(new Message(code,msg));
				back = jo.toString();
		}
				
		return back;
	}
	 
	 /**
	  * 
	   * purpose:返回字符串<br>
	   * step: <br>
	   * @param c
	   * @param c2 格式类名字
	   * @param o
	   * @param suffix json 或 xml
	   * @return
	   * String
	  */
	 public static String replaceBackStr(Class class1, Class class2,Object o,String suffix) {
		 
		 String back = "";
		 StringBuffer sb = new StringBuffer();
		 String cName = class2.getSimpleName().toLowerCase();
		 sb.append(XML_VERSION_UTF8);
		 if(ApiConstant.SUFFIX_XML.equals(suffix)){
			//转换xml并且替换items属性名称为当前list名称
			 XStream stream = new XStream(new DomDriver());
			 stream.alias(RETURN_REPLY , class1);
			 stream.alias(cName, class2);
			 stream.aliasField(cName+"s",class1 ,"items");
			 String content = stream.toXML(o);
			 sb.append(content);
			 back = sb.toString();
			 
		 }else if(ApiConstant.SUFFIX_JSON.equals(suffix)){
			//转换json并且替换items属性名称为当前list名称
			JSONObject jo = JSONObject.fromObject(o);
			String items = jo.get("items")+"";
			jo.accumulate(cName+"s", items);
			jo.remove("items");
			back = jo.toString();
		 }
			
		 return back;
	 }
	 /**
	  * 
	  * purpose:返回字符串<br>
	  * step: <br>
	  * @param c
	  * @param c2 格式类名字
	  * @param o
	  * @param suffix json 或 xml
	  * @return
	  * String
	  */
	 public static String replaceBackStr(Class class1, Class class2,Object o,String suffix,String class2Name) {
		 
		 String back = "";
		 StringBuffer sb = new StringBuffer();
		 sb.append(XML_VERSION_UTF8);
		 if(ApiConstant.SUFFIX_XML.equals(suffix)){
			 //转换xml并且替换items属性名称为当前list名称
			 XStream stream = new XStream(new DomDriver());
			 stream.alias(RETURN_REPLY , class1);
			 stream.alias(class2Name, class2);
			 stream.aliasField(class2Name+"s",class1 ,"items");
			 String content = stream.toXML(o);
			 sb.append(content);
			 back = sb.toString();
			 
		 }else if(ApiConstant.SUFFIX_JSON.equals(suffix)){
			 //转换json并且替换items属性名称为当前list名称
			 JSONObject jo = JSONObject.fromObject(o);
			 String items = jo.get("items")+"";
			 jo.accumulate(class2Name+"s", items);
			 jo.remove("items");
			 back = jo.toString();
		 }
		 
		 return back;
	 }
	 
	 /**
	  * 
	  * purpose:返回字符串<br>
	  * step: <br>
	  * @param c
	  * @param c2 格式类名字
	  * @param o
	  * @param suffix json 或 xml
	  * @return
	  * String
	  */
	 public static String replaceBackStrNew(Class class1, Class class2,Object o,String suffix,String class2Name,Map<String,Class> aliasMap,Map<String,Class> aliasFiledMap) {
		 String back = "";
		 StringBuffer sb = new StringBuffer();
		 sb.append(XML_VERSION_UTF8);
		 if(ApiConstant.SUFFIX_XML.equals(suffix)){
			 //转换xml并且替换items属性名称为当前list名称
			 XStream stream = new XStream(new DomDriver());
			 stream.alias(RETURN_REPLY , class1);
			 stream.alias(class2Name, class2);
			 stream.aliasField(class2Name+"s",class1 ,"items");
			 if(aliasMap != null){
				 Iterator<Map.Entry<String, Class>> entries = aliasMap.entrySet().iterator(); 
				 while (entries.hasNext()) {
					 Map.Entry<String, Class> entry = entries.next();
					 stream.alias(entry.getKey(), entry.getValue());
				}
			}
			if(aliasFiledMap != null){
				Iterator<Map.Entry<String, Class>> filedEntries = aliasFiledMap.entrySet().iterator();
				while (filedEntries.hasNext()) {
					Map.Entry<String, Class> entry = filedEntries.next();
					stream.aliasField(entry.getKey(), entry.getValue(),"items");
				}
			}		
			String content = stream.toXML(o);
			sb.append(content);
			back = sb.toString();
		 }else if(ApiConstant.SUFFIX_JSON.equals(suffix)){
			 //转换json并且替换items属性名称为当前list名称
			 JSONObject jo = JSONObject.fromObject(o);
			 String items = jo.get("items")+"";
			 jo.accumulate(class2Name+"s", items);
			 jo.remove("items");
			 back = jo.toString();
		 }		 
		 return back;
	 }
	 
	/**
		 * 
		 * 方法用途: 获取请求地址 IP<br>
		 * 实现步骤: <br>
		 * @param request
		 * @return
		 */
		public static String getIpAddr(HttpServletRequest request) {
			String ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}
		
	/**
	 * 
	  * purpose:对单个类的转换<br>
	  * step: <br>
	  * @param class1
	  * @param class2
	  * @param o
	  * @param suffix
	  * @return
	  * String
	 */
	public static String replaceBackBean(Class class1,Class class2, Object o, String suffix) {
		String back = "";
		 StringBuffer sb = new StringBuffer();
		 String cName = class2.getSimpleName().toLowerCase();
		 sb.append(XML_VERSION_UTF8);
		 if(ApiConstant.SUFFIX_XML.equals(suffix)){
			//转换xml
			 XStream stream = new XStream(new DomDriver());
			 stream.alias(RETURN_REPLY , class1);
			 stream.aliasSystemAttribute(null, "class");
			 stream.aliasField(cName,class1 ,"item");
			 String content = stream.toXML(o);
			 sb.append(content);
			 back = sb.toString();
			 
		 }else if(ApiConstant.SUFFIX_JSON.equals(suffix)){
			//转换json并且替换items属性名称为当前list名称
			JSONObject jo = JSONObject.fromObject(o);
			String items = jo.get("item")+"";
			jo.accumulate(cName, items);
			jo.remove("item");
			back = jo.toString();
		 }
			
		 return back;
	}
	/**
	 * 
	 * purpose:对单个类的转换<br>
	 * step: <br>
	 * @param class1
	 * @param class2
	 * @param o
	 * @param suffix
	 * @return
	 * String
	 */
	public static String replaceBackBean(Class class1, Object o, String suffix,String class2Name) {
		String back = "";
		StringBuffer sb = new StringBuffer();
		sb.append(XML_VERSION_UTF8);
		if(ApiConstant.SUFFIX_XML.equals(suffix)){
			//转换xml
			XStream stream = new XStream(new DomDriver());
			stream.alias(RETURN_REPLY , class1);
			stream.aliasSystemAttribute(null, "class");
			stream.aliasField(class2Name,class1 ,"item");
			String content = stream.toXML(o);
			sb.append(content);
			back = sb.toString();
			
		}else if(ApiConstant.SUFFIX_JSON.equals(suffix)){
			//转换json并且替换items属性名称为当前list名称
			JSONObject jo = JSONObject.fromObject(o);
			String items = jo.get("item")+"";
			jo.accumulate(class2Name, items);
			jo.remove("item");
			back = jo.toString();
		}
		
		return back;
	}
	/**
	 * 
	 * purpose:对单个类的转换<br>
	 * step: <br>
	 * @param class1
	 * @param class2
	 * @param o
	 * @param suffix
	 * @return
	 * String
	 */
	public static String replaceBackQueyOrder(Class class1,Object o, String suffix) {
		String back = "";
		StringBuffer sb = new StringBuffer();
		sb.append(XML_VERSION_UTF8);
		if(ApiConstant.SUFFIX_XML.equals(suffix)){
			//转换xml
			XStream stream = new XStream(new DomDriver());
			stream.alias(RETURN_REPLY , class1);
			stream.aliasSystemAttribute(null, "class");
			stream.alias("voucher", DisVoucher.class); 
			String content = stream.toXML(o);
			sb.append(content);
			back = sb.toString();
			
		}else if(ApiConstant.SUFFIX_JSON.equals(suffix)){
			//转换json并且替换items属性名称为当前list名称
			JSONObject jo = JSONObject.fromObject(o);
			back = jo.toString();
		}
		
		return back;
	}
	/**
	 * 
	  * purpose:对没有类的转换<br>
	  * step: <br>
	  * @param class1
	  * @param o
	  * @param suffix
	  * @return
	  * String
	 */
	public static String replaceBackVo(Class class1,Object o, String suffix) {
		String back = "";
		StringBuffer sb = new StringBuffer();
		sb.append(XML_VERSION_UTF8);
		if(ApiConstant.SUFFIX_XML.equals(suffix)){
			//转换xml
			XStream stream = new XStream(new DomDriver());
			stream.alias(RETURN_REPLY , class1);
			String content = stream.toXML(o);
			sb.append(content);
			back = sb.toString();
			
		}else if(ApiConstant.SUFFIX_JSON.equals(suffix)){
			//转换json
			JSONObject jo = JSONObject.fromObject(o);
			back = jo.toString();
		}
		
		return back;
	}
	/**
	 * 座位，分割的，转换为 汉字：3排4座，5排6座
	 * scec单个座位格式: 1_1 
	 * 大地单个座位格式: 1|1|1   (区域|列|行)
	 * 洲立单个座位格式:0000000000|1|5|20   (AreaCatCode|AreaNum|Row|Seat) 
	 * 解析规则:用分割符得到的总数超过2,取最后两个数值.
	 * @param seatCodes
	 * @return
	 */
	public static String convertSeatsName(String seatCodes){
		return ConvertSeatsUtils.convertSeatsName(seatCodes);		
	} 
	
	public static String convertSeatsName_Bak(String seatCodes){
		String name = "";
		String[] codes  = seatCodes.split(",");
		
		for(int i=0;i<codes.length;i++){
			if(i > 0) name += ",";
			name += codes[i].replace("_","排") + "座";
		}
		return name;		
	} 
	
	public static void main(String[] args) throws Exception {
		String str1="c_2,c_3,1_11_11,000_2_12_12,3_3,4_4";
		String str2="0000000000|1|11|9,1|17,1|9|9";
		String rstr1 =convertSeatsName(str1);
		String rstr2 =convertSeatsName(str2);
		System.out.println(rstr1);
		System.out.println(rstr2);
	}
	
}
