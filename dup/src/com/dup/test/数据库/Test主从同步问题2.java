/**
 * 
 */
package com.dup.test.数据库;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author hugoyang
 * 
 */
public class Test主从同步问题2 {

	static PreparedStatement ps;
	static Connection conn;

	static String dbURL = "jdbc:mysql://172.16.34.19:3306/cec_ly_replication?autoReconnect=true&amp;characterEncoding=UTF-8&useUnicode=false";
	static String dbUserName = "root";
	static String dbPasswd = "root";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, 48);
		Date time = calendar.getTime();
		for (int m = 0; m < 4; m++) {
			String name = "Test" + m;
			for (int i = 0; i < 2; i++) {
				Timer timer = new Timer();
				timer.schedule(new MyTimerTask(name, i), time, 10);
			}
		}
	}
}

class MyTimerTask extends TimerTask {
	private String name = "";
	private int idx = -1;

	public MyTimerTask(String name, int idx) {
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
			//			Thread.currentThread().sleep(1);
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
