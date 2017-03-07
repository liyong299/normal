<!--首先导入一些必要的packages-->
<%@page import="java.text.MessageFormat"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!--告诉编译器使用SQL包-->
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.*" %>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=UTF-8"%>

<head>
<title>数据变动观察</title>
<link rel="shortcut icon" href="http://a.fsdn.com/con/img/sftheme/favicon.ico">
</head>

 <div style="margin:10px;width:100%;heigth:50px;">
    <a href="del_modify.jsp"  style="margin:0 5 0 5;">清理所有数据 </a>
    <a href="comp_channelshow.jsp" style="margin:0 5 0 5;">对比排期数据 </a>
    <a href="comp.jsp" style="margin:0 5 0 5;">查询数据变化</a>
</div>
<div  style="margin:10px;width:100%;heigth:50px;">
<h3>对比排期数据 </h3>
<form action="/dup/comp">
渠道编码：<input type="text" name="channelCode" />
影院编码：<input type="text" name="cinemaCode" />
<input type="submit" value="对比" />
</form>
</div>

	<%
		Connection con;
		Statement stmt;
		ResultSet rs;

		//加载驱动程序，下面的代码为加载MySQL驱动程序 
		Class.forName("com.mysql.jdbc.Driver");

		//注册MySQL驱动程序 
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());

		//用适当的驱动程序连接到数据库 
		//String dbUrl = "jdbc:mysql://172.16.34.12:3306/cec?user=root&password=abcd1001&useUnicode=true&characterEncoding=UTF-8";
		//String dbUser = "cec"; //用户名 
		//String dbPwd = "cec"; //密码 
		
		String dbUrl = "jdbc:mysql://192.168.9.12:3306/cec_for_yxt_test?user=root&password=abcd1001&useUnicode=true&characterEncoding=UTF-8";
		String dbUser = "cec"; //用户名 
		String dbPwd = "n2h@5B_AoP"; //密码 
		//建立数据库连接 
		con = java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPwd);

		//创建一个JDBC声明 
		stmt = con.createStatement();

		//查询记录 
		stmt.execute("delete from zz_modify");
		stmt.close();
		con.close();
	%>
