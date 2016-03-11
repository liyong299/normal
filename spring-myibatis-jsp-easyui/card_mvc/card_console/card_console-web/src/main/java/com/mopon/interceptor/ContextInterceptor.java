package com.mopon.interceptor;

import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mopon.entity.common.Context;;

/**
 * 应用程序上下文
 * @author chenyu
 *
 */
public class ContextInterceptor implements HandlerInterceptor {
	public ContextInterceptor() {  
        // TODO Auto-generated constructor stub  
    }  
  
    private String mappingURL;
    /*
     * 利用正则映射到需要拦截的路径    
     */
    public void setMappingURL(String mappingURL) {    
           this.mappingURL = mappingURL;    
   }
    
    /** 
     * 在业务处理器处理请求之前被调用 
     * 如果返回false 
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     *  
     * 如果返回true 
     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
     *    再执行被拦截的Controller 
     *    然后进入拦截器链, 
     *    从最后一个拦截器往回执行所有的postHandle() 
     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        // TODO Auto-generated method stub
    	Context context = new Context();
    	context.setRequest(request);
    	context.setResponse(response);
    	
    	Cookie[] cookies = request.getCookies();
    	HashMap<String, String> cookiesMap = new HashMap<String, String>();
    	if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookiesMap.put(cookie.getName(), URLDecoder.decode(cookie.getValue(), "UTF-8"));
			}
		}
    	context.setCookies(cookiesMap);
    	
    	String[] arr = request.getRequestURI().split("/");
    	context.setController(arr[arr.length-2].toLowerCase());
		context.setAction(arr[arr.length-1].toLowerCase().replace(".html", "").replace(".do", ""));
    	
    	ApplicationContext.setContext(context);
    	
        return true;  
    }  
  
    //在业务处理器处理请求执行完成后,生成视图之前执行的动作   
    @Override  
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
        // TODO Auto-generated method stub
    }  
  
    /** 
     * 在DispatcherServlet完全处理完请求后被调用  
     *  
     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
        // TODO Auto-generated method stub
    	//清理应用程序上下文资源
    	ApplicationContext.clean();
    } 
}