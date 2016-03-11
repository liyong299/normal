/**
 * @Title: LogUtils.java
 * @Package com.mopon.util
 * @Description: 日志记录工具类
 * Copyright: Copyright (c) 2011 
 * Company:深圳市泰久信息系统股份有限公司
 * 
 * @author Comsys-yuezt
 * @date 2015年5月6日 下午4:48:05
 * @version V1.0
 */

package com.mopon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: LogUtils
 * @Description: 记日志的工具类
 *               <p>
 *               日志规则：</br> 
 *               1.操作日志 (主要针对后台管理模块的操作日志)--> 记录到数据库,异常操作日志记录到文件</br> 
 *               2.登录日志 -->记录到数据库,异常登录日志记录到文件</br>
 *               3.业务日志（主要针对核心业务流程日志）（从service层记录）
 *               -->正常业务日志记录到文件，异常业务日志记录到文件和数据库，通过logstash管理</br>
 *               5.接口调用日志（从service层记录） -->记录到文件，通过logstash管理</br>
 *               </p>
 * @author yuezt
 * @date 2015年5月6日 下午4:48:05
 *
 */

@SuppressWarnings("rawtypes")
public  class LogUtils {
//	private static Class clazz;
	private static Logger log = null;
	public LogUtils(Class clazz){
//		this.clazz = clazz;
//		System.out.println("-----------class--------"+clazz);
		log = LoggerFactory.getLogger(clazz);
	}
	 
	public static LogUtils getInstance(Class clazz){
		return new LogUtils(clazz);
	}
	/**
	 * 
	 * 
	 *
	 * @Title: logOperationInfo
	 * @Description: 记录正常操作日志到数据库
	 * @param userId
	 *            操作用户ID
	 * @param userName
	 *            操作用户名称
	 * @param function
	 *            操作功能（如：系统管理-->用户管理-->新增用户）
	 * @param discription
	 *            操作描述
	 */
	public final void logInfoOperation(String userId, String userName,
			String function, String discription) {
		// 操作数据库，具体模块实现的人实现此处插入动作
	}

	/**
	 * 
	 * @Description: 记录异常操作日志到文件
	 * @param logKeywords
	 *            日志（检索）关键字，推荐自定义一定格式的关键字，<br>
	 *            例如：[userId:10001-userName:mayue-function:新增用户...]
	 * @param function
	 *            操作功能（如：系统管理-->用户管理-->新增用户）
	 * @param discription
	 *            操作描述（包含请求参数）
	 * @param ex
	 *            异常
	 */
	public final void logErrorOperation(String logKeywords, String function,
			String discription, Throwable ex) {
		String msg = String.format("%s[操作失败]%n[操作功能：%s]%n[请求参数：%s]",
				logKeywords, function, discription);
		log.error(msg, ex);
	}

	/**
	 * 记录接口调用日志。
	 * 
	 * @param logKeywords
	 *            日志（检索）关键字，推荐自定义一定格式的关键字，<br>
	 *            例如：[cinimaArea:北京-cinimaCode:100001-cinimaName:大地影院...]
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param replyString
	 *            响应消息
	 * @param duration
	 *            耗时()
	 * @return void 返回类型
	 */
	public final void logInfoInterface(String logKeywords, String url,
			Object params, String replyString, long duration) {
		replyString = String.format(
				"%s[调用成功][耗时：%s]%n[请求地址：%s]%n[请求参数：%s]%n[响应消息：%s]",
				logKeywords, duration, url, params, replyString);
		log.info(replyString);
	}
	/**
	 * 记录接口调用日志（记录复杂业务处理过程）
	 * 
	 * @param logKeywords
	 *            日志（检索）关键字，推荐自定义一定格式的关键字，<br>
	 *            例如：[cinimaArea:北京-cinimaCode:100001-cinimaName:大地影院...]
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param replyString
	 *            响应消息
	 *@param String processContent
	 *			  业务处理过程(将有必要记录的内容拼成String进行记录)           
	 * @param duration
	 *            耗时()
	 * @return void 返回类型
	 */
	public final void logInfoInterface(String logKeywords, String url,
			Object params, String replyString,String processContent, long duration) {
		replyString = String.format(
				"%s[调用成功][耗时：%s]%n[请求地址：%s]%n[请求参数：%s]%n[响应消息：%s]%n[业务处理过程：%s]",
				logKeywords, duration, url, params, replyString,processContent);
		log.info(replyString);
	}
	/**
	 * 记录接口异常日志。
	 * 
	 * @param logKeywords
	 *            日志（检索）关键字，推荐自定义一定格式的关键字，<br>
	 *            例如：[cinimaArea:北京-cinimaCode:100001-cinimaName:大地影院...]
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param ex
	 *            异常
	 */
	public final void logErrorInterface(String logKeywords, String url,
			Object params, Throwable ex) {
		String msg = String.format("%s[调用失败]%n[请求地址：%s]%n[请求参数：%s]",
				logKeywords, url, params);
		log.error(msg, ex);
	}

	/**
	 * 
	 *
	 * @Title: logInfoLogin
	 * @Description: 记录正常登录日志到数据库
	 * @param userId
	 *            用户ID
	 * @param userName
	 *            用户名称
	 * @param type
	 *            登录类型：（1登入，0登出）
	 * @param ip
	 *            用户操作的电脑客户端IP
	 * @return void 返回类型
	 */
	public final void logInfoLogin(String userId, String userName, int type,
			String ip) {
		// 操作数据库，具体模块实现的人实现此处插入动作
	}

	/**
	 * 
	 * 
	 *
	 * @Title: logErrorLogin
	 * @Description: 记录异常登录日志到文件
	 * @param logKeywords
	 *            日志（检索）关键字，推荐自定义一定格式的关键字，<br>
	 *            例如：[userId:10001-userName:mayue-ip:61.141.251.147...]
	 * @return void 返回类型
	 */
	public final void logErrorLogin(String logKeywords, Throwable ex) {
		String msg = String.format("[登录失败]%n[操作功能：用户登录]%n[请求参数：%s]",
				logKeywords);
		log.error(msg, ex);
	}

	/**
	 * 
	 * 记录正常业务日志到文件
	 *
	 * @Title: logInfoBussiness
	 * @Description: 记录正常业务日志到文件
	 * @param logKeywords
	 *            关键字
	 * @param busDescription
	 *            业务操作描述
	 * @param params
	 *            业务请求参数
	 * @param logContent
	 *            业务日志内容
	 * @param duration
	 *            耗时
	 * @return void 返回类型
	 */
	@Deprecated
	public final void logInfoBussiness(String logKeywords,
			String busDescription, Object params, String logContent,
			long duration) {
		String formatStr = "%s[" + busDescription
				+ "-成功][耗时：%s]%n[请求参数：%s]%n[业务日志内容：%s]";
		logContent = String.format(formatStr, logKeywords, duration, params,
				logContent);
		log.info(logContent);
	}
	public final void logInfoBussiness(String logKeywords,
			String busDescription, long duration,Object params, String logContent
			) {
		String formatStr = "%s[%s-成功][耗时：%s]%n[请求参数：%s]%n[业务日志内容：%s]";
		logContent = String.format(formatStr, logKeywords, busDescription,duration+"毫秒", params,
				logContent);
		log.info(logContent);
	}
	/**
	 * 
	 * @Title: logErrorBussiness
	 * @Description: 记录异常业务日志到文件和数据库
	 * @param logKeywords
	 *            关键字
	 * @param busDescription
	 *            业务操作描述
	 * @param params
	 *            业务请求参数
	 * @param logContent
	 *            业务日志内容
	 * @param duration
	 *            耗时
	 * @return void 返回类型
	 */
	public final void logErrorBussiness(String logKeywords,
			String busDescription, Object params, Throwable ex) {
		String formatStr = "%s[" + busDescription + "-失败]%n[请求参数：%s]";
		String msg = String.format(formatStr, logKeywords, params);
		log.error(msg, ex);
	}

	/**
	 * 获取日志头部。自行定义
	 * 
	 * @return 返回日志头部。
	 */
	@Deprecated
	public String getLogHeader() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append("省份");
		builder.append("-");
		builder.append("影院编码");
		builder.append("-");
		builder.append("影院名称");
		builder.append("]");
		return builder.toString();
	}
	/**
	 * 记录普通日志。
	 * 
	 * @param logKeywords
	 *            日志（检索）关键字，推荐自定义一定格式的关键字，<br>
	 *            例如：[cinimaArea:北京-cinimaCode:100001-cinimaName:大地影院...]
	 * @param content
	 *            日志内容
	 * @return void 返回类型
	 */
	public final void logInfo(String logKeywords,String content) {
		content = String.format(
				"%s%n[日志内容：%s]",
				logKeywords, content);
		log.info(content);
	}
	
	/**
	 * 记录普通单行日志
	 * @param content
	 */
	public final void logInfo(String content)
	{
		log.info(content);
	}
}
