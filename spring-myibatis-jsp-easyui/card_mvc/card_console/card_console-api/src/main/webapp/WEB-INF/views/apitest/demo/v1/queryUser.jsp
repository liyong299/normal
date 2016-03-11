<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryUser.jsp</title>
</head>

<% 
String path = request.getContextPath(); 
// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量 
String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+path+"/"; 
%> 

<body>
<div class="page" layouth="0">
    <div class="pageFormContent">
        <form class="pageForm required-validate" action="<%=basePath %>api/demo/v1/queryUser.do" method="post">
            <fieldset>
                <legend>查询单个用户信息接口</legend>
                
                <dl>
                    <dt>用户名称:</dt>
                    <dd><input type="text" name="appCode" id="appCode" value="admin" class="required"/></dd>
                </dl>
                <dl>
                    <dt>签名:</dt>
                    <dd><input type="text" name="sign" id="sign" class="required" value="0003"/></dd>
                </dl>
                <button type="button" queryType="json" onclick="submitJsonQuery(this)">返回JSON</button>
            </fieldset>
        </form>
    </div>
    <div layouth="120">
    <fieldset>
                <legend>查询单个用户信息结果</legend>
                <div id="result2"></div>
              
            </fieldset>
    </div>
</div>

</body>
<script>
     	
     		function submitJsonQuery(obj){
     			
     			var query={appCode : $("#appCode").val(), "sign": $("#sign").val()};
     			
     			$.ajaxRequest({
     		   		url:"<%=basePath %>api/demo/v1/queryUser.do",
     		   		para: {"appCode": $("#appCode").val(), "sign": $("#sign").val(), "query":query},
     				success: function(result){
     					
     					$("#result2").html(JSON.stringify(result));
     				}
     		     });
     		};
     		
     </script>
     <%@include file="/WEB-INF/views/share/include.jsp" %>
</html>
