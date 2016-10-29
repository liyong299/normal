package com.ly.java.jta;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

public class JTATest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		XADataSource xaDs1=JTATest.getDataSource("jdbc:mysql://172.16.34.48:3306/cec_xf_test", "cec", "cec");
		XAConnection xaCon1=null;
		XAResource xaRes1 = null;
		Connection conn1=null;
		Statement stmt1 =null;
		
		
		// 在内部使用的 XAConnection 对象的工厂。实现 XADataSource 接口的对象通常在使用 Java Naming and Directory InterfaceTM (JNDI) 的命令服务中注册。
		XADataSource xaDs2=JTATest.getDataSource("jdbc:mysql://172.16.34.48:3306/cec_yml_xf", "cec", "cec");
		XAConnection xaCon2=null;
		XAResource xaRes2 = null;
		Connection conn2=null;
		Statement stmt2 =null;
		
		int ret1=0;
		int ret2=0;
		
		Xid xid1=new MyXid(100,new byte[]{0x01}, new byte[]{0x02});
		Xid xid2=new MyXid(100, new byte[]{0x01}, new byte[]{0x03});
		try {	
			xaCon1 = JTATest.getXAConnetion(xaDs1);
			conn1= JTATest.getConnection(xaCon1);
			stmt1=conn1.createStatement();
			xaRes1=xaCon1.getXAResource();
			
			xaCon2 = JTATest.getXAConnetion(xaDs2);
			conn2= JTATest.getConnection(xaCon2);
			stmt2=conn2.createStatement();
			xaRes2=xaCon2.getXAResource();
			
			xaRes1.start(xid1, XAResource.TMNOFLAGS); 
			stmt1.execute("INSERT INTO jta_user (uid,name,crateTime) VALUES (148,'aaabbb',UNIX_TIMESTAMP())");
			xaRes1.end(xid1, XAResource.TMSUCCESS);
			
			if (xaRes2.isSameRM(xaRes1)) {
				xaRes2.start(xid1, XAResource.TMNOFLAGS); 
				stmt2.execute("INSERT INTO jta_user (uid,name,crateTime) VALUES (148,'aaabbb',UNIX_TIMESTAMP())");
				xaRes2.end(xid1, XAResource.TMSUCCESS);
			}else{
				xaRes2.start(xid2, XAResource.TMNOFLAGS); 
				stmt2.execute("INSERT INTO jta_user (uid,name,crateTime) VALUES ('148','aaabbb',UNIX_TIMESTAMP())");
				xaRes2.end(xid2, XAResource.TMSUCCESS);
				ret1=xaRes2.prepare(xid2);
//				if (ret==XAResource.XA_OK) {
//					xaRes2.commit(xid2, false);
//				}
			}
			
			
			try {
			    Thread.currentThread().sleep(50000);
			} catch (InterruptedException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
			ret2 = xaRes1.prepare(xid1); 
			if (ret1 == XAResource.XA_OK&&ret2==XAResource.XA_OK) { 
			 //xaRes1.commit(xid1, false); 			 
			 xaRes1.rollback(xid1);		
			 if (xaRes2.isSameRM(xaRes1)) {
				xaRes2.rollback(xid1);
			 }else{
				 xaRes2.rollback(xid2);
			 }
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (XAException e) {
			e.printStackTrace();
		}
	}
	

	private static XADataSource getDataSource(String url,String user,String password) {
		MysqlXADataSource dataSource = new MysqlXADataSource();
		dataSource.setUrl(url);
		dataSource.setUser(user); 
		dataSource.setPassword(password);
		return dataSource;
	}	
	

	public static XAConnection getXAConnetion(XADataSource dataSource) {
		XAConnection XAConn = null;
		try {
			XAConn = dataSource.getXAConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return XAConn;
	}

	public static Connection getConnection(XAConnection XAConn) {
		Connection conn = null;
		try {
			conn = XAConn.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("连接关闭失败");
		}
	}

}


