<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">编辑支付渠道</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
		<input id="id" name="payChannelId" value="${payChannel.payChannelId }" type="hidden" />
        <div class="item"><label>支付渠道号：</label><easyui_ext:textBox id="payChannelNo" name="payChannelNo" value="${payChannel.payChannelNo }" readonly="true" /><span class="star">*</span></div>
        <div class="item"><label>支付渠道名称：</label><easyui_ext:textBox style="ime-mode: disabled" id="payChannelName" name="payChannelName" value="${payChannel.payChannelName }" /><span class="star">*</span></div>
        <div class="item"><label>支付机构编号(内部编号)：</label><easyui_ext:textBox id="payOrganizationNo" name="payOrganizationNo" value="${payChannel.payOrganizationNo }"/><span class="star">*</span></div>
        <div class="item"><label>支付机构名称：</label><easyui_ext:textBox id="payOrganizationName" name="payOrganizationName" value="${payChannel.payOrganizationName }"/><span class="star">*</span></div>
        <div class="item"><label>支付手续费率(千分比)：</label><easyui_ext:textBox id="payFee" name="payFee"  value="${payChannel.payFee }"  /><span class="star">*</span></div>
        <div class="item"><label>手续费率方式：</label><easyui_ext:textBox id="payFeeType" name="payFeeType" value="${payChannel.payFeeType }" /></div>
        <div class="item"><label>最高手续费：</label><easyui_ext:textBox id="mostFee" name="mostFee"  value="${payChannel.mostFee }"  /><span class="star">*</span></div>
        <div class="item"><label>退款手续费：</label><easyui_ext:textBox id="backFee" name="backFee"  value="${payChannel.backFee }" /><span class="star">*</span></div>
             
        <div class="item"><label>支付渠道银行号：</label><easyui_ext:textBox id="bankNo" name="bankNo" value="${payChannel.bankNo }" /><span class="star">*</span></div>
        <div class="item"><label>支付模式：</label><easyui_ext:textBox id="payModel" name="payModel" value="${payChannel.payModel }" /></div>
       <div class="item">
           <label>是否有二级支付方式：</label>
           <input type="radio" name="isSecondPay" id="isSecondPay1"  value="1"/><label for="isSecondPay1">是</label>
           <input type="radio" name="isSecondPay" id="isSecondPay2"  value="0"/><label for="isSecondPay2">否</label>
        </div>
        <div class="item">
           <label>是否内部支付：</label>
           <input type="radio" name="isInnerPay" id="isInnerPay1"  value="1"/><label for="isInnerPay1">是</label>
           <input type="radio" name="isInnerPay" id="isInnerPay2"  value="0"/><label for="isInnerPay2">否</label>
        </div>
        <div class="item">
           <label>是否默认支付渠道：</label>
           <input type="radio" name="isDefaultPay" id="isDefaultPay1"  value="1"/><label for="isDefaultPay1">是</label>
           <input type="radio" name="isDefaultPay" id="isDefaultPay2"  value="0"/><label for="isDefaultPay2">否</label>
        </div>
        <div class="item"><label>所属省份：</label><easyui_ext:textBox id="provinceNo" name="provinceNo" /></div>
        <div class="item"><label>所属城市：</label><easyui_ext:textBox id="cityNo" name="cityNo" /></div>
        <div class="item"><label>支付渠道Logo：</label><easyui_ext:textBox id="logoUrl"  name="logoUrl" value="${payChannel.logoUrl }"/></div>
        <div class="item"><label>支付渠道描述：</label><easyui_ext:textBox id="memo"  name="memo" value="${payChannel.memo }"/></div>   
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="doEdit()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>

<easyui:templateOverride name="script">
	<script>
$(function(){
		
		// 省级 
	    $('#provinceNo').combobox({
	           valueField:'areaNo', //值字段
	           textField:'areaName', //显示的字段
	           url:'../area/getProvince.do',
	           //panelHeight:'auto',//panelHeight自适应会随着选项的增多而变长，否则会有滚动条
	           editable:false,//不可编辑，只能选择
	           value:'--请选择--',//默认选中值得ID
	           onChange:function(areaNo){
		                $('#cityNo').combobox({
		                valueField:'areaNo', //值字段
		                textField:'areaName', //显示的字段
		                url:'../area/getCityByProvinceId.do?areaNo='+areaNo,
		                //panelHeight:'auto',
		                editable:false,//不可编辑，只能选择
		                value:'--请选择--',
		            });
	           }
	        });
		
	    $('#provinceNo').combobox('select', '${payChannel.provinceNo}');
	    $('#cityNo').combobox('select', '${payChannel.cityNo}');
	    
	    if("${payChannel.isSecondPay}" == 1){
	    	$("#isSecondPay1").attr("checked", "true")
	    }else{
	    	$("#isSecondPay2").attr("checked", "true")
	    }
	    
	    if("${payChannel.isInnerPay}" == 1){
	    	$("#isInnerPay1").attr("checked", "true")
	    }else{
	    	$("#isInnerPay2").attr("checked", "true")
	    }
	    
	    if("${payChannel.isDefaultPay}" == 1){
	    	$("#isDefaultPay1").attr("checked", "true")
	    }else{
	    	$("#isDefaultPay2").attr("checked", "true")
	    }
	});
	
	function doEdit() {		
		if(validata()){
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:"../payChannel/editPayChannel.do",
	            data:$('#info').serialize(),// 你的formid
	            dataType: "json",
	            async: false,
	            error: function(request) {
	                alert("编辑失败，请联系管理员!");
	            },
	            success: function(data) {
	               if(data.success){          	   
	            	parent.datalist._temp.list_box.datagrid("reload");
	       			parent.Tips.Success("成功");
	       			Cancel();
	               }else{
	            	   //parent.Tips.Error(data.errMsg);
	            	   alert("编辑失败，错误内容为:" + data.errMsg);
	               }            
	            }
	        });
		}		
	}
	
	function Cancel(){
		parent.datalist._temp.layout_box.window("close");
	}
	
	function validata(){
		var flag = true;
		var pattern = /^([0-9]\d*(\.\d+)?|0)$/;
		 var payChannelNo = $("#payChannelNo").textbox("getValue");
	        if (payChannelNo == "") {
	            $("#payChannelNo").textbox("showError", "支付渠道号不能为空！");
	            flag = false;
	        }
		
		var payChannelName = $("#payChannelName").textbox("getValue");		
        if (payChannelName == "") {
            $("#payChannelName").textbox("showError", "支付渠道名称不能为空！");
            flag = false;
        }       
        
        var payOrganizationNo = $("#payOrganizationNo").textbox("getValue");
        if (payOrganizationNo == "") {
            $("#payOrganizationNo").textbox("showError", "支付机构编号不能为空！");
            flag = false;
        }

        var payOrganizationName = $("#payOrganizationName").textbox("getValue");
        if (payOrganizationName == "") {
            $("#payOrganizationName").textbox("showError", "支付机构名称不能为空！");
            flag = false;
        }

        
        var payFee = $("#payFee").textbox("getValue");
        if (payFee == "") {
            $("#payFee").textbox("showError", "支付手续费率不能为空！");
            flag = false;
        }
        if(!pattern.test(payFee)){
        	$("#payFee").textbox("showError", "支付手续费率为大于等于0的数！");
            flag = false;
        }

        var mostFee = $("#mostFee").textbox("getValue");
        if (mostFee == "") {
            $("#mostFee").textbox("showError", "最高手续费不能为空！");
            flag = false;
        }
        if(!pattern.test(mostFee)){
        	$("#mostFee").textbox("showError", "最高手续费为大于等于0的数！");
            flag = false;
        }

        var backFee = $("#backFee").textbox("getValue");
        if (backFee == "") {
            $("#backFee").textbox("showError", "退款手续费不能为空！");
            flag = false;
        }
        if(!pattern.test(backFee)){
        	$("#backFee").textbox("showError", "退款手续费为大于等于0的数！");
            flag = false;
        }

        var bankNo = $("#bankNo").textbox("getValue");
        if (bankNo == "") {
            $("#bankNo").textbox("showError", "支付渠道银行号不能为空！");
            flag = false;
        }
        return flag;
    }
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>