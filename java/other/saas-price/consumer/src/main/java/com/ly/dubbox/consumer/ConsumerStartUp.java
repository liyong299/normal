package com.ly.dubbox.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ly.dubbox.api.UserService;
 
public class ConsumerStartUp {
 
    public static void main(String[] args) {
    	 //测试常规服务
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/app-context.xml");
        context.start();
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.getUser(1L));
        
    	DemoConsumer.demo(args);
    	
    	Runtime.getRuntime().addShutdownHook(new Thread(){
    		public void run(){
    			context.close();
    		}
    	});
    }
}