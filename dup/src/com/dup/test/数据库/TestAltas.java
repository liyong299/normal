/**
 * 
 */
package com.dup.test.数据库;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author hugoyang
 * 
 */
public class TestAltas {

	static PreparedStatement ps;
	static Connection conn;

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
//		String dbURL = "jdbc:mysql://172.16.34.12:1234/cec?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
		String dbURL = "jdbc:mysql://172.16.34.12:3306/test1?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
		String dbUserName = "cec";
		String dbPasswd = "cec";

		conn = getConn(dbURL, dbUserName, dbPasswd);
		
		testAltas();
	}
	
	
	
	public static void testAltas()
	{
		String sql1 = "insert into t_test(name) value('";

		String sql2 = "select * from t_test;";
		try {
			for (int i = 0; i < 1; i++)
			{
				executor(sql1 + i + "_name')");
				executorQuery(sql2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(null, ps, conn);
		}
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
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next())
			{
				System.out.println(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(rs, ps, null);
		}
	}
	
	/**
	 * ������Ʒ©��������
	 * 
	 * @param destTableName
	 * @param srcTableName
	 * @param priTime
	 */
	private static void executor(String sql)
	{
		System.out.println("sql : " + sql);

		try {
			ps = conn.prepareStatement(sql);
			boolean num = ps.execute();
			System.out.println("deal num is : " + num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(null, ps, null);
		}
	}

	public static Connection getConn(String url, String userName, String passwd) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, userName, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(ResultSet rs, PreparedStatement stat,
			Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (stat != null)
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
