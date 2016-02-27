/**
 * 
 */
package com.dup.core.thread;

import java.util.Random;

import org.junit.Test;

/**
 * @author hugoyang
 *
 */
public class ThreadLocalDemo implements Runnable {

	private static ThreadLocal<Student> threadLocal = new ThreadLocal<Student>();
	private static ThreadLocal<老师> 老师变量 = new ThreadLocal<老师>();
	
	/**
	 * @param args
	 */
	@Test
	public void test() {
		ThreadLocalDemo td = new ThreadLocalDemo();
        Thread t1 = new Thread(td, "a");
        Thread t2 = new Thread(td, "b");
        t1.start();
        t2.start();
	}

	public void run() 
	{
		String currentThreadName = Thread.currentThread().getName();
		//产生一个随机数并打印
        Random random = new Random();
        int age = random.nextInt(100);
        System.out.println("thread " + currentThreadName + " set age to:" + age);
        
        //获取一个Student对象，并将随机数年龄插入到对象属性中
        Student student = getStudent(currentThreadName);
        student.setName(age + "");
        
                        老师 老师实例1 = get老师(currentThreadName);
                        老师实例1.set性别(age);
        System.out.println("thread " + currentThreadName + " first read 老师实例1 is:" + 老师实例1.get性别());
        
        try 
        {
            Thread.sleep(age);
        }
        catch (InterruptedException ex) 
        {
            ex.printStackTrace();
        }
        System.out.println("thread " + currentThreadName + " second read age is:" + student.getName());
	}

	private Student getStudent(String currentThreadName) 
	{
		Student stu = threadLocal.get();
		
		System.out.println("Thread " + currentThreadName + " is running !");
		//线程首次执行此方法的时候，studentLocal.get()肯定为null
		if (stu == null)
		{
			System.out.println("Thread " + currentThreadName + " stu is null !");
			//创建一个Student对象，并保存到本地线程变量studentLocal中
			stu = new Student();
			
			threadLocal.set(stu);
		}
		return stu;
	}
	
	private 老师 get老师(String currentThreadName)
	{
		老师 stu = 老师变量.get();
		
		System.out.println("Thread " + currentThreadName + " is running !");
		//线程首次执行此方法的时候，studentLocal.get()肯定为null
		if (stu == null)
		{
			System.out.println("Thread " + currentThreadName + " stu is null !");
			//创建一个Student对象，并保存到本地线程变量studentLocal中
			stu = new 老师();
			
			老师变量.set(stu);
		}
		return stu;
	}

}

class Student
{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class 老师
{
	private int 性别;

	public int get性别() {
		return 性别;
	}

	public void set性别(int 性别) {
		this.性别 = 性别;
	}
	
}