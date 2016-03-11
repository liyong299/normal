<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
         <div  style="padding:10px 10px">
            <p>审核意见：</p>
            <p>
                <input class="easyui-textbox" type="text" id="Memo" name="Memo" data-options="multiline:true" style="width: 480px; height: 250px" />
            </p>
        </div>
        <div class="item item_row button">
 			<a class="easyui-linkbutton" onclick="Pass(${orderno},'${checktype}')">审核通过</a>
            <a class="easyui-linkbutton" onclick="Refuse(${orderno},'${checktype}')">审核拒绝</a>
            <a class="easyui-linkbutton" onclick="Cancel()">关闭</a>
        </div>
 </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	$(function(){
		 $('#Memo').textbox("setValue", "${memo}"); 
	})
	function Pass(orderno,checktype){
		var memo = $("#Memo").val();
		 var result = "";
		 var orderStatus="";
		if(checktype=='InfoCheck'){
			result="确定进行信息审核操作?";
			orderStatus="3";
		}
		else if(checktype=='FinanceCheck'){
			result="确定进行财务审核操作?";
			orderStatus="1";
		}
		 $.messager.confirm('提示', result, function (r) {
			 if (r) {
			        var data={
			        		orderno:orderno,
			        		memo:$("#Memo").val(),
			        		status:orderStatus
			        };
			        $.ajaxRequest({
			        	url:"checking.do",
			        	para:data,
			        	success:function(result){
			        	if(result.isSucceed){
			    		   	parent.datalist._temp.list_box.datagrid("reload");
			    		   	parent.datalist._temp.layout_box1.window("close");
			        		parent.Tips.Success("操作成功");
			        		Cancel();
			        	}
			        	else{
			        		parent.Tips.Error(result.message);
			        		}
			        	}
			        });
			 }
		 });
	}
	function Refuse(orderno,checktype){
			var memo = $("#Memo").val();
			 var result = "";
			 var orderStatus="";
			if(checktype=='InfoCheck'){
				result="确定进行信息审核拒绝操作?";
				orderStatus="-2";
			}
			else if(checktype=='FinanceCheck'){
				result="确定进行财务审核拒绝操作?";
				orderStatus="-3";
			}
			 $.messager.confirm('提示', result, function (r) {
				 if (r) {
				        var data={
				        		orderno:orderno,
				        		memo:$("#Memo").val(),
				        		status:orderStatus
				        };
				        $.ajaxRequest({
				        	url:"checking.do",
				        	para:data,
				        	success:function(result){
				        	if(result.isSucceed){
				    		   	parent.datalist._temp.list_box.datagrid("reload");
				    		   	parent.datalist._temp.layout_box1.window("close");
				        		parent.Tips.Success("操作成功");
				        		Cancel();
				        	}
				        	else{
				        		parent.Tips.Error(result.message);
				        		}
				        	}
				        });
				 }
			 });
	}
	function Cancel(){
		parent.datalist._temp.layout_box.window("close");
	}
	</script>
	</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp" %>