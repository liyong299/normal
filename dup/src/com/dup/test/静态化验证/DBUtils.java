package com.dup.test.静态化验证;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DBUtils {
	static Connection conn;


	String dbURL = "jdbc:mysql://192.168.9.12:3306/cec_for_yxt_test?autoReconnect=true&characterEncoding=UTF-8";
	String dbUserName = "cec";
	String dbPasswd = "n2h@5B_AoP";
	
	public static void main(String[] args) 
	{
		if (args == null || args.length < 6) 
		{
			return;
		}
		DBUtils deal = new DBUtils();
//		conn = deal.getConn(dbURL, dbUserName, dbPasswd);

		try 
		{
			String cinemaCode = "";
			deal.queryChannelCodesByCinemaCode(cinemaCode);
			deal.queryCinemaCodes();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			System.out.println("data deal success!");
			deal.closeAll(null, null, conn);
		}
	}
	
	/**
	 * 查询所有的影院编码
	 * @param destTableName
	 * @param srcTableName
	 * @param priTime
	 */
	public Set<String> queryCinemaCodes() 
	{
		PreparedStatement ps;
		ResultSet ret = null;
		try {  
			String sql = "SELECT code from cec_cinema cma where cma.`status` = 1 and cma.ticketSetted = 1 ";
			
			conn = getConn(dbURL, dbUserName, dbPasswd);
			ps = conn.prepareStatement(sql);
            ret = ps.executeQuery();//执行语句，得到结果集  
            Set<String> codes = new HashSet<String>();
            while (ret.next()) 
            {  
                String code = ret.getString(1);  
                codes.add(code);
//                System.out.println(code);  
            }//显示数据  
            ret.close();  
            ps.close();//关闭连接  
            return codes;
        } catch (SQLException e) {  
            e.printStackTrace();  
            return null;
        }  
	}
	

	/**
	 * 根据影院编码，查询渠道
	 * @param destTableName
	 * @param srcTableName
	 * @param priTime
	 */
	public Set<String> queryChannelCodesByCinemaCode(String cinemaCode) 
	{
		PreparedStatement ps;
		ResultSet ret = null;
		try {  
			String sql = "SELECT code from CEC_Channel cel where cel.id in ("
					+ "SELECT channelId from CEC_ChannelPolicy cpy where cpy.id in("
					+ "SELECT policyId from CEC_ChannelRuleGroup crg where crg.cinemaId in "
					+ "(SELECT id from CEC_Cinema where code = '" + cinemaCode
					+ "'))" + ")";
			
			conn = getConn(dbURL, dbUserName, dbPasswd);
			ps = conn.prepareStatement(sql);
            ret = ps.executeQuery();//执行语句，得到结果集  
            Set<String> codes = new HashSet<String>();
            while (ret.next()) 
            {  
                String code = ret.getString(1);  
                codes.add(code);
                System.out.println(code);  
            }//显示数据  
            ret.close();  
            ps.close();//关闭连接  
            return codes;
        } catch (SQLException e) {  
            e.printStackTrace();  
            return null;
        }  
	}

	public Connection getConn(String url, String userName, String passwd) {
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

	public void closeAll(ResultSet rs, PreparedStatement stat, Connection conn) {
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
