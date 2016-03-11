package com.mopon.service.contant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

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
	public  static final String JSONDATA = "jsonData";
	/**
	 * 分页标识
	 */
	public static final String PAGES="pages";
	
	public static final String ISADMIN="admin";
	/**
	 * 影院列表接口
	 */
	public static final String QUERYCINEMAS = "queryCinemas.json";
	/**
	 * 影厅列表接口
	 */
	public static final String QUERYHALLS = "queryHalls.json";
	/**
	 * 影厅的座位列表
	 */
	public static final String QUERYSEATS = "querySeats.json";
	/**
	 * 影片列表
	 */
	public static final String QUERYFILMS = "queryFilms.json";
	/**
	 * 影片单个
	 */
	public static final String QUERYFILM = "queryFilm.json";
	/**
	 * 影院单个
	 */
	public static final String QUERYCINEMA = "queryCinema.json";
	/**
	 * 场次列表
	 */
	public static final String QUERYSHOWS = "queryShows.json";
	/**
	 * 场次座位列表
	 */
	public static final String QUERYSHOWSEATS = "queryShowSeats.json";
	/**
	 * 替代场次
	 */
	public static final String QUERYREPLACESHOW = "queryReplaceShow.json";
	/**
	 * 锁定座位
	 */
	public static final String LOCKSEATS = "lockSeats.json";
	/**
	 * 释放座位
	 */
	public static final String RELEASESEATS = "releaseSeats.json";
	/**
	 * 提交订单
	 */
	public static final String SUBMITORDER = "submitOrder.json";
	/**
	 * 查询订单
	 */
	public static final String QUERYORDER = "queryOrder.json";
	/**
	 * 查询打票状态
	 */
	public static final String QUERYPRINTBYVOUCHER = "queryPrintByVoucher.json";
	/**
	 * 查询打票状态
	 */
	public static final String QUERYPRINTBYVERIFYCODE = "queryPrintByVerifyCode.json";
	/**
	 *确认打票
	 */
	public static final String CONFIRMPRINT = "confirmPrint.json";
	
	/**
	 * SCES 返回正确code
	 */
	public static final String SUCCESS_CODE = "001";
	/**
	 * 影院列表
	 */
	public static final String SCES_CINEMAS = "SCES_CINEMAS";
	
	/**
	 * 接口返回参数，用于返回给业务平台
	 */
	public static final String REPLY_CODE = "REPLY_CODE";
	/**
	 * 接口返回参数，用于返回给业务平台
	 */
	public static final String REPLY_MSG = "REPLY_MSG";
	/**
	 * 接口返回参数，用于返回给业务平台：平台订单号
	 */
	public static final String REPLY_ORDER_CODE = "REPLY_ORDER_CODE";
	/**
	 * 接口返回参数，用于返回给业务平台：锁座时长（分钟）
	 */
	public static final String REPLY_LOGCK_TIME = "REPLY_LOGCK_TIME";
	/**
	 * 接口返回参数，用于返回给业务平台：短信类型（1：地面取票号；2：平台凭证号；3：混合方式）
	 */
	public static final String REPLY_PRINT_MODE = "REPLY_PRINT_MODE";
	/**
	 * 接口返回参数，用于返回给业务平台：接入商类型（1：火烈鸟 2：鼎新 3：火凤凰 4：满天星）
	 */
	public static final String REPLY_PROVIDER = "REPLY_PROVIDER";
	/**
	 * 接口返回参数，用于返回给业务平台：凭证号
	 */
	public static final String REPLY_VOUCHERCODE = "REPLY_VOUCHERCODE";
	/**
	 * 接口返回参数，用于返回给业务平台：取票号
	 */
	public static final String REPLY_PRINTCODE = "REPLY_PRINTCODE";
	/**
	 * 接口返回参数，用于返回给业务平台：取票验证码
	 */
	public static final String REPLY_VERIFYCODE = "REPLY_VERIFYCODE";
	/**
	 * 接口返回参数，用于返回给业务平台
	 */
	public static final String REPLY_TICKET_STA = "REPLY_TICKET_STA";
	/**
	 * 接口返回参数，用于返回给业务平台
	 */
	public static final String REPLY_TICKET = "REPLY_TICKET";
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
}
