/**
 * 项目名称：core
 * 文件包名：com.dup.test.util
 * 文件名称：MySQLConfig.java
 * 版本信息：SCEC_Branches
 * 生成日期：2015年12月21日 下午2:40:53
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.dup.test.util;

/**
 * @author ly
 *
 */
public class MySQLConfig
{
	private String userName;
	private String password;
	private String dbUrl;
	private boolean cacheCallableStmts = true;
	private int connectTimeout = 2000;
	private int LoginTimeout = 2000;
	private boolean useUnicode = true;
	
	private String encoding = "UTF-8";
	private String zeroDateTimeBehavior = "convertToNull";
	private int maxReconnects = 3;
	private int initConnets = 10;
	private int maxConnets = 10;
	private boolean autoReconnect = true;
	
	public MySQLConfig(String dbUrl, String userName, String password)
	{
		this.dbUrl = dbUrl;
		this.userName = userName;
		this.password = password;
	}
	/**
	 * @return the userName
	 */
	public String getUserName()
	{
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	/**
	 * @return the dbUrl
	 */
	public String getDbUrl()
	{
		return dbUrl;
	}
	/**
	 * @param dbUrl the dbUrl to set
	 */
	public void setDbUrl(String dbUrl)
	{
		this.dbUrl = dbUrl;
	}
	/**
	 * @return the cacheCallableStmts
	 */
	public boolean isCacheCallableStmts()
	{
		return cacheCallableStmts;
	}
	/**
	 * @param cacheCallableStmts the cacheCallableStmts to set
	 */
	public void setCacheCallableStmts(boolean cacheCallableStmts)
	{
		this.cacheCallableStmts = cacheCallableStmts;
	}
	/**
	 * @return the connectTimeout
	 */
	public int getConnectTimeout()
	{
		return connectTimeout;
	}
	/**
	 * @param connectTimeout the connectTimeout to set
	 */
	public void setConnectTimeout(int connectTimeout)
	{
		this.connectTimeout = connectTimeout;
	}
	/**
	 * @return the loginTimeout
	 */
	public int getLoginTimeout()
	{
		return LoginTimeout;
	}
	/**
	 * @param loginTimeout the loginTimeout to set
	 */
	public void setLoginTimeout(int loginTimeout)
	{
		LoginTimeout = loginTimeout;
	}
	/**
	 * @return the useUnicode
	 */
	public boolean isUseUnicode()
	{
		return useUnicode;
	}
	/**
	 * @param useUnicode the useUnicode to set
	 */
	public void setUseUnicode(boolean useUnicode)
	{
		this.useUnicode = useUnicode;
	}
	/**
	 * @return the encoding
	 */
	public String getEncoding()
	{
		return encoding;
	}
	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}
	/**
	 * @return the zeroDateTimeBehavior
	 */
	public String getZeroDateTimeBehavior()
	{
		return zeroDateTimeBehavior;
	}
	/**
	 * @param zeroDateTimeBehavior the zeroDateTimeBehavior to set
	 */
	public void setZeroDateTimeBehavior(String zeroDateTimeBehavior)
	{
		this.zeroDateTimeBehavior = zeroDateTimeBehavior;
	}
	/**
	 * @return the maxReconnects
	 */
	public int getMaxReconnects()
	{
		return maxReconnects;
	}
	/**
	 * @param maxReconnects the maxReconnects to set
	 */
	public void setMaxReconnects(int maxReconnects)
	{
		this.maxReconnects = maxReconnects;
	}
	/**
	 * @return the autoReconnect
	 */
	public boolean isAutoReconnect()
	{
		return autoReconnect;
	}
	/**
	 * @param autoReconnect the autoReconnect to set
	 */
	public void setAutoReconnect(boolean autoReconnect)
	{
		this.autoReconnect = autoReconnect;
	}
	/**
	 * @return the initConnets
	 */
	public int getInitConnets()
	{
		return initConnets;
	}
	/**
	 * @param initConnets the initConnets to set
	 */
	public void setInitConnets(int initConnets)
	{
		this.initConnets = initConnets;
	}
	/**
	 * @return the maxConnets
	 */
	public int getMaxConnets()
	{
		return maxConnets;
	}
	/**
	 * @param maxConnets the maxConnets to set
	 */
	public void setMaxConnets(int maxConnets)
	{
		this.maxConnets = maxConnets;
	}
}
