/**
 * 项目名称：java
 * 文件包名：com.ly.java.string
 * 文件名称：String溢出.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年4月8日 上午9:42:56
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.java.string;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.Date;
import java.util.Random;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

/**
 * 功能描述：
 * <p color="red">
 * 实现加载
 * </p>
 * 文件名称：String溢出.java
 * 
 * @author ly
 */
public class String溢出 {

    /**
     * <p>
     * 功能描述：
     * <p>
     * 方法功能
     * </p>
     * </p>
     * <p>
     * 实现逻辑：
     * <p>
     * 实现步骤
     * </p>
     * </p>
     * 
     * @param args
     */
    public static void main(String[] args) {

	test();
    }

    public static void test() {
	String溢出 aa = new String溢出();
	
	aa.new MyThread().start();
	aa.new MyThread().start();
	aa.new MyThread().start();
	aa.new MyThread().start();
	
	
	aa.new MonitorThread().start();
    }
    
    class MyThread extends Thread
    {
	public void run() {
		Random random = new Random(128);
		System.out.println(new Date());
		while (true) {
		    String str = random.nextLong() + "";
		    }
		
	    }
    }
    
    class MonitorThread extends Thread
    {
	public void run() {
		int count = 0, divisor = 100000000;
		while (true) {
		    if (count++ % divisor == divisor - 1) {
			try {
			    System.out.println("----------------------------------");
			    MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean(); 
			    MemoryUsage usage = memorymbean.getHeapMemoryUsage(); 
			    System.out.println("INIT HEAP: " + usage.getInit()); 
			    System.out.println("MAX HEAP: " + usage.getMax()); 
			    System.out.println("USE HEAP: " + usage.getUsed()); 
			    System.out.println("\nFull Information:"); 
			    System.out.println("Heap Memory Usage: " 
			    + memorymbean.getHeapMemoryUsage()); 
			    System.out.println("Non-Heap Memory Usage: " 
			    + memorymbean.getNonHeapMemoryUsage()); 
			    System.out.println("----------------------------------");
			    count = 0;
			} catch (Exception e) {
			    e.printStackTrace();
			}

		    }
		}
	    }
    }

    private static void memory() throws SigarException {
	Sigar sigar = new Sigar();
	Mem mem = sigar.getMem();
	// 内存总量
	System.out.println("内存总量:" + mem.getTotal() / 1024L + "K av");
	// 当前内存使用量
	System.out.println("当前内存使用量:" + mem.getUsed() / 1024L + "K used");
	// 当前内存剩余量
	System.out.println("当前内存剩余量:" + mem.getFree() / 1024L + "K free");
	Swap swap = sigar.getSwap();
	// 交换区总量
	System.out.println("交换区总量:" + swap.getTotal() / 1024L + "K av");
	// 当前交换区使用量
	System.out.println("当前交换区使用量:" + swap.getUsed() / 1024L + "K used");
	// 当前交换区剩余量
	System.out.println("当前交换区剩余量:" + swap.getFree() / 1024L + "K free");
    }

    private static void cpu() throws SigarException {
	Sigar sigar = new Sigar();
	CpuInfo infos[] = sigar.getCpuInfoList();
	CpuPerc cpuList[] = null;
	cpuList = sigar.getCpuPercList();
	for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
	    CpuInfo info = infos[i];
	    System.out.println("第" + (i + 1) + "块CPU信息");
	    System.out.println("CPU的总量MHz:" + info.getMhz());// CPU的总量MHz
	    System.out.println("CPU生产商:" + info.getVendor());// 获得CPU的卖主，如：Intel
	    System.out.println("CPU类别:" + info.getModel());// 获得CPU的类别，如：Celeron
	    System.out.println("CPU缓存数量:" + info.getCacheSize());// 缓冲存储器数量
	    printCpuPerc(cpuList[i]);
	}
    }

    private static void printCpuPerc(CpuPerc cpu) {
	System.out.println("CPU用户使用率:" + CpuPerc.format(cpu.getUser()));// 用户使用率
	System.out.println("CPU系统使用率:" + CpuPerc.format(cpu.getSys()));// 系统使用率
	System.out.println("CPU当前等待率:" + CpuPerc.format(cpu.getWait()));// 当前等待率
	System.out.println("CPU当前错误率:" + CpuPerc.format(cpu.getNice()));//
	System.out.println("CPU当前空闲率:" + CpuPerc.format(cpu.getIdle()));// 当前空闲率
	System.out.println("CPU总的使用率:" + CpuPerc.format(cpu.getCombined()));// 总的使用率
    }
}
