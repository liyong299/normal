<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">撤消订单申请</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
        <div class="item"><label>订单号：</label><easyui_ext:textBox id="orderNo" value="${orderNo}" readonly="true" /></div>
        <div class="item"><label>订单金额：</label><easyui_ext:textBox id="amount" value="${amount}"  readonly="true" /></div>
        <div class="item"><label>付款金额：</label><easyui_ext:textBox id="finalAmount" value="${finalAmount}"  readonly="true"/></div>
        <div class="item"><label>实际支付金额：</label><easyui_ext:textBox id="realPayAmount" value="${realPayAmount}"  readonly="true"/></div>
        <div class="item"><label>退款金额：</label><easyui_ext:textBox id="refundAmount" /></div>
        <div class="item" style="display: none;"><easyui_ext:textBox id="isMultPay"  value="${isMultPay}"  readonly="true"/></div>
        <!-- <div class="item"><label>备注：</label><textarea rows="6" cols="16" name="remark" id="remark"></textarea></div> -->
        <div class="item item_row"><label>备注：</label><easyui_ext:textBox id="remark" height="150px" width="193px" multiline="true"/></div>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">提交申请</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		function Save(){
		 	var isok = true;
		 	
		    var orderNo = $("#orderNo").textbox("getValue");
			var amount = $("#amount").textbox("getValue");
			var finalAmount = $("#finalAmount").textbox("getValue");
			var realPayAmount = $("#realPayAmount").textbox("getValue");
			var remark = $("#remark").textbox("getValue");
		 	var refundAmount = $("#refundAmount").textbox("getValue");
			var isMultPay = $("#isMultPay").textbox("getValue");
	        if (refundAmount == "") {
	            $("#refundAmount").textbox("showError", "退款金额不能为空！");
 	            if (isok) {
	                parent.Tips.Error("退款金额不能为空！");
	            }
	            isok = false;
	            return;
	        }
	        else if(!validate()){
		            isok = false;
		            return;
	        }
	        else if (parseFloat(refundAmount) > parseFloat(finalAmount)) {
	            $("#refundAmount").textbox("showError", "退款金额不能大于付款金额！");
	            if (isok) {
	                parent.Tips.Error("退款金额不能大于付款金额！");
	            }
	            isok = false;
	            return;
	        }else if(isMultPay==1 && parseFloat(refundAmount) != parseFloat(finalAmount)){
		            if (isok) {
		                parent.Tips.Error("混合支付不准许部分撤消！");
		            }
		            isok = false;
		            return;
	        }
	        else {
	            $("#refundAmount").textbox("closeError");
	        } 
	        var data = {
	        	orderNo: orderNo,
	        	amount: amount,
	        	finalAmount: finalAmount,
	        	realPayAmount: realPayAmount,
	        	refundAmount:refundAmount,
	        	remark:remark
            }; 
           $.ajaxRequest({
        		url: "../order/cancelOrder.do",
        		para: data,
        		success: function(data){
        			if(data.isSuccess){
        				parent.datalist._temp.list_box.datagrid("reload");
	        			parent.Tips.Success("提交申请成功,已冻结该订单未使用的凭证号！");
	        			Cancel();
        			}else if(data.flag=="1"){
        				parent.datalist._temp.list_box.datagrid("reload");
	        			parent.Tips.Success("提交申请成功！");
	        			Cancel();
	        		}else{
	        			Cancel();
	        			parent.$("#tab").append("<tr><td align='center' width='120px'>凭证号</td><td align='center' width='190px'>错误信息</td></tr>");
	        			 var dataObj=data.rows;    
	        			 if(dataObj.length==0){
	        				 parent.Tips.Error("系统异常，请联系管理员！");
		        		 }else{
	        		       	 for(var i=0;i<dataObj.length;i++){    
	        		            var voucherno=dataObj[i].voucherno;    
	        		            var description=dataObj[i].description; 
	        		            parent.$("#tab").append("<tr><td align='center' width='120px'>"+voucherno+"</td><td align='center' width='190px'>"+description+"</td></tr>");
	        		         }  
	        		       	parent.$("#panelShow").show();
	        				parent.Tips.Error(result.message);
		        		}
		        	 }
        		}
        	});
		}
		
		function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}
		function validate()
		{
			var flag = true;
			var num = $("#refundAmount").textbox("getValue");
			var remark = $("#remark").textbox("getValue");

		    var reg = /^\d+(?=\.{0,1}\d+$|$)/
			if (!reg.test(num)) {
				$("#refundAmount").textbox("showError", "非法金额,请重新输入正数数字！");
				flag = false;
			}
		    if(remark == ""){
		    	$("#remark").textbox("showError", "备注不能为空！");
		    	flag = false;
		    }
			return flag;
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>