<!--首先导入一些必要的packages-->
<%@page import="java.text.MessageFormat"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!--告诉编译器使用SQL包-->
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.*" %>
<!--设置中文输出-->
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<title>数据变动观察</title>
<link rel="shortcut icon" href="http://a.fsdn.com/con/img/sftheme/favicon.ico">
</head>
<body>
 <div style="margin:10px;width:99%;heigth:50px;">
    <a href="del_modify.jsp"  style="margin:0 5 0 5;">清理所有数据 </a>
    <a href="comp_channelshow.jsp" style="margin:0 5 0 5;">对比排期数据 </a>
    <a href="comp.jsp" style="margin:0 5 0 5;">查询数据变化</a>
</div>
<div  style="margin:10px;width:99%;heigth:50px;">
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
		String dbUrl = "jdbc:mysql://172.16.34.48:3306/cec_xf_test?user=root&password=abcd1001&useUnicode=true&characterEncoding=UTF-8";
		String dbUser = "cec"; //用户名 
		String dbPwd = "cec"; //密码 
		//建立数据库连接 
		con = java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPwd);

		//创建一个JDBC声明 
		stmt = con.createStatement();

		//查询记录 
		rs = stmt.executeQuery("select id,content,createtime,tablename,oprtype from zz_modify order by createtime desc ");

		//输出查询结果 
		
		int idx = 0;
		while (rs.next())
		{
			String content = rs.getString("content");
			String createtime = rs.getString("createtime");
			String tablename = rs.getString("tablename");
			String oprtype = rs.getString("oprtype");
			
			out.println("<table border=1  width='80%' style='margin-left:auto;margin-right:auto;margin-top:10px;margin-bottom:10px;'>");
			out.println(MessageFormat.format("<caption style='margin-top:10px;margin-bottom:10px;'>第【{0}】次表【{1}】在【{2}】被修改【{3}】。</caption>", ""+(++idx), tablename, createtime, oprtype));
			
			if (content == null || "".equals(content))
			{
				//打印所显示的数据 
				out.println("<tr width='100%'>"
						+ "<td width='100%'>没有获取到修改内容。</td>"
						+ "</tr>");
			}
			else
			{
				// 打印 表头 
				out.println("<tr width='100%' style='word-break: break-all;'>"
				        + "<th width='5%'> 表字段 </td>"
						+ "<th width='20%'> 旧的值 </td>"
						+ "<th width='10%'> 新的值 </td>"
						+ "</tr>");
				
				StringTokenizer st = new StringTokenizer(content, "#&");
				while (st.hasMoreTokens())
				{
					String row = st.nextToken();
					String[] rowarr = row.split("~");
					String oldContent = rowarr[0];
					String newContent = rowarr[1];
					String fieldName = oldContent.substring(oldContent.indexOf('.') + 1, oldContent.indexOf('='));
					String oldValue = oldContent.substring(oldContent.indexOf('=') + 1);
					String newValue = newContent.substring(newContent.indexOf('=') + 1);
				    
					if (newValue.equals(oldValue))
					{
						//打印所显示的数据 
						out.println("<tr width='100%' style='word-break: break-all;'>"
						        + "<td width='5%'>" + fieldName + "</td>"
								+ "<td width='20%'>" + oldValue + "</td>"
								+ "<td width='10%'>" + newValue + "</td>"
								+ "</tr>");
					}
					else
					{
						//打印所显示的数据 
						out.println("<tr width='100%' style='word-break: break-all;color:red;'>"
						        + "<td width='5%'>" + fieldName + "</td>"
								+ "<td width='20%'>" + oldValue + "</td>"
								+ "<td width='10%'>" + newValue + "</td>"
								+ "</tr>");
					}
				}
				
			}
			
			out.println("</table>");
		}
		
		//关闭数据库连结 
		rs.close();
		stmt.close();
		con.close();
	%>
	
	 
</body>
<div id="stuff" style="position: fixed;bottom: 20px; right: -50px;z-index: 888;width: 200px;">
	<a href="javascript:scroll(0,0)" mce_href="javascript:scroll(0,0)">返回顶部</a> 
	</div>
</html>
