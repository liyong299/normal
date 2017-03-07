/**
 * 
 */
package com.dup.test.数据库;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hugoyang
 * 
 */
public class TestJDBC {

	static PreparedStatement ps;
	static Connection conn;

	static String dbURL = "jdbc:mysql://172.16.34.12:3306/cec?autoReconnect=true&amp;characterEncoding=UTF-8&useUnicode=false";
	static String dbUserName = "cec";
	static String dbPasswd = "cec";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		conn = getConn(dbURL, dbUserName, dbPasswd);
		修改所有影院数据();
	}

	public static void testByteArr() {
		String sql2 = "SELECT yxid,configTime, max(num),'_', RTRIM(concat(configTime, '_', max(num))) as stat from ( SELECT 	yxid,	configTime,	num FROM 	(SELECT yxid, configTime, count(1) as num from ( select (select parentId from CEC_Organ where id = (SELECT orgid from CEC_Cinema where id = cinemaId)) as yxid, SUBSTR(DATE_FORMAT(confirmTime, '%Y-%m-%d %H:%i%S') FROM 1 FOR 10) as configTime from CEC_TicketOrder tor where status = 4 ) a GROUP BY yxid,configTime  ORDER BY num DESC ) b where yxid = "
				+ "'aaa90c37-ecd1-4d6c-b73d-39537d345452'" + " ORDER BY num desc ) a GROUP BY yxid ";
		try {
			query(sql2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(null, ps, conn);
		}
	}

	public static Map<String, String[]> 查询编号最大的影院数据() {
		String sql2 = "select * from CEC_Cinema2 where code = (select max(code) from CEC_Cinema2)";
		Map<String, String[]> map = new HashMap<String, String[]>();
		try {
			ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			if (rs == null)
				return null;

			String[] columns = new String[4];

			while (rs.next()) {
				columns[0] = rs.getString(1);
				columns[1] = rs.getString(2);
				map.put(columns[0], columns);
			}
			rs.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(null, ps, null);
		}
		return map;
	}

	public static Map<String, String[]> 添加影院数据() {

		Map<String, String[]> map = 查询编号最大的影院数据();

		for (int i = 0; i < 100000; i++) {
			reInsertMaxCode();
		}
		return map;
	}

	private static void reInsertMaxCode() {
		String sql = " INSERT INTO CEC_Cinema2 select * from CEC_Cinema2 where code in (select max(code) from CEC_Cinema2) ";

		executor(getConn(TestJDBC.dbURL, TestJDBC.dbUserName, TestJDBC.dbPasswd), sql);
	}

	static ExecutorService exec1 = Executors.newFixedThreadPool(100);

	public static Map<String, String[]> 修改所有影院数据() {

		Map<String, String[]> map = 查询所有影院数据();

		CountDownLatch cdl = new CountDownLatch(map.size()); // 暂时不用
		int idx = 0;
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			idx++;
			System.out.println("idx  :  " + idx);
			//	    String id = modifyOneID2UUID(entry.getKey(), entry.getValue(), idx);
			//	    modifyOneCode(id, entry.getValue(), idx);
			exec1.execute(new ModifyWorker(entry.getKey(), entry.getValue(), idx, cdl));
		}
		closeAll(null, ps, conn);
		return map;
	}

	public static void modifyOneID(Connection conn, String key, String[] value, int idx) {
		String sql = "update CEC_Cinema2 set id = ''{0}'',modifieddate=now()  where id = ''{1}'' ";
		String str = String.format("%08d", idx);
		sql = MessageFormat.format(sql, key.subSequence(0, key.length() - 8) + str, key);
		System.out.println(sql);
		executor(conn, sql);
	}

	public static String modifyOneID2UUID(Connection conn, String key, String[] value, int idx) {
		String id = UUID.randomUUID().toString();
		String sql = "update CEC_Cinema2 set id = ''{0}'',modifieddate=now() limit 1";
		String str = String.format("%08d", idx);
		sql = MessageFormat.format(sql, id);
		System.out.println(sql);
		executor(conn, sql);
		return id;
	}

	public static void modifyOneCode(Connection conn, String key, String[] value, int idx) {
		String sql = "update CEC_Cinema2 set code = ''{0}'',modifieddate=now() where id = ''{1}'' ";
		String str = String.format("%08d", idx);
		String code = value[1];
		sql = MessageFormat.format(sql, code.subSequence(0, code.length() - 8) + str, key);
		System.out.println(sql);
		executor(conn, sql);
	}

	public static Map<String, String[]> 查询所有影院数据() {
		String sql2 = "select id, code,  SUBSTR(id FROM (LENGTH(id) -4)) as maxidx from CEC_Cinema2";
		Map<String, String[]> map = new HashMap<String, String[]>();
		try {
			ps = conn.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			if (rs == null)
				return null;

			String[] columns = new String[4];

			while (rs.next()) {
				columns[0] = rs.getString(1);
				columns[1] = rs.getString(2);
				columns[2] = rs.getString(3);
				map.put(columns[0], columns);
			}
			rs.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(null, ps, null);
		}
		return map;
	}

	public static void testAltas() {
		Connection conn = TestJDBC.getConn(TestJDBC.dbURL, TestJDBC.dbUserName, TestJDBC.dbPasswd);
		String sql1 = "insert into t_test(name) value('";

		String sql2 = "select * from t_test;";
		try {
			for (int i = 0; i < 1000; i++) {
				executor(conn, sql1 + i + "_name')");
				executor(conn, sql2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(null, ps, null);
		}
	}

	/**
	 * ������Ʒ©��������
	 * 
	 * @param destTableName
	 * @param srcTableName
	 * @param priTime
	 */
	private static void query(String sql) {
		System.out.println("sql : " + sql);

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs == null)
				return;
			int i = 1;
			while (rs.next()) {
				System.out.println(rs.getObject(i++));
				System.out.println(rs.getObject(i++));
				System.out.println(rs.getObject(i++));
				System.out.println(rs.getObject(i++));
				System.out.println(rs.getString(i++));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("data deal success!");
			closeAll(null, ps, null);
		}
	}

	/**
	 * ������Ʒ©��������
	 * 
	 * @param destTableName
	 * @param srcTableName
	 * @param priTime
	 */
	private static void executor(Connection conn, String sql) {
		System.out.println("sql : " + sql);
		PreparedStatement ps = null;
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

class ModifyWorker implements Runnable {
	private String key;
	private String[] value;
	private int idx;
	private CountDownLatch cdl;

	public ModifyWorker(String key, String[] value, int idx, CountDownLatch cdl) {
		this.idx = idx;
		this.key = key;
		this.value = value;
		this.cdl = cdl;
	}

	@Override
	public void run() {
		Connection conn = TestJDBC.getConn(TestJDBC.dbURL, TestJDBC.dbUserName, TestJDBC.dbPasswd);
		//	String id = TestJDBC.modifyOneID2UUID(conn, key, value, idx);
		String id = key;
		TestJDBC.modifyOneCode(conn, id, value, idx);
		TestJDBC.closeAll(null, null, conn);
	}
}
