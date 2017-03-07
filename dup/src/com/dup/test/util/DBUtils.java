/**
 * 项目名称：core
 * 文件包名：com.dup.test.util
 * 文件名称：DBUtils.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月21日 下午2:30:44
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 定义一个简单的数据库连接池
 * @author ly
 *
 */
public class DBUtils
{
	private MySQLPool pool;
	private static DBUtils dbUtil;
	
	public static DBUtils getInstall(String url, String userName, String password)
	{
		if (dbUtil == null)
		{
			synchronized (DBUtils.class)
			{
				if (dbUtil == null)
					return new DBUtils(url, userName, password);
				else
					return dbUtil;
			}
		}
		else
			return dbUtil;
	}
	public DBUtils(String url, String userName, String password)
	{
		pool = MySQLPool.getInstall(url, userName, password);
	}
	
	/**
	 * 执行更新操作
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql)
	{
		Connection conn = null;
		PreparedStatement stat = null;
		try
		{
			conn = pool.getConnection();
			stat = conn.prepareStatement(sql);
			return stat.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(stat, null);
		}
		return -1;
	}
	
	/**
	 * 执行更新操作
	 * @param sql
	 * @return
	 */
	public Object[][] executeQuery(String sql)
	{
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try
		{
			conn = pool.getConnection();
			stat = conn.prepareStatement(sql);
			ResultSetMetaData md = stat.getMetaData();
			rs = stat.executeQuery();
			
//			rs.afterLast();
			rs.last();
			Object[][] objs = new Object[rs.getRow()][];
			int j = 0;
			
			rs.beforeFirst();
			while (rs.next())
			{
				Object[] arr = new Object[md.getColumnCount()];
				for (int i = 1; i <= md.getColumnCount(); i++)
				{
					arr[i - 1] = rs.getObject(i);
				}
				objs[j] = arr;
				j++;
			}
			return objs;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(stat, rs);
		}
		return null;
	}
	
	public void close(Statement stat, ResultSet rs)
	{
		if (stat != null)
		{
			try
			{
				stat.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}
}
