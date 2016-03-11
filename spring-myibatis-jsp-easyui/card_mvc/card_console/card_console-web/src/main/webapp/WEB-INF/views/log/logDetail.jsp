<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">查看详情</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
		<legend>错误信息</legend>
		<span>
			<textarea id="expStackTrace" class="textbox-text validatebox-text" placeholder="" style="margin-left: 0px; margin-right: 0px; height:90px; width: 540px;">
						${expMsg}
			</textarea>
		</span>
	</fieldset>
	<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
		<legend>错误来源</legend>
		<span>
			<textarea id="expStackTrace" class="textbox-text validatebox-text" placeholder="" style="margin-left: 0px; margin-right: 0px; height: 430px; width: 540px;">
						${expStackTrace}
			</textarea>
		</span>
	</fieldset>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = datalist	;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_box1 = $("#layout_box1");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null,
	        	layout_box1: null
	        },
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	       /*  Delete: function(id){
	        	 $.messager.confirm("系统提示", "确定删除？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "delete.do",
	     	        		para: { id: id },
	     	        		success: function(result){
	     	        			if(result.isSuccess){
	     	        				datalist._temp.list_box.datagrid("reload");
	     		        			Tips.Success("成功");
	     		        		}
	     	        		}
	     	        	});
	                 }
	        	 });
	        }, */
	        Edit: function(orderNo,amount,finalAmount,realPayAmount,isMultPay) {
	        	$("#layout_box iframe").attr("src", "cancelApply.html?orderNo="+orderNo+"&amount="+amount+"&finalAmount="+finalAmount+"&realPayAmount="+realPayAmount+"&isMultPay="+isMultPay);
	            $('#layout_box').window({
	                title: "申请撤消订单"
	            });
	        },
	        Enable: function(id){
	         	$.messager.confirm("系统提示", "确定该撤消订单？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "cancelOrder.do",
	     	        		para: { orderNo: id },
	     	        		success: function(result){
	     	        			if(result.isSuccess){
	     	        				datalist._temp.list_box.datagrid("reload");
	     		        			Tips.Success("成功");
	     		        		}
	     	        		}
	     	        	});
	                 }
	        	 }); 
	        },
	        formatter: function(value, rowData, index){
	        	var html = ""; 
	        	if((rowData.status == '2' ||rowData.status == '3') && rowData.orderType=='1' ){
	        		 html += "<a class=\'g-mlr-5\' onclick=\'datalist.Edit(" +rowData.orderNo + ","+rowData.normalAmount+","+rowData.normalFinalAmount+","+rowData.normalRealPayAmount+","+rowData.isMultPay+")\'>申请撤消</a>"; 
	        		/* html += "<a class=\'g-mlr-5\' onclick=\'datalist.Enable(" +rowData.orderNo+")\'>申请撤消</a>"; */
	        	}
	        	return html;
	        },
	        orderStatus:function(value, rowData, index){
		        	if(value == '0'){
		        		return '待付款';
		        	}else if(value == '1'){
		        		return '待出票';
		        	}else if(value == '2'){
		        		return '已出票';
		        	}else if(value == '3'){
		        		return '出票失败';
		        	}else if(value == '4'){
		        		return '退款待审核';
		        	}else if(value == '5'){
		        		return '已撤消';
		        	}else if(value == '6'){
		        		return '已关闭';
		        	}
			},
			orderType:function(value){
				if(value == '0'){
	        		return '退款';
	        	}else if(value == '1'){
	        		return '通兑票';
	        	}else if(value == '2'){
	        		return '选座票';
	        	}
			},
	        isMultPay:function(value, rowData, index){
	        	if(value == '0'){
	        		return '正常';
	        	}else if(value == '1'){
	        		return '异常';
	        	}
	        },
			orderDetail:function(value,rowData){ 
				//return "<a onclick=queryOrder("+value+")>"+value+"</a>";
				return "<a href=<%=request.getContextPath()%>/order/orderDetail.do?id="+value+" style=text-decoration:none;>"+value+"</a>";
			}
		};
		$(function(){
			datalist.Init();
		});
		
		function queryOrder(id){
			 $.ajax({
                 type: 'post',
                 cache: false,
                 dataType: 'json',
                 url: "../order/orderDetail.do",
                 data: { id: id },
                 async: true,
                 success: function (result) {
                     if (result.isSucceed) {
                         $("#layout_areabox").datagrid("reload");
                     }
                     else {
                         alert(result.message);
                     }
                 },
                 error: function () {
                 },
                 complete: function () {
                 }
             });
		}
		function numFormat(value){
	    	if(value == 0 ||value == null){
	    		return "";
	    	}else{
	    		return value;
	    	}
	    }
		function remove(){
			$("#panelShow").hide();
			$("#tab").children().remove();
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>