/**
 * 
 */
package com.dup.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ly
 *
 */
public class Thread2Test
{
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	private static ExecutorService executor2 = Executors.newFixedThreadPool(5);
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		List<TaskTest> tasks = new ArrayList<TaskTest>();
		tasks.add(new TaskTest());
		tasks.add(new TaskTest());
		tasks.add(new TaskTest());
		tasks.add(new TaskTest());
		tasks.add(new TaskTest());
		
		executor.invokeAll(tasks, 2000, TimeUnit.MILLISECONDS);
		
		System.out.println(new TaskTest().call());
		
		executor.execute(new TaskTest2());
		
		executor2.execute(new TaskTest2());
		
		System.out.println("==================");
		
		executor.shutdown();
	}
	
	

}
