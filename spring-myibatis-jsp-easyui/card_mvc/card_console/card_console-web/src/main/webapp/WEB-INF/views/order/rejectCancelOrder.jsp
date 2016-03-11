<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">驳回撤消</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
        <div class="item"><label>订单号：</label><easyui_ext:textBox id="orderNo" value="${orderNo}" readonly="true" /></div>
      <%--   <div class="item"><label>订单金额：</label><easyui_ext:textBox id="amount" value="${amount}"  readonly="true" /></div>
        <div class="item"><label>付款金额：</label><easyui_ext:textBox id="finalAmount" value="${finalAmount}"  readonly="true"/></div>
        <div class="item"><label>实际支付金额：</label><easyui_ext:textBox id="realPayAmount" value="${realPayAmount}"  readonly="true"/></div> --%>
        <div class="item"><label>退款金额：</label><easyui_ext:textBox id="finalAmount" value="${finalAmount}" readonly="true"/></div>
        <div class="item item_row"><label>撤消原因：</label><easyui_ext:textBox id="remark" height="150px" width="193px" multiline="true" value="${remark}" readonly="true"/></div>
         <div class="item item_row"><label>驳回原因：</label><easyui_ext:textBox id="reject" height="150px" width="193px" multiline="true" required="true"/></div>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">驳回</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		function Save(){
			if(!validate()){
				return;
			}
		 	var isok = true;
		 	var remark=$("#remark").textbox("getValue");
		 	var orderNo = $("#orderNo").textbox("getValue");
		    var reject = $("#reject").textbox("getValue");
	        var data = {
	        		remark:remark,
	        	orderNo:orderNo,
	        	reject:reject
            }; 
           $.ajaxRequest({
        		url: "../order/rejectCancelOrder.do",
        		para: data,
        		success: function(data){
        			if(data.flag=='1'){
        				Cancel();
        				parent.datalist._temp.list_box.datagrid("reload");
	        			parent.Tips.Success("驳回退款申请成功，自动解冻单家通兑票已经冻结的凭证号！！");
	        		}else if(data.flag=='2'){
	        			Cancel();
	        			parent.datalist._temp.list_box.datagrid("reload");
	        			parent.Tips.Success("驳回退款申请成功，不支持自动解冻多家通兑票凭证号，需手动处理！");
	        		}else if(data.flag=='3'){
	        			Cancel();
	        			parent.datalist._temp.list_box.datagrid("reload");
	        			parent.Tips.Success("驳回退款申请成功！");
		        	 }else{
		        		 Cancel();
		        		parent.Tips.Error("系统异常，请联系管理员！");
		        	 }
        		}
        	});
		}
		
		function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}
		function validate()
		{
			var reject = $("#reject").textbox("getValue");
			if(reject == ""){
				$("#reject").textbox("showError", "驳回原因不能为空！");
				return false;
			}
			return true;
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>