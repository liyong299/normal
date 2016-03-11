<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">异常日志</easyui:templateOverride>
<easyui:templateOverride name="head">
<style type="text/css">
        #tab{ border-collapse:collapse; border:solid 1px Black; }
      #tab td{ border:solid 1px Black; padding:5px;} 
</style>
</easyui:templateOverride>
<easyui:templateOverride name="body">
<!-- 	<div id="panelShow" class="panel window " style="display: none; width: 600px; left: 300px; top: 80px; z-index: 9999; 	position: absolute;">
		<div class="panel-header panel-header-noborder window-header" style="width: 600px;">
			<div class="panel-title" style="cursor: move;"> <font >查看详情</font></div>
			<div class="panel-tool"><a class="panel-tool-close" href="javascript:remove();"></a></div>
		</div>
		<div class="messager-body panel-body panel-body-noborder window-body" title=""  style="width: 578px;height: 500px">
			<div>
			</div>
		</div>
	</div> -->
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="应用程序名"><easyui_ext:textBox id="applicationName" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="方法名"><easyui_ext:textBox id="funName"  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="异常信息"><easyui_ext:textBox id="expMsg"  /></easyui_ext:searchItem>
 			    <easyui_ext:searchItem name="记录时间"><easyui:inputDate id="singleSale_query_create_date_bagin" name="createDateBagin" value=""  />到:<easyui:inputDate id="singleSale_query_create_date_end" name="createDateEnd" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist"   rownumbers="true"  url="querySysExecptionLog.do" fit="true" pageSize="30" pagination="true" >
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			    </easyui:facet>
			    <easyui:columns frozen="true">
			    <easyui:column field="logID"  align="center"  width="60" title="ID" /> 
			   <easyui:column field="applicationName" title="应用程序名" align="center"  width="150" />
			        <easyui:column field="funName"  align="center"  width="300" title="方法名" />
			        <easyui:column field="expMsg"  align="center"  width="500" title="异常信息" />
			        <easyui:column field="expStackTrace" align="center"  width="250" title=" 异常堆栈" hidden="true"/> 
			        <easyui:column field="recTimeShow"  align="center"  width="120" title=" 记录时间" />
			      <easyui:column field="_handler" title="操作" formatter="datalist.formatter" /> 
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box1" style="overflow:hidden" data-options="width: 400,height: 450,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	<div id="layout_box" style="overflow:hidden" data-options="width: 600,height: 500,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
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
	        Edit: function(expStackTrace,expMsg) {
	        	$("#layout_box iframe").attr("src",encodeURI(encodeURI("logDetail.html?expStackTrace="+expStackTrace+"&expMsg="+expMsg)));
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
	        	      var data = "'" + rowData.expStackTrace + "'" + "," + "'" + rowData.expMsg + "'";
	        	      html += "<a class=\'g-mlr-5\' onclick=\"datalist.Edit("+data+")\">详情</a>"; 
	        	return html;
	        },
	        orderStatus:function(value, rowData, index){
		        	if(value == '0'){
		        		return '待付款';
		        	}else if(value == '1'){
		        		return '待出票';
		        	}else if(value == '2'){
		        		return '已出票';
		        	}else if(value == '3'){
		        		return '出票失败';
		        	}else if(value == '4'){
		        		return '退款待审核';
		        	}else if(value == '5'){
		        		return '已撤消';
		        	}else if(value == '6'){
		        		return '已关闭';
		        	}
			},
	        isMultPay:function(value, rowData, index){
	        	if(value == '0'){
	        		return '否';
	        	}else if(value == '1'){
	        		return '是';
	        	}
	        },
		};
		$(function(){
			datalist.Init();
		});
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