
<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="e" uri="org.topteam/easyui" %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>
	    <meta charset="UTF-8">
	    <title><e:templateBlock name="title"/> - 兜有院线通管理后台</title>
	    <link href="<%=request.getContextPath()%>/resources/css/base.css" rel="stylesheet" type="text/css"/> 
		<link href="<%=request.getContextPath()%>/resources/css/themes/easyui.css" rel="stylesheet" type="text/css"/>
		<link href="<%=request.getContextPath()%>/resources/css/themes/icon.css" rel="stylesheet" type="text/css"/>
		<link href="<%=request.getContextPath()%>/resources/css/themes/color.css" rel="stylesheet" type="text/css"/>

	    <e:templateBlock name="head"/>
	</head>
	<e:body animation="false" full="true">
	    <e:templateBlock name="body"/>
	    
	    <script src="<%=request.getContextPath()%>/resources/scripts/jquery-1.11.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jquery-migrate-1.2.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jquery.easyui.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/easyui-lang-zh_CN.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jquery.serialize-object.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jeasyui.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/base.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/common/common.js"></script>
	    <e:templateBlock name="script"/>
	</e:body>
</html>













