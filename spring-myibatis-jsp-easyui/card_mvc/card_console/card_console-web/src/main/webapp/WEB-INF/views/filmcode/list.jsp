<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
		<easyui_ext:searchItem name="影片名称"><easyui_ext:textBox id="filmname"  value=""  /></easyui_ext:searchItem>
		<easyui_ext:searchItem name="接入商类型"><easyui_ext:textBox id="provider"  value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="非标准影片编码"><easyui_ext:textBox id="filmno" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="标准影片编码"><easyui_ext:textBox id="normcode" value=""  /></easyui_ext:searchItem>			
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
					<easyui:button id="btn_edit" text="修改" plain="true"  iconCls="icon-edit"  onclick="edit()"></easyui:button>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="id" title="ID"/>
			        <easyui:column field="filmno" title="非标准影片编码"/>
			        <easyui:column field="filmname" title="影片名称"/>
			        <easyui:column field="publishdate" title="上映日期"/>
			        <easyui:column field="showtype" title="放映类型"/>
			        <easyui:column field="normcode" title="标准影片编码"/>
			        <easyui:column field="provider" title="接入商类型"/>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = List;
	
	            _this._temp.list_box = $("#list-grid");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_productbox = $("#layout_productbox");
	        },
	        Search: function () {
	        	 $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	        	
	        },
	        Deleteasyui: function(){
	        }
		};
		function edit(){
			var obj = $('#datalist').datagrid('getSelections')[0];
			if(obj == null){
				$.messager.alert('信息提示','请选择要编辑的信息！','success',null);
				return;
			}
			var id = obj.id;
			parent.openTab('编辑编码信息', '../film/toEdit.html?id=' + id);
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>