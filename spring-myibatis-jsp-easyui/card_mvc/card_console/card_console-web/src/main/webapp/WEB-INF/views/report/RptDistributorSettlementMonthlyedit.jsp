<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">标注</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
		<input id="id" name="id" value="${RptDistributorSettlementMonthly.id }"  type="hidden" />
        <div class="item"><label>分成金额：</label><easyui_ext:textBox id="fee" name="fee" value="${ RptDistributorSettlementMonthly.fee }" /></div>
        <div class="item"><label>对账金额：</label><easyui_ext:textBox id="checkAmount" name="checkAmount"  value="${RptDistributorSettlementMonthly.checkAmount }"/></div>
        <div class="item"><label>调帐：</label><easyui_ext:textBox id="manualAmount" name="manualAmount" value="${RptDistributorSettlementMonthly.manualAmount }"  /></div>
        <div class="item"><label>收款金额：</label><easyui_ext:textBox id="realAmount" name="realAmount"  value="${RptDistributorSettlementMonthly.realAmount }"  /></div>
        
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="doEdit()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>


<easyui:templateOverride name="script">
	<script>	   
	
	function doEdit() {
		if(validata()){
		$.ajax({
            cache: true,
            type: "POST",
            url:"../distrbutor/editRptDistributorSettlementMonthly.do",
            data:$('#info').serialize(),// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
               if(data.success){
            	   //$.messager.alert('信息提示','操作成功','success',null);
            	   //parent.refreshTab("添加用户");
            	   parent.datalist._temp.list_box.datagrid("reload");
            	   parent.Tips.Success("操作成功");
            	   Cancel();
               }else{
            	   parent.Tips.Error("操作失败");
               }            
            }
        });
	}
	}
	function validata(){
		  var isok = true;	
		 var fee = $("#fee").textbox("getValue");
	        if (fee == ""||fee<0) {
	            $("#fee").textbox("showError", "金额不能为空！");
	            isok= false;
	        }		
	    	var checkAmount = $("#checkAmount").textbox("getValue");		
	        if (checkAmount == ""||checkAmount<0) {
	            $("#checkAmount").textbox("showError", "金额不能为空！");
	            isok= false;
	        }  

	        var manualAmount = $("#manualAmount").textbox("getValue");		
	        if (manualAmount == ""||manualAmount<0) {
	            $("#manualAmount").textbox("showError", "金额不能为空！");
	            isok= false;
	        }  	    
	        var realAmount = $("#realAmount").textbox("getValue");		
	        if (realAmount == ""||realAmount<0) {
	            $("#realAmount").textbox("showError", "金额不能为空！");
	            isok= false;
	        } 
          return  isok;
	}
	
	function Cancel(){
		parent.datalist._temp.layout_box.window("close");
	}
	
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>