<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<easyui:templateOverride name="title">设置特殊价</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<div class="easyui-layout" fit="true">
		<div data-options="region:'center',split:true" style="border:0" >
			<div class="edit-box">
				<div class="item item_row"><label>价格（元）：</label><easyui_ext:textBox id="txt_common_price" value="${show_price}" /></div>
			</div>
		</div>
		<div data-options="region:'south'" style="border:0">
			<div class="edit-box">
				<div class="bottom">
		            <a class="easyui-linkbutton" onclick="operation.Save()">保存</a>
		            <a class="easyui-linkbutton" onclick="operation.Cancel()">取消</a>
		        </div>
	        </div>
		</div>
	</div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var operation = {
			Save: function(){
				var common_price = $("#txt_common_price").textbox("getValue");
				var isok = true;
				
				if(common_price == ""){
	        		$("#txt_common_price").textbox("showError", "请输入特殊价格！");
	        		
	        		if(isok){
		        		parent.Tips.Error("请输入特殊价格！");
		        	}
	        		isok = false;
	        	}
	        	else if(!/^\d+(\.\d{0,2})?$/.test(common_price)){
					$("#txt_common_price").textbox("showError", "请输入最多带两位小数的正确特殊价格！");
	        		
	        		if(isok){
		        		parent.Tips.Error("请输入最多带两位小数的正确特殊价格！");
		        	}
	        		isok = false;
	        	}
	        	else if(parseFloat(common_price) <= 0){
					$("#txt_common_price").textbox("showError", "请输入大于0的特殊价格！");
	        		
	        		if(isok){
		        		parent.Tips.Error("请输入大于0的特殊价格！");
		        	}
	        		isok = false;
	        	}
	        	else{
	        		$("#txt_common_price").textbox("closeError");
	        	}
				
				if(!isok){
					return false;
				}
				
				var data = {
						templateid: "${templateid}",
						cinemano: "${cinemano}",
						maxprice: common_price * 100
					};
				
				$.ajaxRequest({
	        		url: "setSpecialPrice.do",
	        		para: data,
	        		success: function(result){
	        			if(result.isSuccess){
	        				parent.operation.ChangeTemplateID();
	        				parent.operation._temp.list_box.datagrid("reload");
		        			parent.Tips.Success("成功");
		        			operation.Cancel();
		        		}
	        			else{
	        				parent.Tips.Error("失败");
	        			}
	        		}
	        	});
			},
			Cancel: function(){
				parent.operation._temp.layout_box.window("close");
			}
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>