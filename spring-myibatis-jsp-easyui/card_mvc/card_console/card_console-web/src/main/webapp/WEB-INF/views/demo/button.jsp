<%@taglib prefix="e" uri="org.topteam/easyui" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<e:templateOverride name="title">按钮 - 兜有院线通管理系统</e:templateOverride>
<e:templateOverride name="head">
</e:templateOverride>
<e:templateOverride name="body">
	<e:layout id="pagecontent" fit="true">
		<e:button id="btn_search" text="查询" iconCls="icon-search" />
   		<e:button id="btn_add" text="添加" iconCls="icon-add" />
   		<e:button id="btn_edit" text="编辑" iconCls="icon-edit" />
	    <e:button id="btn_remove" text="删除" iconCls="icon-delete" />
	    <e:button id="btn_save" text="保存" iconCls="icon-save" />
	    <e:button id="btn_printer" text="打印" iconCls="icon-printer" />
	    <e:button id="btn_export" text="导出" iconCls="icon-page_excel" />
	    <e:button id="btn_enable" text="启用" iconCls="icon-tick" />
	    <e:button id="btn_disable" text="禁用" iconCls="icon-stop" />
	    <e:button id="text-btn" text="按钮" />
   	</e:layout>
</e:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>