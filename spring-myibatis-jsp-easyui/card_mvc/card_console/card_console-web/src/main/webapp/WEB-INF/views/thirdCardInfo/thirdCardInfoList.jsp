<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="卡bin"><easyui_ext:textBox id="cardBin"  value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="名称"><easyui_ext:textBox id="cardName" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="状态"><easyui:comboBox id="status"  style="width:80px" data="<%= com.mopon.helpers.ui.combobox.Data.getStatusList()%>"  ></easyui:comboBox></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			        <easyui_ext:facetButton id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()"/>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="id" title="ID"/>
			        <easyui:column field="cardBin" title="卡bin"/>
			        <easyui:column field="cardName" title="卡名称"/>
			        <easyui:column field="settlOrgzainationName" title="所属结算机构"/>
			        <easyui:column field="status" title="卡状态"  formatter="function(value, rowData, index){if(value == '0'){return '禁用';}else if(value == '1'){return '启用';}}"/>
			         <easyui:column field="feeType" title="分成方式"  formatter="function(value, rowData, index){if(value == '1'){return '按结算金额';}else if(value == '2'){return '按订单金额';}}"/>
			        <easyui:column field="feeRate" title="分成比例(%)"/>
			         <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="'rowData.id'" />
			        <easyui_ext:handlerColumn text="启用" onclick="datalist.Enable" param="'rowData.id'"  condition="rowData.status == '0'"/>
			        <easyui_ext:handlerColumn text="禁用" onclick="datalist.Disable" param="'rowData.id'" condition="rowData.status == '1'"/>
			        <easyui_ext:handlerColumn text="删除" onclick="datalist.Delete" param="'rowData.id'" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 400,height: 300,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = datalist;
	
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
	        	$("#layout_box iframe").attr("src", '../thirdCardInfo/thirdCardInfoAdd.html');
	            $('#layout_box').window({
	                title: "添加第三方卡"
	            });
	        },
	        Edit:function(id){
	        	$("#layout_box iframe").attr("src", '../thirdCardInfo/thirdCardInfoEdit.html?id=' + id);
	            $('#layout_box').window({
	                title: "编辑第三方卡"
	            });
	        },
	        Delete: function(id){
	        	$.messager.confirm("提示","确认删除吗？",function(b){
					if(b){
						$.ajax({
							    cache: true,
					            type: "POST",
					            data:"id="+id,
					            url:"../thirdCardInfo/deleteThirdCardInfo.do" ,			            
					            dataType: "json",
					            async: false,
					            error: function(request) {
					            	$.messager.alert("删除失败，请联系管理员！");
					            },
					            success: function(data) {
					            	 if(data.success){
						            	   datalist._temp.list_box.datagrid("reload");
			     		        		   Tips.Success("删除成功");			            	  
						               }else{
						            	   alert(data.errMsg);
						               }          
					            }
						});
					}
				});	
	        },
	        Enable:function(id){
	        	$.messager.confirm("提示","确认启用吗？",function(b){
					if(b){
						$.ajax({
							    cache: true,
					            type: "POST",
					            data:"id="+id+"&status=1",
					            url:"../thirdCardInfo/updateThirdCardInfo.do" ,			            
					            dataType: "json",
					            async: false,
					            error: function(request) {
					                alert("启用第三放卡失败");
					            },
					            success: function(data) {
					               if(data.success){
					            	   datalist._temp.list_box.datagrid("reload");
		     		        		   Tips.Success("成功");			            	  
					               }else{
					            	   $.messager.alert(data.errMsg);
					               }            
					            }
						});
					}
				});	
	        },
	        Disable:function(id){
	        	$.messager.confirm("提示","确认禁用吗？",function(b){
					if(b){
						$.ajax({
							    cache: true,
					            type: "POST",
					            data:"id="+id+"&status=0",
					            url:"../thirdCardInfo/updateThirdCardInfo.do" ,			            
					            dataType: "json",
					            async: false,
					            error: function(request) {
					                alert("启禁用第三放卡失败");
					            },
					            success: function(data) {
					               if(data.success){
					            	   datalist._temp.list_box.datagrid("reload");
		     		        		   Tips.Success("成功");			            	  
					               }else{
					            	   $.messager.alert(data.errMsg);
					               }            
					            }
						});
					}
				});	
	        },
	        Export:function(){
	        	var  cardBin = $("#cardBin").textbox("getValue");
				var cardName = $("#cardName").textbox("getValue");
				var status = $("#status").combobox("getValue");
				var url = "../thirdCardInfo/exportThirdCardInfo.do?cardBin=" + cardBin + "&cardName=" + cardName + "&status=" + status;
				window.open(url,'第三方卡列表','');
	        }
		};
		
		$(function(){
			datalist.Init();
		});
		
		
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>