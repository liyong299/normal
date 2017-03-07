/**
 * 
 */
package com.dup.test.thread;

import java.util.Date;


/**
 * @author ly
 * 
 */
public class TaskTest2 implements Runnable
{

	@Override
	public void run()
	{
		try
		{
			Thread.currentThread().sleep(2000);
			System.out.println("--------" + Thread.currentThread().getName() + "|" + new Date());
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
