<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">调帐管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
<form id="info" class="edit_box"  method="post" action="">
		 <input id="id" name="id" type="hidden" value="${id}" />
	 	<div class="item"><label>渠道名称：</label><easyui_ext:textBox id="payChannelName"  readonly="false"/></div>
        <div class="item"><label>平台销售金额：</label><easyui_ext:textBox id="saleAmount"  readonly="false" /></div>
        <div class="item"><label>银行收款金额：</label><easyui_ext:textBox id="bankAmount" /></div>
        <div class="item"><label>调帐：</label><easyui_ext:textBox id="manualAmount"/></div>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
       
</form>
</easyui:templateOverride>

<easyui:templateOverride name="script">
 <script>
 $(function(){
		
		$("#payChannelName").textbox("setValue", "${payChannelName}");
		$("#saleAmount").textbox("setValue", "${saleAmount}");
	});
 
 function Save(){
	 var id = $("#id").val();
	 var bankAmount = $("#bankAmount").textbox("getValue");
	 var manualAmount= $("#manualAmount").textbox("getValue");
	 
	 var data = {
			 id:id,
			 bankAmount:bankAmount,
			 manualAmount:manualAmount
	 }
	 
	 $.ajaxRequest({
 		url: "updateRptPayChannelCashMonthly.do",
 		para: data,
 		success: function(result){
 			if(result.isSuccess){
 				parent.datalist._temp.list_box.datagrid("reload");
     			parent.Tips.Success("成功");
     			Cancel();
     		}
 			else{
 				parent.Tips.Error(result.message);
 			}
 		}
 	});
 }
 
 function Cancel(){
		parent.datalist._temp.layout_box.window("close");
	}
 
 </script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>