/**
 * 项目名称：ngc.alone
 * 文件包名：cn.mopon.cec.ng.log
 * 文件名称：LogIntercept.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年5月3日 下午2:06:35
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.server.actions.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 功能描述：<p color="red">通过aop拦截后记录一些日志信息</p>
 * 文件名称：LogIntercept.java
 * @author ly
 */
@Aspect
@Component
public class LogIntercept {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    
    // service和facade
    @Pointcut("execution(* com.dup.server.actions.platform..*.*(..))")
    public void recordLog1(){}
    
    // action拦截
    @Pointcut("execution(* com.dup.server.services..*.*(..))")
    public void recordLog2(){}
    
    @Pointcut("recordLog1() || recordLog2()")
    private void recordLog(){}
    

//    @Before("recordLog()")
//    public void before() {
//        this.printLog("已经记录下操作日志@Before 方法执行前");
//    }

    @Around("recordLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
	return aroundExecute(point);
    }

    public Object aroundExecute(ProceedingJoinPoint point) throws Throwable
    {
//	this.printLog("已经记录下操作日志@Around 方法执行前");
	String methodName = point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName();
	String flag = System.currentTimeMillis() + "_" + methodName + "_" + Math.random();
	String msg = String.format(
		"[Flag：%s][目标方法：%s][参数：%s][被织入的目标对象：%s]",
		flag,
		methodName, 
		Arrays.toString(point.getArgs()), point.getTarget());

	this.printLog(msg);
	
        long startTime = System.nanoTime();  
        
        Object result = point.proceed();
        
        long endTime = System.nanoTime();
        
        String msg2 = String.format(
		"[Flag：%s][执行时间： %s 毫秒]",
		flag,
		(endTime - startTime) / 1000000);
        
        this.printLog(msg2);  
        return result;
    }

//    @After("recordLog()")
//    public void after() {
//        this.printLog("已经记录下操作日志@After 方法执行后");
//    }

    private void printLog(String str){
//	log.warn(str);
	System.out.println(str);
    }
}