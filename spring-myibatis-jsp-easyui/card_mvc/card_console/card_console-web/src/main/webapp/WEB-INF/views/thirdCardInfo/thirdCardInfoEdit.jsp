<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">编辑第三方卡</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
		<input id="id" name="id" value="${thirdCardInfo.id }"  type="hidden" />
        <div class="item"><label>卡BIN：</label><easyui_ext:textBox id="cardBin" name="cardBin" value="${thirdCardInfo.cardBin }" /><span class="star">*</span></div>
        <div class="item"><label>卡名称：</label><easyui_ext:textBox id="cardName" name="cardName"  value="${thirdCardInfo.cardName }"/><span class="star">*</span></div>
        <div class="item"><label>结算机构：</label><easyui_ext:comboBox id="settlOrgzainationId" name="settlOrgzainationId" value="${thirdCardInfo.settlOrgzainationId }"   data="${results }" /><span class="star">*</span></div>
        <div class="item"><label>分成方式：</label><easyui_ext:comboBox id="feeType" name="feeType" value="${thirdCardInfo.feeType }" data="<%= com.mopon.helpers.ui.combobox.Data.getFeeType()%>" /><span class="star">*</span></div>
        <div class="item"><label>分成比例(%)：</label><easyui_ext:textBox id="feeRate" name="feeRate"  value="${thirdCardInfo.feeRate }"  /><span class="star">*</span></div>
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
	            url:"../thirdCardInfo/editThirdCardInfo.do",
	            data:$('#info').serialize(),// 你的formid
	            dataType: "json",
	            async: false,
	            error: function(request) {
	                alert("Connection error");
	            },
	            success: function(data) {
	            	if(data.success){          	   
		            	parent.datalist._temp.list_box.datagrid("reload");
		       			parent.Tips.Success("成功");
		       			Cancel();
		               }else{
		            	   parent.Tips.Error(data.message);
		               }            
	            }
	        });
		}
	}
	
	function Cancel(){
		parent.datalist._temp.layout_box.window("close");
	}
	
	function validata(){
		var id = $("#id").val();
		var flag = true;	
		 var cardBin = $("#cardBin").textbox("getValue");
	        if (cardBin == "") {
	            $("#cardBin").textbox("showError", "卡bin不能为空！");
	            flag = false;
	        }
		
		var cardName = $("#cardName").textbox("getValue");		
        if (cardName == "") {
            $("#cardName").textbox("showError", "卡名称不能为空！");
            flag = false;
        }       
        
        var settlOrgzainationId = $("#settlOrgzainationId").combobox("getValue");
        if (settlOrgzainationId == "") {
            $("#settlOrgzainationId").textbox("showError", "结算机构不能为空！");
            flag = false;
        }

        var feeType = $("#feeType").combobox("getValue");
        if (feeType == "") {
            $("#feeType").textbox("showError", "分成方式不能为空！");
            flag = false;
        }

        var feeRate = $("#feeRate").textbox("getValue");
        if (feeRate == "") {
            $("#feeRate").textbox("showError", "分成比例不能为空！");
            flag = false;
        }
        var pattern = /^([0-9]\d*(\.\d+)?|0)$/;
        if(!pattern.test(feeRate)){
        	$("#feeRate").textbox("showError", "分成比例为大于等于0的数！");
            flag = false;
        }
       
        if(flag){
        	$.ajax({
            	type:"post",
            	url:"../thirdCardInfo/getThirdCardInfo.do?cardBin=" + cardBin + "&id=" + id,
            	dataType: "json",
                async: false,
                error: function(request) {
                    flag = false;
                },
                success: function(data) {
                    if(!data.success){          	   
                    	flag = false;
                    	$("#cardBin").textbox("showError", data.errMsg);
                    }            
                 }
            });       
        }
        return flag;
	}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>