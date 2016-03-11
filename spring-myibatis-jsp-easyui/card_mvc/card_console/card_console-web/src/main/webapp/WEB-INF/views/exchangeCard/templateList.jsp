<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">兑换卡模板</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
			<easyui_ext:searchItem name="模板编号"><easyui_ext:textBox id="templateid" /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="模板名称"><easyui_ext:textBox id="templatename" /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="status" data="<%= com.mopon.helpers.ui.combobox.Data.getCardticketTemplateStatus() %>" /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="templateList.do" fit="true" striped="true" rownumbers="true" pageSize="30" pagination="true" singleSelect="true" fitColumns="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="operation.Search()" />
			        <easyui_ext:facetButton id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="operation.Add()" />
			    </easyui:facet>
			    <easyui:columns frozen="true">
			    	<easyui:column field="templateidmaster" title="编号"/>
			        <easyui:column field="templatename" title="名称"/>
			        <easyui:column field="statusShow" title="状态"/>
			    </easyui:columns>
			    <easyui:columns>
			        <%-- <easyui:column field="isglobal" title="是否全局"/> --%>
			        <easyui:column field="createdateshow" title="创建时间"/>
			        <easyui:column field="creator" title="创建人"/>
			        <easyui:column field="modifieddateshow" title="最后修改时间"/>
			        <easyui:column field="modifier" title="修改人"/>
			        <easyui:column field="checkeddateshow" title="审核时间"/>
			        <easyui:column field="checker" title="审核人"/>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        	<easyui_ext:handlerColumn text="查看" onclick="operation.Display" param="'rowData.templateid'" />
			        	<easyui_ext:handlerColumn text="编辑" onclick="operation.Edit" param="'rowData.templateid'" condition="rowData.status == 1 || rowData.status == -2 || rowData.status == -3 || rowData.status == 4" />
			        	<easyui_ext:handlerColumn text="提交信息审核" onclick="operation.SubmitInformationCheck" param="'rowData.templateid'" condition="rowData.status == 1" />
			        	<easyui_ext:handlerColumn text="信息审核" onclick="operation.InformationCheck" param="'rowData.templateid'" condition="rowData.status == 2" />
			        	<easyui_ext:handlerColumn text="财务审核" onclick="operation.FinanceCheck" param="'rowData.templateid'" condition="rowData.status == 3" />
			        	<easyui_ext:handlerColumn text="删除" onclick="operation.Delete" param="'rowData.templateid'" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
	<div id="layout_box" style="overflow:hidden" data-options="width: 800,height: 565,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var operation = {
			_temp: {
	        	list_box: null,
	        	layout_box: null
	        },
			Init: function () {
	            var _this = operation;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	        },
	        Search: function(){
	        	operation._temp.list_box.datagrid('load', $(".search_box").serializeObject());
	        },
	        Display: function(templateid){
				var _this = operation;
	        	
	        	_this._temp.layout_box.find("iframe").attr("src", "display.html?templateid=" + templateid);
	            $('#layout_box').window({
	                title: "查看模板"
	            });
	        },
	        SubmitInformationCheck: function(templateid){
				var _this = operation;
	        	
	        	_this._temp.layout_box.find("iframe").attr("src", "submitInformationCheck.html?templateid=" + templateid);
	            $('#layout_box').window({
	                title: "提交信息审核"
	            });
	        },
	        InformationCheck: function(templateid){
				var _this = operation;
	        	
	        	_this._temp.layout_box.find("iframe").attr("src", "informationCheck.html?templateid=" + templateid);
	            $('#layout_box').window({
	                title: "信息审核"
	            });
	        },
	        FinanceCheck: function(templateid){
				var _this = operation;
	        	
	        	_this._temp.layout_box.find("iframe").attr("src", "financeCheck.html?templateid=" + templateid);
	            $('#layout_box').window({
	                title: "财务审核"
	            });
	        },
	        Add: function(){
	        	var _this = operation;
	        	
	        	_this._temp.layout_box.find("iframe").attr("src", "templateAdd.html");
	            $('#layout_box').window({
	                title: "添加模板"
	            });
	        },
	        Edit: function(templateid){
				var _this = operation;
	        	
	        	_this._temp.layout_box.find("iframe").attr("src", "templateEdit.html?templateid=" + templateid);
	            $('#layout_box').window({
	                title: "编辑模板"
	            });
	        },
	        Delete: function(templateid){
	        	$.messager.confirm("系统提示", "确定删除？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	 		        		url: "delete.do",
	 		        		para: {templateid: templateid},
	 		        		success: function(result){
	 		        			if(result.isSuccess){
	 		        				operation._temp.list_box.datagrid("reload");
	 			        			Tips.Success("成功");
	 			        		}
	 		        			else{
	 		        				parent.Tips.Error(result.message);
	 		        			}
	 		        		}
	 		        	});
	                 }
	        	 });
	        }
		};
		
		$(operation.Init);
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>