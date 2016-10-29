package com.ly.java.jta;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * 多数据源事物处理 
 * @author KEVIN LUAN
 * @datetime Mar 3, 20135:44:42 PM
 * 
 */
public class ManyXADataSource {
	public static void main(String[] args) {
		ManyXADataSource mdt = new ManyXADataSource();
		try {
			mdt.manyXADataSourceTest();
		} catch (Exception ex) {
			System.out.println("除SQLException、XAException之外的异常: \n");
			ex.printStackTrace();
		}
	}

	public void manyXADataSourceTest() {
		// 定义所需用到的变量
		Connection mysqlConnec = null;
		Connection msSqlConnec = null;
		Connection mysql_Connec = null;
		Connection oracleConnec = null;

		MysqlXADataSource mysqlXADataSource = null;
		SQLServerXADataSource msSqlXADataSource = null;
		MysqlXADataSource mysql_XADataSource = null;
		OracleXADataSource oracleXADataSource = null;

		XAConnection mysqlXAConnection = null;
		XAConnection msSqlXAConnection = null;
		XAConnection mysql_XAConnection = null;
		XAConnection oracleXAConnection = null;

		XAResource mysqlXAResource = null;
		XAResource msSqlResource = null;
		XAResource mysql_Resource = null;
		XAResource oracleResource = null;

		Xid mysqlXid = null;
		Xid msSqlXid = null;
		Xid mysql_Xid = null;
		Xid oracleXid = null;

		Statement mysqlStatement = null;
		Statement msSqlStatement = null;
		Statement mysql_Statement = null;
		Statement oracleStatement = null;
		try {
			// 获得数据源
			mysqlXADataSource = new MysqlXADataSource();
			mysqlXADataSource.setURL("jdbc:mysql://localhost:3306/test");
			mysql_XADataSource = new MysqlXADataSource();
			mysql_XADataSource.setURL("jdbc:mysql://10.10.10.119:3306/test");
			msSqlXADataSource = new SQLServerXADataSource();
			msSqlXADataSource.setURL("jdbc:sqlserver://10.10.10.119:1433;DatabaseName=RTC;loginTimeout=20;user=sa;password=chgpwd122105");
			// sqlDs.setUser("sa");
			// sqlDs.setPassword("chgpwd122105");
			// sqlDs.setServerName("10.10.10.119");
			// sqlDs.setPortNumber(1433);
			// sqlDs.setDatabaseName("RTC");
			oracleXADataSource = new OracleXADataSource();
			oracleXADataSource.setURL("jdbc:oracle:thin:@10.10.10.119:1521:WMS");
			// 获得连接
			mysqlXAConnection = mysqlXADataSource.getXAConnection("root", "123456");
			System.out.println("xamysqlCn: " + mysqlXAConnection);
			msSqlXAConnection = msSqlXADataSource.getXAConnection();
			System.out.println("xasqlCn: " + msSqlXAConnection);
			mysql_XAConnection = mysql_XADataSource.getXAConnection("root", "9999");
			System.out.println("xamysqlCn2: " + mysql_XAConnection);
			oracleXAConnection = oracleXADataSource.getXAConnection("tiger", "tiger");
			System.out.println("xaoraCn: " + oracleXAConnection);

			mysqlConnec = mysqlXAConnection.getConnection();
			msSqlConnec = msSqlXAConnection.getConnection();
			mysql_Connec = mysql_XAConnection.getConnection();
			oracleConnec = oracleXAConnection.getConnection();

			mysqlStatement = mysqlConnec.createStatement();
			msSqlStatement = msSqlConnec.createStatement();
			mysql_Statement = mysql_Connec.createStatement();
			oracleStatement = oracleConnec.createStatement();
			// 定义XAResource
			mysqlXAResource = mysqlXAConnection.getXAResource();
			msSqlResource = msSqlXAConnection.getXAResource();
			mysql_Resource = mysql_XAConnection.getXAResource();
			oracleResource = oracleXAConnection.getXAResource();
			// 定义Xid
			mysqlXid = new MyXid(0, new byte[] { 0x01 }, new byte[] { 0x02 });
			msSqlXid = new MyXid(0, new byte[] { 0x01 }, new byte[] { 0x03 });
			mysql_Xid = new MyXid(0, new byte[] { 0x01 }, new byte[] { 0x04 });
			oracleXid = new MyXid(0, new byte[] { 0x01 }, new byte[] { 0x05 });
			// 执行Mysql
			mysqlXAResource.start(mysqlXid, XAResource.TMNOFLAGS);
			mysqlStatement.executeUpdate("insert into test values(4,'XA','F','Class4')");
			mysqlXAResource.end(mysqlXid, XAResource.TMSUCCESS);
			// 执行SQLServer
			msSqlResource.start(msSqlXid, XAResource.TMNOFLAGS);
			msSqlStatement.executeUpdate("insert into test values('444')");
			msSqlResource.end(msSqlXid, XAResource.TMSUCCESS);
			// 执行Mysql
			mysql_Resource.start(mysql_Xid, XAResource.TMNOFLAGS);
			mysql_Statement.executeUpdate("insert into test values(4,'XA','F','Class4')");
			mysql_Resource.end(mysql_Xid, XAResource.TMSUCCESS);
			// 执行Oracle
			System.out.println("xaoraRes: " + oracleResource);
			oracleResource.start(oracleXid, XAResource.TMNOFLAGS);
			oracleStatement.executeUpdate("insert into test123 values('4','44','444')");
			System.out.println("oraXid: " + oracleXid);
			oracleResource.end(oracleXid, XAResource.TMSUCCESS);
			// 准备
			int mysqlRes = mysqlXAResource.prepare(mysqlXid);
			int msSqlRes = msSqlResource.prepare(msSqlXid);
			int mysql_Res = mysql_Resource.prepare(mysql_Xid);
			int oracleRes = oracleResource.prepare(oracleXid);
			// 判断准备就绪与否 提交或回滚
			if (mysqlRes == XAResource.XA_OK && mysql_Res == XAResource.XA_OK && oracleRes == XAResource.XA_OK && msSqlRes == XAResource.XA_OK) {
				mysqlXAResource.commit(mysqlXid, false);
				System.out.println("Mysql 事务提交成功！");
				msSqlResource.commit(msSqlXid, false);
				System.out.println("SQLServer 事务提交成功！");
				mysql_Resource.commit(mysql_Xid, false);
				System.out.println("Mysql2 事务提交成功！");
				oracleResource.commit(oracleXid, false);
				System.out.println("Oracle 事务提交成功！");
			} else {
				mysqlXAResource.rollback(mysqlXid);
				msSqlResource.rollback(msSqlXid);
				mysql_Resource.rollback(mysql_Xid);
				oracleResource.rollback(oracleXid);
				System.out.println("事务回滚成功！");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				mysqlXAResource.rollback(mysqlXid);
				msSqlResource.rollback(msSqlXid);
				mysql_Resource.rollback(mysql_Xid);
				oracleResource.rollback(oracleXid);
			} catch (XAException e) {
				System.out.println("回滚也出错咯！~");
				e.printStackTrace();
			}
		} catch (XAException ex) {
			ex.printStackTrace();
		} finally {
			try {
				// 关闭
				mysqlStatement.close();
				mysqlConnec.close();
				mysqlXAConnection.close();
				msSqlStatement.close();
				msSqlConnec.close();
				msSqlXAConnection.close();
				mysql_Statement.close();
				mysql_Connec.close();
				mysql_XAConnection.close();
				oracleStatement.close();
				oracleConnec.close();
				oracleXAConnection.close();
			} catch (SQLException ex) {
				Logger.getLogger(ManyXADataSource.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
