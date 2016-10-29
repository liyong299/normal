package com.ly.java.jta.jdk;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
 
/**
 * @author zcshun
 * @see 提供了一个获得全局事务UserTransaction对象的方法。
 *      提供了两个获得分布式数据库服务器datasource对象。（通过在resin中间件中配置JNDI）
 *      提供了两个获得分布式数据库服务器的connection对象。
 * @date 2012-6-21 上午10:49:56
 */ 
public class text { 
     
    /**
     * 获取全局式事务接口
     */ 
    public static UserTransaction getUserTransaction(){ 
        UserTransaction userTransaction = null; 
        try { 
            Context initContext = new InitialContext(); 
            userTransaction = (UserTransaction) initContext.lookup("java:comp/UserTransaction"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return userTransaction; 
    }    
     
    /**
     * 功能：获取db2数据库的数据源
     */ 
    static private DataSource getDataSouceFromDB2() {
        DataSource _source = null; 
        if (_source == null){ 
            try { 
                Context context = new javax.naming.InitialContext(); //从JNDI取出java环境上下文对象 
                _source = (DataSource) context.lookup("java:comp/env/jdbc/db2Demo");//取出数据源 
            } catch (NamingException e) { 
                System.out.println("NamingException->"+e.toString()); 
            } 
        } 
        if(_source == null){ 
            System.out.println("_source为null"); 
        } 
        return _source; 
    } 
     
    /**
     * 功能：获取db2数据库的连接对象
     */ 
    public static Connection getConnectionFromDB2(){ 
        try{ 
            Connection c = getDataSouceFromDB2().getConnection(); //从连接池获取连接对象  
            //c.setReadOnly(false); 
            if(c!=null) 
                System.out.println("已经获得DB2数据库连接对象conn。"); 
            return c; 
        } catch (Exception e) { 
            System.err.println("JndiRes.getConnection() error."); 
            e.printStackTrace(); 
            return null; 
        } 
    } 
     
    /**
     * 功能：获取oracle数据库数据源：
     */ 
    static private DataSource getDataSouceFromOracleDB() { 
        DataSource _source = null; 
        if (_source == null){ 
            try { 
                Context env = new javax.naming.InitialContext(); 
                _source = (DataSource) env.lookup("java:comp/env/jdbc/oracleDemo"); 
            } catch (NamingException e) { 
                System.out.println("NamingException->"+e.toString()); 
            } 
        } 
        return _source; 
    } 
 
    /**
     * 功能：获得Oracle数据库连接对象。
     */ 
    public static Connection getConnectionFromOracleDB(){ 
        try{ 
            Connection c = getDataSouceFromOracleDB().getConnection(); 
            if(c!=null) 
                System.out.println("已经获得oracle数据库连接对象conn。"); 
            return c; 
        } catch (Exception e) { 
            System.err.println("JndiRes.getConnection() error."); 
            e.printStackTrace(); 
            return null; 
        } 
    }    
     
}