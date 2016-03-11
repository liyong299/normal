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
		 <input id="settlementAmount" name="settlementAmount" type="hidden" value="${settlementAmount}" />
	 	<div class="item"><label>卡名称：</label><easyui_ext:textBox id="cardName"  readonly="false"/></div>
        <div class="item"><label>分层金额：</label><easyui_ext:textBox id="fee" /></div>
        <div class="item"><label>对账金额：</label><easyui_ext:textBox id="checkAmount" /></div>
        <div class="item"><label>调帐：</label><easyui_ext:textBox id="manualAmount"/></div>
         <div class="item"><label>收款金额：</label><easyui_ext:textBox id="realAmount"/></div>
         <div class="item"><label>备注：</label><easyui_ext:textBox id="remark" multiline="true"  height="100px"/></div>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
       
</form>
</easyui:templateOverride>

<easyui:templateOverride name="script">
 <script>
 $(function(){
		
		$("#cardName").textbox("setValue", "${cardName}");
		$("#fee").textbox("setValue", "${fee}");
		$("#checkAmount").textbox("setValue", "${checkAmount}");
		$("#manualAmount").textbox("setValue", "${manualAmount}");
		$("#realAmount").textbox("setValue", "${realAmount}");
		$("#remark").textbox("setValue", "${remark}");
	});
 
 function Save(){
	 var id = $("#id").val();
	 var settlementAmount= $("#settlementAmount").val();
	 var realAmount = $("#realAmount").textbox("getValue");
	 var fee= $("#fee").textbox("getValue");
	 var checkAmount= $("#checkAmount").textbox("getValue");
	 var manualAmount= $("#manualAmount").textbox("getValue");
	 var remark = $("#remark").textbox("getValue");
	
	
	 var data = {
			 id : id,
			 fee : fee,
			 checkAmount : checkAmount,
			 manualAmount : manualAmount,
			 realAmount : realAmount,
			 settlementAmount : settlementAmount,
			 remark : remark
	 }
	 
	 $.ajaxRequest({
 		url: "updateThirdCardSettle.do",
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