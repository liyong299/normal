/**
 * 
 */
package com.dup.test.thread;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author ly
 * 
 */
public class TaskTest implements Callable<Integer>
{
	@Override
	public Integer call() throws Exception
	{
		try
		{
			Thread.currentThread().sleep(1000);
			System.out.println("--------" + Thread.currentThread().getName()  + "|" + new Date());
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
