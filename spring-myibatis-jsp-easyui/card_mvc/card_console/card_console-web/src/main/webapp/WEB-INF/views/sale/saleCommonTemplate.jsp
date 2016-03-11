<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">通兑票模板管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="模板编号"><easyui_ext:textBox id="templateId" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="模板名称"><easyui_ext:textBox id="templateName" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="产品类型"><easyui_ext:comboBox id="productType" data="<%= com.mopon.helpers.ui.combobox.Data.getProductType() %>" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="status" data="<%= com.mopon.helpers.ui.combobox.Data.getTemplateStatus() %>" /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" fitColumns="true" striped="true" rownumbers="true"  url="getlist.do" fit="true" pageSize="30" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			        <easyui_ext:facetButton id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()" />
			        <easyui_ext:facetButton id="btn_export" text="导出"  plain="true" iconCls="icon-page_excel"  onclick="exportTemplateList()"/>
			    </easyui:facet>
			    <easyui:columns  frozen="true">
			        <easyui:column field="templateId" title="模板编号" formatter="datalist.Detail" />
			        <easyui:column field="templateName" title="模板名称" />
			        <easyui:column field="statusValue" title="状态"/>
			        <easyui:column field="productTypeValue" title="产品类型"/>
			        <easyui:column field="useTimesValue" title="是否引用"/>
			        </easyui:columns>
			        <easyui:columns>
			        <easyui:column field="templateTypeValue" title="是否通用"/>
			        <easyui:column field="creator" title="创建人"/>
			        <easyui:column field="createdDate" title="创建时间"/>
			        <easyui:column field="modifier" title="修改人"/>
			        <easyui:column field="modifiedDate" title="修改时间"/>
			        <easyui:column field="checker" title="审核人"/>
			        <easyui:column field="checkedDate" title="审核时间"/>
			        <easyui:column field="memo" title="备注"/>
			        <%-- <easyui:column field="_handler" title="操作" formatter="datalist.formatter" /> --%>
			         <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        	<easyui_ext:handlerColumn text="信息审核" onclick="datalist.Check" param="'rowData.templateId',1" condition="rowData.status == '4'" />
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="'rowData.templateId'" condition="rowData.status == '0' || rowData.status == '1' || rowData.status == '3' || rowData.status == '5'" />
			        	<easyui_ext:handlerColumn text="删除" onclick="datalist.Delete" param="'rowData.templateId','rowData.useTimesValue'" condition="rowData.status == '0' || rowData.status == '1' || rowData.status == '3' || rowData.status == '5'" />
			        	<easyui_ext:handlerColumn text="财务审核" onclick="datalist.Check" param="'rowData.templateId',2" condition="rowData.status == '2'" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
	<div id="layout_box" style="overflow:hidden" data-options="width: 1000,height: 550,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
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
	                title: "添加模板"
	            });
	        },
	        Delete: function(templateId,useTimesValue){
	        	if(useTimesValue==="已引用")
	        		{
	        		datalist._temp.list_box.datagrid("reload");
	        		Tips.Error("该模板已被引用，不允许删除，请重试");
	        		return;
	        		}
	        	 $.messager.confirm("系统提示", "确定删除？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "delete.do",
	     	        		para: { templateId: templateId },
	     	        		success: function(result){
	     	        			if(result.isSucceed)
	     	        				{
	     	        				datalist._temp.list_box.datagrid("reload");
	     		        			Tips.Success(result.message);
	     	        				}
	     	        			else
	     	        				{
	     	        				Tips.Error(result.message);
	     	        				}
	     	        		}
	     	        	});
	                 }
	        	 });
	        },
	        Dispaly: function(templateId,checker) {
	        	$("#layout_box iframe").attr("src", "check.html?templateId=" + templateId+"&checker="+checker);
	            $('#layout_box').window({
	                title: "查看模板"
	            });
	        },
	        Edit: function(templateId) {
	        	$("#layout_box iframe").attr("src", "edit.html?templateId=" + templateId);
	            $('#layout_box').window({
	                title: "编辑模板"
	            });
	        },
	        Check: function(templateId,checker) {
	        	$("#layout_box iframe").attr("src", "check.html?templateId=" + templateId+"&checker="+checker);
	            $('#layout_box').window({
	                title: "审核模板"
	            });
	        },
			Detail:function(value,rowData){ 
				var html="<a class=\'g-mlr-5\' onclick=\'datalist.Dispaly(\"" + rowData.templateId + "\",0)\'>"+value+"</a>";
				return html;
			},
	        formatter: function(value, rowData, index){
	        	var html = ""; 
	        	
	        	/* html += "<a class=\'g-mlr-5\' onclick=\'datalist.Edit(" + rowData.id + ")\'>编辑</a>"; */
	        	
	        	/* if(rowData.status == '8'){
	        		html += "<a class=\'g-mlr-5\' onclick=\'datalist.Enable(" + rowData.id + ")\'>启用</a>";
	        	}
	        	else if(rowData.status == '9'){
	        		html += "<a class=\'g-mlr-5\' onclick=\'datalist.Disable(" + rowData.id + ")\'>禁用</a>";
	        	} */
	        	if(rowData.status == '4'){
	        		html += "<a class=\'g-mlr-5\' onclick=\'datalist.Check(\"" + rowData.templateId + "\",1)\'>信息审核</a>";
	        	}
	        	else if(rowData.status == '0' || rowData.status == '1' || rowData.status == '3' || rowData.status == '5' ){
	        		html += "<a class=\'g-mlr-5\' onclick=\'datalist.Edit(\"" + rowData.templateId + "\")\'>编辑</a>";
	        		html += "<a class=\'g-mlr-5\' onclick=\'datalist.Delete(\"" + rowData.templateId + "\",\""+rowData.useTimesValue+"\")\'>删除</a>";
	        	}
	        	else if(rowData.status == '2'){
	        		html += "<a class=\'g-mlr-5\' onclick=\'datalist.Check(\"" + rowData.templateId + "\",2)\'>财务审核</a>";
	        	}
	        	return html;
	        },
	        cellStyler:function(value, rowData, index){
	        	var b = $("#cardtmpl-grid").datagrid("getData");
	            for (var i = 0; i < b.rows.length; i++) {
	                switch (b.rows[i].Status) {
	                    case 3://待信息审核
	                        if (index == i) {
	                            return 'color:Orange;';
	                        }
	                        break;
	                    case 2://待财务审核
	                        if (index == i) {
	                            return 'color:Orange;';
	                        }
	                        break;
	                    case 1:
	                        if (index == i) {
	                            return 'color:green;';
	                        }
	                        break;
	                    case -9:
	                        if (index == i) {
	                            return 'color:gray;';
	                        }
	                        break;
	                    case -3://信息拒绝
	                        if (index == i) {
	                            return 'color:red;';
	                        }
	                        break;
	                    case -2://财务拒绝
	                        if (index == i) {
	                            return 'color:red;';
	                        }
	                        break;

	                    default:
	                        break;
	                }
	            }
	        }
		};
		function exportTemplateList(){
			var  templateId = $("#templateId").textbox("getValue");
			var templateName = $("#templateName").textbox("getValue");
			var productType = $("#productType").combobox("getValue");
			var status = $("#status").combobox("getValue");
			var url = "../commonTemplate/exportTemplateList.do?templateId=" + templateId + "&templateName=" + templateName 
					+ "&productType=" + productType+"&status=" + status;
			window.open(url,'模板列表','');
		}
		$(function(){
			datalist.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>