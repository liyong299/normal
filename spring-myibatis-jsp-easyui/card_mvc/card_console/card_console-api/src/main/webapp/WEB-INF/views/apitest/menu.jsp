<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口测试</title>
</head>

<% 
String path = request.getContextPath(); 
// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量 
String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+path+"/"; 
%> 

<body>
<div class="accordion" fillSpace="sidebar">
    <div class="accordionHeader">
        <h2><span class="a38">Folder</span>Demo接口</h2>
    </div>
    <div class="accordionContent">
        <ul class="tree">
        	<li><a href="<%=basePath%>apitest/demo/v1/queryUser.do">查询用户</a></li>
        </ul>
    </div>
</div>
</body>
</html>