package com.mopon.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mopon.entity.common.Context;

/**
 * 应用程序上下文
 * @author chenyu
 *
 */
public class ApplicationContext {
	private static ThreadLocal<Context> values = new ThreadLocal<Context>();
	
	/*public static Context getContext(){
		return values.get();
	}*/
	
	/**
	 * 设置应用程序上下文
	 * @param context
	 */
	public static void setContext(Context context){
		values.set(context);
	}
	
	/**
	 * 获取请求对象
	 * @return
	 */
	public static HttpServletRequest request(){
		return values.get().getRequest();
	}
	
	/**
	 * 获取响应对象
	 * @return
	 */
	public static HttpServletResponse response(){
		return values.get().getResponse();
	}
	
	/**
	 * 获取cookie值
	 * @param key
	 * @return
	 */
	public static String cookie(String key){
		return values.get().getCookie(key);
	}
	
	/**
	 * 获取访问mvc控制器名称
	 * @param key
	 * @return
	 */
	public static String controller(){
		return values.get().getController();
	}
	
	/**
	 * 获取访问mvc方法名称
	 * @param key
	 * @return
	 */
	public static String action(){
		return values.get().getAction();
	}
	
	/**
	 * 清理应用程序上下文
	 */
	public static void clean(){
		Context context = values.get();
		context.setRequest(null);
		context.setResponse(null);
		context.setCookies(null);
		context.setController(null);
		context.setAction(null);
		values.set(null);
	}
}