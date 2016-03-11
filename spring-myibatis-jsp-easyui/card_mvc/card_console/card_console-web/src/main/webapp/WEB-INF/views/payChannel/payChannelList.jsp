<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="支付渠道编号"><easyui_ext:textBox id="payChannelNo"  value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="名称"><easyui_ext:textBox id="payChannelName" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
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
			        <easyui:column field="payChannelId" title="ID"/>
			        <easyui:column field="payChannelNo" title="支付渠道号"/>
			        <easyui:column field="payChannelName" title="支付渠道名称"/>
			        <easyui:column field="payOrganizationNo" title="支付机构编号(内部编号)"/>
			        <easyui:column field="status" title="状态"  formatter="function(value, rowData, index){if(value == '0'){return '禁用';}else if(value == '1'){return '启用';}}"/>
			         <easyui:column field="createTime" title="创建时间"/>
			        <easyui:column field="payFee" title="支付手续费率(千分比)"/>
			        <easyui:column field="payFeeType" title="手续费率方式"/>
			        <easyui:column field="mostFee" title="最高手续费"/>
			        <easyui:column field="backFee" title="退款手续费"/>
			        <easyui:column field="memo" title="支付渠道描述" />
			         <easyui:column field="isSecondPay" title="是否有二级支付方式" formatter="function(value, rowData, index){if(value == '0'){return '否';}else if(value == '1'){return '是';}}"/>
			        <easyui:column field="bankNo" title="支付渠道银行号"/>
			        <easyui:column field="payModel" title="支付模式"/>
			        <easyui:column field="isInnerPay" title="是否内部支付" formatter="function(value, rowData, index){if(value == '0'){return '否';}else if(value == '1'){return '是';}}"/>
			        <easyui:column field="isDefaultPay" title="是否默认支付渠道" formatter="function(value, rowData, index){if(value == '0'){return '否';}else if(value == '1'){return '是';}}"/>
			        <easyui:column field="provinceName" title="所属省份" />
			         <easyui:column field="cityName" title="所属城市"/>
			        <easyui:column field="logoUrl" title="支付渠道Logo"/>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="rowData.payChannelId" />
			        <easyui_ext:handlerColumn text="启用" onclick="datalist.Enable" param="rowData.payChannelId"  condition="rowData.status == '0'"/>
			        <easyui_ext:handlerColumn text="禁用" onclick="datalist.Disable" param="rowData.payChannelId" condition="rowData.status == '1'"/>
			        <easyui_ext:handlerColumn text="删除" onclick="datalist.Delete" param="rowData.payChannelId" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 600,height: 500,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
		
		        _temp: {
		        	list_box: null,
		        	layout_box: null
		        },
			Init: function () {
	            var _this = datalist;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	        },	        
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	        	$("#layout_box iframe").attr("src", "../payChannel/payChannelAdd.html");
	            $('#layout_box').window({
	                title: "添加支付渠道"
	            });
	        },
	        Edit:function(id){
	        	$("#layout_box iframe").attr("src", '../payChannel/payChannelEdit.html?id=' + id);
	            $('#layout_box').window({
	                title: "编辑支付渠道"
	            });
	        },
	        Delete: function(id){
	        	$.messager.confirm("提示","确认删除吗？",function(b){
					if(b){
						$.ajax({
							    cache: true,
					            type: "POST",
					            data:"id="+id,
					            url:"../payChannel/deletePayChannel.do" ,			            
					            dataType: "json",
					            async: false,
					            error: function(request) {
					            	alert("删除失败，请联系管理员！");
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
					            url:"../payChannel/updatePayChannel.do" ,			            
					            dataType: "json",
					            async: false,
					            error: function(request) {
					                alert("启用失败，请联系管理员！");
					            },
					            success: function(data) {
					               if(data.success){
					            	   datalist._temp.list_box.datagrid("reload");
		     		        		   Tips.Success("成功");
					               }else{
					            	   alert(data.errMsg);
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
					            url:"../payChannel/updatePayChannel.do" ,			            
					            dataType: "json",
					            async: false,
					            error: function(request) {
					                alert("禁用失败，请联系管理员！");
					            },
					            success: function(data) {
					               if(data.success){
					            	   datalist._temp.list_box.datagrid("reload");
		     		        		   Tips.Success("成功");
					               }else{
					            	   alert(data.errMsg);
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