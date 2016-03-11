<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">角色管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search_box">
				<easyui_ext:searchItem name="角色名"><easyui_ext:textBox id="name" /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="getrolelist.do" fit="true" pageSize="30" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			        <easyui_ext:facetButton id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()" />
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="name" title="角色名" />
			        <easyui:column field="createTimeShow" title="创建时间" />
			        <easyui:column field="system" title="系统角色" formatter="function(value,rowData,index){if(value == '0'){return '否';}else{return '是';}}" />
			        <%-- <easyui:column field="_handler" title="操作" formatter="datalist.formatter" /> --%>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="rowData.id" />
			        	<easyui_ext:handlerColumn text="权限" onclick="datalist.Authority" param="rowData.id" />
			        	<easyui_ext:handlerColumn text="删除" onclick="datalist.Delete" param="rowData.id" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
	<div id="layout_box" style="overflow:hidden" data-options="width: 400,height: 150,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	<div id="authority_box" style="overflow:hidden" data-options="width: 500,height: 500,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			_temp: {
	        	list_box: null,
	        	layout_box: null,
	        	authority_box: null
	        },
			Init: function () {
	            var _this = datalist	;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.authority_box = $("#authority_box");
	        },
	        Search: function () {
	        	datalist._temp.list_box.datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	        	datalist._temp.layout_box.find("iframe").attr("src", "add.html");
	        	datalist._temp.layout_box.window({
	                title: "添加角色"
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
	        	datalist._temp.layout_box.find("iframe").attr("src", "edit.html?id=" + id);
	        	datalist._temp.layout_box.window({
	                title: "编辑角色"
	            });
	        },
	        Authority: function(id){
	        	datalist._temp.authority_box.find("iframe").attr("src", "authority.html?roleid=" + id);
	        	datalist._temp.authority_box.window({
	                title: "编辑权限"
	            });
	        },
	        formatter: function(value, rowData, index){
	        	var html = ""; 
	        	
	        	html += "<a class=\'g-mlr-5\' onclick=\'datalist.Edit(" + rowData.id + ")\'>编辑</a>";
	        	
	        	html += "<a class=\'g-mlr-5\' onclick=\'datalist.Authority(" + rowData.id + ")\'>权限</a>";
	        	
	        	html += "<a class=\'g-mlr-5\' onclick=\'datalist.Delete(" + rowData.id + ")\'>删除</a>";
	        	return html;
	        }
		};
		
		$(function(){
			datalist.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>