<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">操作日志</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="菜单名称"><easyui_ext:textBox id="menuName" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="按钮名称"><easyui_ext:textBox id="btnName"  /></easyui_ext:searchItem>
 			    <easyui_ext:searchItem name="用户ID"><easyui_ext:textBox id="userID" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="日志状态"><easyui_ext:comboBox id="logStatus"   data="<%= com.mopon.helpers.ui.combobox.Data.getLogStatus() %>" /></easyui_ext:searchItem>
 			    <easyui_ext:searchItem name="记录时间"><easyui:inputDate id="singleSale_query_create_date_bagin" name="createDateBagin" value=""  />到:<easyui:inputDate id="singleSale_query_create_date_end" name="createDateEnd" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist"   rownumbers="true"  url="queryOperaterLog.do" fit="true" pageSize="30" pagination="true" >
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			    </easyui:facet>
			    <easyui:columns frozen="true">
			      <%--   <easyui:column field="ck" checkbox="true"/> --%>
			      <easyui:column field="logID"  align="center"  width="60" title="ID"  /> 
			       <%--  <easyui:column field="menuID" align="center"  width="80" title="菜单ID" /> --%>
			        <easyui:column field="menuName"  align="center"  width="100" title="菜单名称" />
			        <easyui:column field="btnName"  align="center"  width="80" title="按钮名称" />
			       <%--  <easyui:column field="userID"  align="center"  width="80" title="用户ID" /> --%>
			        <easyui:column field="userName" align="center"  width="80" title=" 用户名称"/>
			        <easyui:column field="memo" align="center"  width="300"  title="操作描述"/>
			        <easyui:column field="dataChangeInfos" title="数据变更情况" align="center"  width="300"  />
			        <easyui:column field="logStatus"  align="center"  width="50" title=" 状态" formatter="datalist.logStatus"/>
			        <easyui:column field="recTimeShow" align="center"  width="120" title="记录时间" />
			        <easyui:column field="_handler" title="操作" formatter="datalist.formatter" />
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box1" style="overflow:hidden" data-options="width: 400,height: 450,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	<div id="layout_box" style="overflow:hidden" data-options="width: 600,height: 450,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = datalist	;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_box1 = $("#layout_box1");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null,
	        	layout_box1: null
	        },
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Edit: function(memo,dataChangeInfos) {
	        	$("#layout_box iframe").attr("src",encodeURI(encodeURI("operaterLogDetail.html?memo="+memo+"&dataChangeInfos="+dataChangeInfos)));
	            $('#layout_box').window({
	                title: "查看详情"
	            });
	        },
	        Enable: function(id){
	         	$.messager.confirm("系统提示", "确定该撤消订单？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "cancelOrder.do",
	     	        		para: { orderNo: id },
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
	        formatter: function(value, rowData, index){
	        	var html = ""; 
	        	//if(rowData.btnName == '编辑' ){
	        		 var data = "'" + rowData.memo + "'" + "," + "'" + rowData.dataChangeInfos + "'";
	        	      html += "<a class=\'g-mlr-5\' onclick=\"datalist.Edit("+data+")\">详情</a>";  
	        	//}
	        	return html;
	        },
			orderType:function(value){
				if(value == '0'){
	        		return '退款';
	        	}else if(value == '1'){
	        		return '通兑票';
	        	}else if(value == '2'){
	        		return '选座票';
	        	}
			},
			logStatus:function(value, rowData, index){
	        	if(value == '0'){
	        		return '正常';
	        	}else if(value == '1'){
	        		return '异常';
	        	}
	        },
	        logDetail:function(value,rowData){ 
				//return "<a onclick=queryOrder("+value+")>"+value+"</a>";
				return "<a href=<%=request.getContextPath()%>/order/orderDetail.do?id="+value+" style=text-decoration:none;>"+value+"</a>";
			}
		};
		$(function(){
			datalist.Init();
		});
		
		function queryOrder(id){
			 $.ajax({
                 type: 'post',
                 cache: false,
                 dataType: 'json',
                 url: "../order/orderDetail.do",
                 data: { id: id },
                 async: true,
                 success: function (result) {
                     if (result.isSucceed) {
                         $("#layout_areabox").datagrid("reload");
                     }
                     else {
                         alert(result.message);
                     }
                 },
                 error: function () {
                 },
                 complete: function () {
                 }
             });
		}
		function numFormat(value){
	    	if(value == 0 ||value == null){
	    		return "";
	    	}else{
	    		return value;
	    	}
	    }
		function remove(){
			$("#panelShow").hide();
			$("#tab").children().remove();
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>