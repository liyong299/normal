/**
 * 
 */
package com.dup.test.数据库;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.mopon.cec.core.util.DateUtil;

/**
 * @author hugoyang
 * 
 */
public class Test主从同步问题 {

	static PreparedStatement ps;
	static Connection conn;

	static String dbURL = "jdbc:mysql://172.16.34.19:3306/cec_ly_replication?autoReconnect=true&amp;characterEncoding=UTF-8&useUnicode=false";
	static String dbUserName = "root";
	static String dbPasswd = "root";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		conn = getConn(dbURL, dbUserName, dbPasswd);
		//		Map<String, String[]> map = 查询("sendOrderPushFailMail2");
		//		System.out.println(map);
		//
		//		插入(conn, "Test");

		for (int m = 0; m < 4; m++) {
			String name = "Test" + m;
			ScheduledExecutorService schService = Executors.newScheduledThreadPool(32, new MyThreadFactory(name));
			for (int i = 0; i < 2; i++) {
				schService.scheduleAtFixedRate(new MyWorker(name, i), 0, 10, TimeUnit.MILLISECONDS);
			}
		}
		//		new MyWorker("Test3", 0).run();
	}

	public static int 插入(Connection conn, String name) {
		String id = UUID.randomUUID().toString();
		new DateUtil();
		String time = DateUtil.getCurrentDateTime();

		String sql = " INSERT INTO `CEC_TaskSchedule` VALUES (?, ?, ?)";
		return executor(conn, sql, id, name, time);
	}

	public static int 删除(Connection conn, String name) {
		String sql = "delete from CEC_TaskSchedule where id = ?";
		return executor(conn, sql, name);
	}

	public static Map<String, String> 查询(Connection conn, String name) {
		String sql2 = "select * from CEC_TaskSchedule where name = ?";
		Map<String, String> map = new HashMap<String, String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs == null)
				return null;

			String[] columns = new String[4];

			while (rs.next()) {
				columns[0] = rs.getString("id");
				columns[1] = rs.getString("name");
				map.put(columns[1], columns[0]);
			}
			rs.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(rs, ps, null);
		}
		return map;
	}

	/**
	 * 执行更新
	 * 
	 * @param destTableName
	 * @param srcTableName
	 * @param priTime
	 */
	private static int executor(Connection conn, String sql, String... params) {
		System.out.println("sql : " + sql);
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			int idx = 1;
			for (String param : params) {
				ps.setString(idx++, param);
			}

			int num = ps.executeUpdate();
			System.out.println("deal num is : " + num);
			return num;
		} catch (Exception e) {
			//			e.printStackTrace();
			System.out.println(e.getMessage());
			return -1;
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

	public static void closeAll(ResultSet rs, PreparedStatement stat, Connection conn) {
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

class MyWorker implements Runnable {
	private String name = "";
	private int idx = -1;

	public MyWorker(String name, int idx) {
		this.name = name;
		this.idx = idx;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "-" + idx + "-" + System.currentTimeMillis() + ".");
		Connection conn = Test主从同步问题.getConn(Test主从同步问题.dbURL, Test主从同步问题.dbUserName, Test主从同步问题.dbPasswd);
		try {
			conn.setAutoCommit(false);
			Map<String, String> map = Test主从同步问题.查询(conn, name);
			if (map == null || map.size() < 1) {
				Test主从同步问题.插入(conn, name);
				conn.commit();
				return;
			}
			String id = map.get(name);
			Test主从同步问题.删除(conn, id);
			Test主从同步问题.插入(conn, name);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			}
			System.out.println("-----------------" + e.getMessage());
		} finally {
			Test主从同步问题.closeAll(null, null, conn);
		}
	}
}
