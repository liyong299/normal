package com.mopon.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class ProfilingAspect {
	private Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);
	
	@Around("@annotation(com.mopon.util.Profiling)")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch clock = new StopWatch();  
        clock.start(); //计时开始  
        Object result = null;  
        //监控的类名  
        String className = pjp.getSignature().getDeclaringTypeName();  
        //监控的方法名  
        String methodName = className + "." + pjp.getSignature().getName();  
        try {  
            //这个是我们监控的bean的执行并返回结果  
            result = pjp.proceed();  
        } catch (Throwable e) {  
            //监控的参数  
            Object[] objs = pjp.getArgs();  
            logger.error("执行异常,方法名：" + methodName + "参数:" + getString(objs), e);  
            throw e;  
        }  
        clock.stop(); //计时结束  
        if (logger.isInfoEnabled()) {  
            logger.info("执行时间:" + clock.getTotalTimeMillis()+ " ms [" + methodName + "]");  
        }  
        return result;  
	}
	
	
	/** 
     * 这个类主要是用于输出方法的参数 
     *  
     * @param objs 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public String getString(Object[] objs) {  
        StringBuffer stringBuffer = new StringBuffer();  
        for (int i = 0, len = objs.length; i < len; i++) {  
            if (objs[i] instanceof String) {  
                stringBuffer.append("String类型：" + objs[i].toString());  
            } else if (objs[i] instanceof Map) {  
                HashMap<String, Object> hashMap = (HashMap<String, Object>) objs[i];  
                HashMap<String, Object> map = hashMap;  
               // HashSet<String> set = (HashSet<String>) map.keySet();  
                stringBuffer.append("Map类型");  
                Iterator iterator = map.keySet().iterator();
                while(iterator.hasNext()){
                	Object obj = iterator.next();
                	 stringBuffer.append(obj + "=" + map.get(obj));  
                }
                /*for (String str : set) {  
                    stringBuffer.append(str + "=" + map.get(str));  
                }  */
            } else if (objs[i] instanceof Integer) {  
                stringBuffer.append("整数类型：");  
                stringBuffer.append(objs[i].toString());  
            } else {  
                stringBuffer.append(objs[i].toString());  
            }  
        }  
        return stringBuffer.toString();  
    }  
  
}
