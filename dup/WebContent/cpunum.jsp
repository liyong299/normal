<!--首先导入一些必要的packages-->
<%@page import="java.text.MessageFormat"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>

<!--设置中文输出-->
<%@ page contentType="text/html; charset=UTF-8"%>

<%
	out.println(Runtime.getRuntime().availableProcessors() * 2);
%>
