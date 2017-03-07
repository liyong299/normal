/**
 * 项目名称：core
 * 文件包名：com.dup.test.util
 * 文件名称：MySQLPool.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月21日 下午2:32:30
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * @author ly
 * 
 */
public class MySQLPool
{
	private MysqlDataSource ds;
	private static MySQLPool mysqlPool;
	private MySQLConfig config;
	private Map<Connection, Boolean> map = new ConcurrentHashMap<Connection, Boolean>();

	public MySQLPool(MySQLConfig config)
	{
		this.config = config;
		init(config);
	}

	public MySQLPool(String url, String userName, String password)
	{
		this.config = new MySQLConfig(url, userName, password);
		init(config);
	}
	
	public static MySQLPool getInstall(String url, String userName, String password)
	{
		MySQLConfig config = new MySQLConfig(url, userName, password);
		return getInstall(config);
	}
	
	public static MySQLPool getInstall(MySQLConfig config)
	{
		if (mysqlPool == null)
		{
			synchronized (MySQLPool.class)
			{
				if (mysqlPool == null)
					return new MySQLPool(config);
				else
					return mysqlPool;
			}
		}
		else
			return mysqlPool;
	}

	private void init(MySQLConfig config)
	{
		try
		{
			ds = new MysqlDataSource();
			if (config.getDbUrl() == null || "".equals(config.getDbUrl()))
			{
				throw new Exception("DB的url配置错误");
			}
			ds.setUrl(config.getDbUrl());
			if (config.getUserName() == null || "".equals(config.getUserName()) || config.getPassword() == null
					|| "".equals(config.getPassword()))
			{
				throw new Exception("DB的用户名或者密码配置错误");
			}
			ds.setUser(config.getUserName());
			ds.setPassword(config.getPassword());
			ds.setCacheCallableStmts(config.isCacheCallableStmts());
			ds.setConnectTimeout(config.getConnectTimeout());
			ds.setLoginTimeout(config.getLoginTimeout());
			ds.setUseUnicode(config.isUseUnicode());
			ds.setEncoding(config.getEncoding());
			ds.setZeroDateTimeBehavior(config.getZeroDateTimeBehavior());
			ds.setMaxReconnects(config.getMaxReconnects());
			ds.setAutoReconnect(config.isAutoReconnect());

			for (int i = 0; i < config.getInitConnets(); i++)
			{
				map.put(getConnection(), true);
			}
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 从连接池获取链接
	 * @return
	 */
	public synchronized Connection getConnection()
	{
		Connection conn = null;
		try
		{
			for (Entry<Connection, Boolean> entry : map.entrySet())
			{
				if (entry.getValue())  // 如果存在未被使用的则返回。
				{
					conn = entry.getKey();
					map.put(conn, false);
					return conn;
				}
			}
			
			// 链接池被占满，则重新生成。
			if (conn == null)
			{
				if (map.size() < config.getMaxConnets())
				{
					conn = getNewConnection();
					map.put(conn, false);
				}
				else
				{
					wait(100);
					conn = getConnection();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}

    /**
     * 释放链接
     * @param conn
     */
    public void releaseConnection(Connection conn)
	{
		if (conn == null)
		{
			return;
		}
		try
		{
			if (map.containsKey(conn))
			{
				if (conn.isClosed())
				{
					map.remove(conn);
				}
				else
				{
					if (!conn.getAutoCommit())
					{
						conn.setAutoCommit(true);
					}
					map.put(conn, true);
				}
			}
			else
			{
				conn.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
    
	/**
	 * 获取一个新的链接
	 * @return
	 */
	private Connection getNewConnection()
	{
		try
		{
			return ds.getConnection();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
