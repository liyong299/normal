<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">重发凭证</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
<form id="info" class="edit_box"  method="post" action="">
	<div class="item"><label>订单号：</label><easyui_ext:textBox id="orderNo" name="orderNo"  value="${orderNo}" /></div>
	<div class="item"><label>手机号：</label><easyui_ext:textBox id="sendMobile" name="sendMobile"  value="${mobile}"/></div>
	<div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Send()">发送</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
</form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		function Send(){
			var orderNo = $("#orderNo").textbox("getValue");
			var sendMobile = $("#sendMobile").textbox("getValue");
			var data={orderNo:orderNo,sendMobile:sendMobile};
			
			$.ajaxRequest({
        		url: "reissue.do",
        		para: data,
        		success: function(result){
        			if(result.isSuccess){
        				parent.datalist._temp.list_box.datagrid("reload");
	        			parent.Tips.Success(result.description);
	        			Cancel();
	        		}
        			else{
        				parent.Tips.Error(result.description);
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