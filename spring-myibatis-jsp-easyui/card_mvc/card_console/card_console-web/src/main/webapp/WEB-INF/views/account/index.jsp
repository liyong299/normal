<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">用户管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="登录名称"><easyui_ext:textBox id="name" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="用户姓名"><easyui_ext:textBox id="fullName" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="角色"><easyui_ext:comboBox id="roleID" data="<%= com.mopon.helpers.ui.combobox.Data.getRoleList() %>" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="status" width="<%= com.mopon.helpers.ui.combobox.Width.SHORT %>" data="<%= com.mopon.helpers.ui.combobox.Data.getStatusList() %>" /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="getlist.do" fit="true" pageSize="30" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			        <easyui_ext:facetButton id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()" />
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="name" title="登录名称"/>
			        <easyui:column field="fullName" title="用户姓名"/>
			        <easyui:column field="roleName" title="角色"/>
			        <easyui:column field="status" title="状态" formatter="function(value, rowData, index){if(value == '0'){return '禁用';}else if(value == '1'){return '启用';}}"/>
			        <easyui:column field="loginTimes" title="登录次数"/>
			        <easyui:column field="lastTimeShow" title="最后登录时间"/>
			        <easyui:column field="createTimeShow" title="创建时间"/>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="rowData.id" />
			        	<easyui_ext:handlerColumn text="启用" onclick="datalist.Enable" param="rowData.id" condition="rowData.status == '0'" />
			        	<easyui_ext:handlerColumn text="禁用" onclick="datalist.Disable" param="rowData.id" condition="rowData.status == '1'" />
			        	<easyui_ext:handlerColumn text="删除" onclick="datalist.Delete" param="rowData.id" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
	<div id="layout_box" style="overflow:hidden" data-options="width: 400,height: 250,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = datalist	;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null
	        },
	         Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },  
	        Add: function(){
	        	$("#layout_box iframe").attr("src", "add.html");
	            $('#layout_box').window({
	                title: "添加用户"
	            });
	        },
	        Delete: function(id){
	        	 $.messager.confirm("系统提示", "确定删除？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "delete.do",
	     	        		para: { id: id },
	     	        		success: function(result){
	     	        			if(result.isSuccess){
	     	        				datalist._temp.list_box.datagrid("reload");
	     		        			Tips.Success("成功");
	     		        		}
	     	        		}
	     	        	});
	                 }
	        	 });
	        },
	        Edit: function(id) {
	        	$("#layout_box iframe").attr("src", "edit.html?id=" + id);
	            $('#layout_box').window({
	                title: "编辑用户"
	            });
	        },
	        Enable: function(id){
	        	$.messager.confirm("系统提示", "确定启用？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "enable.do",
	     	        		para: { id: id },
	     	        		success: function(result){
	     	        			if(result.isSuccess){
	     	        				datalist._temp.list_box.datagrid("reload");
	     		        			Tips.Success("成功");
	     		        		}
	     	        		}
	     	        	});
	                 }
	        	 });
	        },
	        Disable: function(id){
	        	$.messager.confirm("系统提示", "确定禁用？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "disable.do",
	     	        		para: { id: id },
	     	        		success: function(result){
	     	        			if(result.isSuccess){
	     	        				datalist._temp.list_box.datagrid("reload");
	     		        			Tips.Success("成功");
	     		        		}
	     	        		}
	     	        	});
	                 }
	        	 });
	        }
		};
		
		$(function(){
			datalist.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>