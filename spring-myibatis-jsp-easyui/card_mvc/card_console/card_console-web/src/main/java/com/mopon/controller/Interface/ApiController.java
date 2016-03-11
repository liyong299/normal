/**
 * 
 */
package com.mopon.controller.Interface;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mopon.service.contant.ApiConstant;
import com.mopon.service.contant.ErrorCodeEnum;
import com.mopon.service.contant.Contants;
import com.mopon.util.BusinessException;
import com.mopon.util.HttpProcess;
import com.mopon.util.LogUtils;
import com.mopon.util.ProjectXml;
import com.mopon.util.ResponseWriteUtils;
import com.mopon.util.ValidateParams;

import org.dom4j.Document;

/**
 * 
 * <p>Description:对外提供接口 </p>
 * @date 17 Apr 2015
 * @author Aaron
 * @version 1.0
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
@Controller
@RequestMapping(value = "/interfaceApi")
public class ApiController{

	private final static Logger log =  LoggerFactory.getLogger(ApiController.class);
	private final static LogUtils loggerUtil = new LogUtils(ApiController.class);
	
	private	HttpProcess httpProcess = new HttpProcess();
		
	@RequestMapping(value = "queryHalls")
	public String queryHalls(HttpServletRequest request,HttpServletResponse response) {
		return "/thirdInterface/queryHalls";
	}
	
	@RequestMapping(value = "queryCinemas")
	public String queryCinemas(HttpServletRequest request,HttpServletResponse response) {
		return "/thirdInterface/queryCinemas";
	}
	
	/**
	 * 加密通用 方法
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "sign/toSign")
	public void toSign(HttpServletRequest request,HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		
		log.info("process in toSign......");
		
		String channelCode = request.getParameter("channelCode");
		Map<String,String> params = new HashMap<String,String>(); 
		params.put("channelCode",channelCode);
		try {
			
			Map endum = request.getParameterMap();
			Iterator it = endum.entrySet().iterator();
			 while (it.hasNext()) {
			    Map.Entry entry = (Map.Entry) it.next();
			    Object key = entry.getKey();
			    Object value = entry.getValue();
			   
			    if(key.equals("source") || key.equals("method") || key.equals("channelCode")){
			    	continue;
			    }
			    params.put(key+"", request.getParameter(key+""));
			}
			 
			String str = Contants.genSignForParams(params, "根据不同渠道key不同，TODO"); 
			jsonObject.put("sign", str);
		} catch (Exception e) {
			log.error("操作异常",e);
		}
		ResponseWriteUtils.returnAjax(response, jsonObject.toString());
	}
	
	 /**
	  * 
	  * 方法用途:  <br>
	  * 实现步骤: <br>
	  * @param ctx
	  */
	 @RequestMapping(value = "ticket/v2")
	 public void ticket(HttpServletRequest request,HttpServletResponse response){
		 StopWatch stopWatch = new StopWatch();
		 stopWatch.start();
		 
		 log.debug("process in ApiServlet.ticket start......");
		 response.setContentType("text/html; charset=UTF-8");
		 PrintWriter writer = null;
		 String returnStr = "";
		 String suffix = ""; 
		 String requestPath = request.getRequestURL().toString();
		 String ip = ApiConstant.getIpAddr(request);
		 log.info("IP:"+ip);
		 log.debug("requestPath:"+requestPath);
		 Map<String,String> map = getParamterMap(request);
		 String channelCode = map.get("channelCode");
		 String requestName = map.get("method");
		try{
			 writer = response.getWriter();
			 
			 String requestCode = requestName.substring(0,requestName.indexOf("."));
			 suffix = requestName.substring(requestName.indexOf(".")+1,requestName.length());
			
			 map.put("suffix", suffix);//将后缀添加map，方便后续操作		
			 
			 //校验参数 错误会抛出异常
			 this.checkParam(map);
		    
			 map.put("seckey",  "根据不同渠道key不同，TODO");
			//请求业务码 ： 参数、接口名称（如：queryFilms.json）			
			 returnStr = requestHttpProcess(map,requestName);
				 
		}catch(BusinessException e){
			returnStr = ApiConstant.replaceBack500(ErrorCodeEnum.ERROR.getErrorCode()+"",e.getMessage(),suffix);
			log.error("ApiServlet.doPost 操作异常： "+e.getMessage());
		 }catch(Exception e){
			 returnStr = ApiConstant.replaceBack500(ErrorCodeEnum.E_SYSTEM.getErrorCode()+"",ErrorCodeEnum.E_SYSTEM.getCodeName(),suffix);
			 log.error("ApiServlet.doPost 操作异常： ",e);
		 }
		log.debug("返回报文："+returnStr);
		
		try {
			stopWatch.stop();
			String logKeywords = "[channelCode渠道:"+channelCode+",method接口:"+requestName+"]";
			loggerUtil.logInfoInterface(logKeywords,requestPath, map.toString(), "", stopWatch.getTotalTimeMillis());
			
		} catch (Exception e) {
			returnStr = ApiConstant.replaceBack500(ErrorCodeEnum.E_SYSTEM.getErrorCode()+"",ErrorCodeEnum.E_SYSTEM.getCodeName(),suffix);
			log.error("转换UTF-8异常：" ,e);
		}  
		writer.write(returnStr);
	 }
	 	 
	 
		/**
		 * 组装参数，请求接口
		 * @param params
		 * @param method 调用接口方法
		 * @return
		 */
		private String requestHttpProcess(Map<String,String> params,String method){
			String third_interface_path = ProjectXml.getValue("third_interface_path");
			String returnStr = "";
	
			try {
				byte[] returnByte = httpProcess.doPost(third_interface_path, params, "UTF-8");
				returnStr = new String(returnByte, "UTF-8");
				returnStr = URLDecoder.decode(returnStr, "UTF-8");	
			} catch (UnsupportedEncodingException e) {
				returnStr="字符串转换编码格式异常";
				log.error(returnStr,e);
			}catch(ConnectTimeoutException e2){
				returnStr="请求超时";
				log.error(returnStr,e2); //6秒
			} catch (IOException e1) {
				returnStr="请求错误";
				log.error(returnStr,e1);
			}
			
			return returnStr;		
		}
	 
	 
	/**
	 * 
	 * purpose:获取 接口接收到的参数 设置到map中 <br>
	 * step: <br>
	 * @param request
	 * @return
	 */
	private  Map<String,String>  getParamterMap(HttpServletRequest request){
		
		Map<String,String> map = new HashMap<String,String>();
		//系统参数
		String channelCode = request.getParameter("channelCode");
		String sign = request.getParameter("sign");
		String method = request.getParameter("method");
		//应用参数
		String startDate = request.getParameter("startDate");
		String areaNo = request.getParameter("areaNo");
		String cinemaCode = request.getParameter("cinemaCode");
		String filmNo = request.getParameter("filmNo");
		String channelShowCode = request.getParameter("channelShowCode");
		String status = request.getParameter("status");
		String seatCodes = request.getParameter("seatCodes");
		String mobile = request.getParameter("mobile");
		String channelOrderCode = request.getParameter("channelOrderCode");
		String cinemaNo = request.getParameter("cinemaNo");
		String cityNo = request.getParameter("cityNo");
		String ticketNo = request.getParameter("ticketNo");
		String price = request.getParameter("price");
		String count = request.getParameter("count");
		String orderCode = request.getParameter("orderCode");
		String orderType = request.getParameter("orderType");
		String extendInfo = request.getParameter("extendInfo");
		String payment = request.getParameter("payment");
		String orderNo = request.getParameter("orderNo");
		String amount = request.getParameter("amount");
		String payTime = request.getParameter("payTime");
		String voucherNo = request.getParameter("voucherNo");
		String hallCode = request.getParameter("hallCode");
		
		map.put("channelCode", channelCode);
		map.put("sign", sign);
		map.put("method", method);
		map.put("startDate", startDate); // 起始日期
		map.put("areaNo", areaNo); // 城市编码
		map.put("cinemaCode", cinemaCode);//影院编码
		map.put("filmNo", filmNo); //影片编码
		
		map.put("status", status); //场次座位状态
		map.put("channelShowCode", channelShowCode); //渠道场次编码
		
		map.put("seatCodes", seatCodes); //座位编码多个，隔开
		map.put("mobile", mobile); 
		map.put("channelOrderCode", channelOrderCode); 
		map.put("cinemaNo", cinemaNo); 
		map.put("cityNo", cityNo); 
		map.put("ticketNo", ticketNo); 
		map.put("price", price); 
		map.put("count", count); 
		map.put("orderCode", orderCode); 
		map.put("orderType", orderType); 
		map.put("extendInfo", extendInfo); 
		map.put("payment", payment); 
		map.put("orderNo", orderNo); 
		map.put("payTime", payTime); 
		map.put("amount", amount); 
		map.put("voucherNo", voucherNo);
		map.put("hallCode", hallCode); //影厅编码
		log.info("input parameter:"+map.toString());
		return map;
	}
	
	private String checkParam(Map<String,String> map){
		
		String channelCode = map.get("channelCode");
		String suffix = map.get("suffix");
		String mobile = map.get("mobile");
		String price = map.get("price");
		String amount = map.get("amount");
		
		//对后缀名称的校验 ，校验失败则返回xml格式提示	
		if(!ApiConstant.SUFFIX_JSON.equals(suffix) && !ApiConstant.SUFFIX_XML.equals(suffix)){
			BusinessException.raise(ErrorCodeEnum.E_METHOD.getCodeName());
		}
		
		//校验手机号
		if(!StringUtils.isBlank(mobile) && !ValidateParams.isPhone(mobile)){ 
			BusinessException.raise(ErrorCodeEnum.E_ORDER_PHONE.getCodeName());
		}

		if(!StringUtils.isBlank(amount) && !ValidateParams.isPrice(amount)){ 
			BusinessException.raise(ErrorCodeEnum.E_ORDER_AMOUNT.getCodeName());
		}
		if(!StringUtils.isBlank(price) && !ValidateParams.isPrice(price)){ 
			BusinessException.raise(ErrorCodeEnum.E_ORDER_AMOUNT.getCodeName());
		}
		
		//验证错误会抛出异常
	    ApiConstant.checkSystemParam(map);
	    
		return null;
	}
	
}