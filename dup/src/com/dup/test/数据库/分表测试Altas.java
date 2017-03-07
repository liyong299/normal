/**
 * 项目名称：core
 * 文件包名：com.dup.test.jdbc
 * 文件名称：分表测试Altas.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月21日 下午2:29:21
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.数据库;

import com.dup.test.util.DBUtils;

/**
 * @author ly
 * 
 */
public class 分表测试Altas
{
	private static DBUtils dbutil = null;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String dbURL = "jdbc:mysql://172.16.34.12:1234/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
		String userName = "cec";
		String password = "cec";

		dbutil = DBUtils.getInstall(dbURL, userName, password);

		select(1); // 不可以
		selectwhere("1");
		selectjoin("1");
//		insert(1);
//		update(2);
//		delete(3);
//		deleteall(); // 不可以
	}

	public static void select(int num)
	{
		String sql2 = "select * from t_test limit ";
		executorQuery(sql2 + num);
	}
	
	public static void selectwhere(String id)
	{
		String sql2 = "select * from t_test where id = '";
		executorQuery(sql2 + id + "'");
	}
	
	public static void selectjoin(String id)
	{
		String sql2 = "select * from t_test a join t_test_tmp b on a.id = b.id where b.id = '";
		executorQuery(sql2 + id + "'");
	}
	
	public static void insert(int num)
	{
		String sql1 = "insert into t_test(id, name) value(";
		for (int i = 0; i < num; i++)
		{
			executorUpdate(sql1 + i + ", '" + i + "_name')");
		}
	}
	
	public static void update(int num)
	{
		String sql1 = "update t_test set name='";
		executorUpdate(sql1 + Math.random() * 1000 + "_name' where id = " + num);
	}
	
	public static void delete(int num)
	{
		String sql1 = "delete from t_test where id = ";
		executorUpdate(sql1 + num);
	}
	
	public static void deleteall()
	{
		String sql1 = "delete from t_test ";
		executorUpdate(sql1);
	}

	/**
	 * 查询
	 * 
	 * @param destTableName
	 * @param srcTableName
	 * @param priTime
	 */
	private static void executorQuery(String sql)
	{
		System.out.println("sql : " + sql);
		try
		{
			Object[][] objs = dbutil.executeQuery(sql);
			for (Object[] arr : objs)
			{
				for (Object obj : arr)
				{
					System.out.printf("%s  ", obj);
				}
				System.out.println();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("data deal success!");
		}
	}

	/**
	 * 更新操作
	 * 
	 * @param destTableName
	 * @param srcTableName
	 * @param priTime
	 */
	private static void executorUpdate(String sql)
	{
		System.out.println("sql : " + sql);
		try
		{
			int result = dbutil.executeUpdate(sql);
			System.out.println("num is : " + result);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("data deal success!");
		}
	}
}
