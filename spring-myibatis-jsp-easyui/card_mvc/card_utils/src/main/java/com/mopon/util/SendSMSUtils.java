package com.mopon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mopon.mts.webservice.Mms;
import com.mopon.mts.webservice.MoponMMS;
import com.mopon.mts.webservice.MoponSMS;
import com.mopon.mts.webservice.Sms;

public class SendSMSUtils {
	
	/**
	 * 发送短信（调用该方法，需要在本项目project.xml 文件中配置短信接口地址和密钥）
	 *    <sms_path>http://172.16.36.21:8080/</sms_path>
   		   <sms_secretkey>34qw71f7-a8c9-11e1-8933-</sms_secretkey>
            <sms_clientCode>01</sms_clientCode>
	 * @param content
	 * @param mobile
	 */
	public static  String sendSms(String content,String mobile){
		String result="";
		String retainStr="";
		int maxLen = 179; //每条短信允许的最大字符
		int len = getStringLen(content);
		
		//当短信内容长度大于180个字节时，做短信拆分处理，分段发送
		if(len>maxLen){
			String contentStr = content.substring(0, maxLen);
			retainStr = content.substring(maxLen, content.length());
			while (true) {
				result= SendToSMS(contentStr,mobile);				
				if(getStringLen(retainStr)>maxLen){					
					contentStr = retainStr.substring(0, maxLen);
					retainStr = retainStr.substring(maxLen, retainStr.length());
				}else{
					result=SendToSMS(retainStr,mobile);					
					break;
				}
			}
		}else {
			//下发短信
			result = SendToSMS(content,mobile);			
		}

		return result;
	}
	
	/**
	 * 调用短信接口，发送短信 
	 * @param content
	 * @param mobile
	 * @return
	 */
	private static String SendToSMS(String content,String mobile){
		Sms sms = new Sms();
		sms.setClientCode(ProjectXml.getValue("sms_clientCode"));
		sms.setContent(content);
		sms.setMobileNo(mobile);
		sms.setAddress(ProjectXml.getValue("sms_path"));
		sms.setSecretKey(ProjectXml.getValue("sms_secretkey"));
		//sms.setAddSerial("1001");//拓展号
		return new MoponSMS().sendSSMS(sms);
	}
	
	
	
	/**
	 * 调用彩信接口，发送彩信
	 * @param content
	 * @param mobile
	 * @return
	 */
	public static String SendMMS(String content,String mobile){
		Mms mms = new Mms();
		mms.setClientCode(ProjectXml.getValue("sms_clientCode"));
		mms.setContent(content);
		mms.setMobileNo(mobile);
		mms.setAddress(ProjectXml.getValue("sms_path"));
		mms.setSecretKey(ProjectXml.getValue("sms_secretkey"));
		//sms.setAddSerial("1001");//拓展号
		return new MoponMMS().sendSMMS(mms);
	}
	
	public static int getStringLen(String str){
		try {
			char[] chars = str.toCharArray();
			return chars.length;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	  *
	  * @Title: replaceSmsContent
	  * @Description: 根据接入商类型判断是否是微影,如果是微影那么将 [兜有电影] 替换成  「中影票务通|微信电影票」
	  * @param @param providerID 接入商类型编号
	  * @param @param Content 短信内容
	  * @param @return    替换短信内容.
	  * @return String    返回类型
	  * @throws
	 */
	public static String  replaceSmsContent(String providerID,String Content) {
		// String txt="【中影票务通】(voucherno),(showdate),(cinemaname),(filmname),(seatno)等(goodsnum)张票已定,凭该凭证至(deviceposition)取票,询4000668882,祝观影愉快![兜有电影]";
		String strContent = Content;
		try {
		if(providerID.equals(com.mopon.util.enums.ProviderEnum.WYCINEMA.getId()+"")){			   
			    String re2=".*?\\[(.+?)]";	
			    Pattern p = Pattern.compile(re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			    Matcher m = p.matcher(strContent);
			    if (m.find())
			    {
			        String sbraces1=m.group(1);
			        sbraces1 ="[" + sbraces1  + "]";
			        strContent =strContent.replace(sbraces1, "「中影票务通|微信电影票」");
			    }
			    else {
			    	strContent=strContent+"「中影票务通|微信电影票」";
				}
		}
		}catch(Exception ex) {			
		}
		
		return strContent;
	}
	
	public static void main(String[] args)
	  {
	    String txt="【中影票务通】(voucherno),(showdate),(cinemaname),(filmname),(seatno)等(goodsnum)张票已定,凭该凭证至(deviceposition)取票,询4000668882,祝观影愉快![兜有电影]";
	    String replaceStr = replaceSmsContent("8",txt);
	    System.out.print(replaceStr);
	
	    
	  }
	
	/*public static void main(String[] args){
		sendSms("【中影票务通】26499847401,26448845189,26415086049,26461825030,26407555322,26453144752,26401124292,26439758669,26408502111,26421140734,假日影城lcc2D常规价通兑票已定,至3取票,该凭证可消费10张,2015-09-30 23:59:59310，26499847401,26448845189,26415086049,26461825030,26407555322,26453144752,26401124292,26439758669,26408502111,26421140734假日影城lcc2D常规价通兑票", "13714004263");
	}*/
}
