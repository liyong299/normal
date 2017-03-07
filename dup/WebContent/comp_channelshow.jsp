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
旧的地址：<input type="text" name="oldURL" value=""/>
新的地址：<input type="text" name="newURL" value=""/>
<input type="submit" value="对比" />
</form>
</div>

</body>
</html>
