<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">控件扩展 - 兜有院线通管理系统</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:tabs id="tabs">
	    <easyui:tab title="表单">
	    	<h3>textboxExt</h3>
	    	<p>增加属性width、height，设定默认宽度为193px</p>
	    	<p></p>
	    </easyui:tab>
	    <easyui:tab title="tab">
	    </easyui:tab>
	</easyui:tabs>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>