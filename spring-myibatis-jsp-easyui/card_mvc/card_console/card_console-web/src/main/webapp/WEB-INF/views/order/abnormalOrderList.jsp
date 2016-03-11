<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">异常订单管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="订单编号"><easyui_ext:textBox id="orderNo" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="用户手机号"><easyui_ext:textBox id="mobile" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="订单类型"><easyui_ext:comboBox id="orderType" data="<%= com.mopon.helpers.ui.combobox.Data.getOrderType() %>" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="订单状态"><easyui_ext:comboBox id="status" panelMaxHeight="200"  data="<%= com.mopon.helpers.ui.combobox.Data.getOrderStatus() %>" /></easyui_ext:searchItem>
			   <easyui_ext:searchItem name="支付状态"><easyui_ext:comboBox id="statusPay" data="<%= com.mopon.helpers.ui.combobox.Data.getOrderStatusPay() %>" /></easyui_ext:searchItem>
			    <easyui_ext:searchItem name="出票渠道"><easyui_ext:comboBox id="channleId"   panelMaxHeight="250"  data="${channelTypeList}" /></easyui_ext:searchItem>
			    <easyui_ext:searchItem name="下单时间"><easyui:inputDate id="singleSale_query_create_date_bagin" name="createDateBagin" value=""  />到:<easyui:inputDate id="singleSale_query_create_date_end" name="createDateEnd" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="abnormalOrderList.do" fit="true" pageSize="30" pagination="true" singleSelect="true"  rownumbers="true" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			    </easyui:facet>
			    <easyui:columns frozen="true">
					<easyui:column field="orderNo" align="center"  width="80"   title="订单号"	formatter="datalist.orderDetail" />
					<easyui:column field="orderType" align="center"  width="80"   title="订单类型"	formatter="datalist.orderType" />
					<easyui:column field="originalOrderNo" align="center"  width="80"   title="原订单编号" formatter="numFormat" />
					<easyui:column field="normalAmount" align="center"  width="80"   title="订单金额"  formatter="function(value, rowData, index){ if(value !='') return value+'/元'}"/>
					<easyui:column field="normalFinalAmount" align="center"  width="80"   title=" 付款金额" formatter="datalist.normalFinalAmount"/>
					<easyui:column field="normalRealPayAmount" align="center"  width="80"   title="实际支付金额"  formatter="function(value, rowData, index){ if(value !='') return value+'/元'}"/>
				</easyui:columns>
			     <easyui:columns >
					<easyui:column field="isMultPay" align="center"  width="80"   title="是否混合支付" formatter="datalist.isMultPay" />
					<easyui:column field="normalDisSettleAmount" align="center"  width="100"   title=" 分销商结算金额"  formatter="function(value, rowData, index){ if(value !='') return value+'/元'}"/>
					<easyui:column field="activityId" align="center"  width="80"   title="活动ID" formatter="numFormat" />
					<easyui:column field="orderTimeShow" align="center"  width="130"   title="下单时间" />
					<easyui:column field="channelname" align="center"  width="80"   title="出票渠道" />
					<easyui:column field="userName" align="center"  width="80"   title="下单用户编号" />
					<easyui:column field="mobile" align="center"  width="100"   title=" 接收凭证的手机号" />
					<easyui:column field="channelOrderNo" align="center"  width="100"   title="渠道订单号" />
					<easyui:column field="status" align="center"  width="80"   title="状态"	formatter=" datalist.orderStatus" />
					 <easyui:column field="statusPay"  align="center"  width="80" title="支付状态" formatter=" datalist.orderStatusPay"/>
					  <easyui:column field="checkStatus"  align="center"  width="80" title="审核状态" formatter=" datalist.orderCheckStatus"/>
					    <easyui:column field="checker" align="center"  width="80"   title="审核人" />
			          <easyui:column field="checkTimeShow" align="center"  width="130"   title="审核时间" />
			          <easyui:column field="remark" align="center"  width="120"   title="备注" />
					<easyui_ext:formatterColumn title="操作" param="value, rowData, index">
						<easyui_ext:handlerColumn text="重发凭证" onclick="datalist.Resend" param="rowData.orderNo,'rowData.mobile','rowData.channleId'" condition="rowData.statusPay == '2' && rowData.status == '4' "/>
			        	<easyui_ext:handlerColumn text="补发凭证" onclick="datalist.Supplement" param="rowData.orderNo,rowData.mobile" condition="rowData.statusPay == '2' && rowData.status=='7'"/>
			        	<easyui_ext:handlerColumn text="撤销" onclick="datalist.Edit" param="rowData.orderNo,rowData.normalAmount,rowData.normalFinalAmount,rowData.normalRealPayAmount,rowData.isMultPay" condition="rowData.statusPay == '2'  && rowData.orderType=='1' &&( rowData.checkStatus=='-1' || rowData.checkStatus=='')"/>
			        </easyui_ext:formatterColumn>
				</easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box1" style="overflow:hidden" data-options="width: 800,height: 600,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	<div id="layout_box" style="overflow:hidden" data-options="width: 400,height: 450,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
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
	        Delete: function(id){
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
	        },
	        Edit: function(orderNo,amount,finalAmount,realPayAmount) {
	        	
	        	$("#layout_box iframe").attr("src", "orderCancelApply.html?orderNo="+orderNo+"&amount="+amount+"&finalAmount="+finalAmount+"&realPayAmount="+realPayAmount);
	            $('#layout_box').window({
	                title: "撤消申请"
	            });
	        },
	        Resend:function(orderNo,mobile,channleId){//重发
	        	$("#layout_box iframe").attr("src", "resendVoucher.html?orderNo="+orderNo+"&mobile="+mobile+"&channleId="+channleId);
	            $('#layout_box').window({
	                title: "重发凭证"
	            });
	        },
 			Supplement:function(orderNo,mobile){//补发凭证
 				$("#layout_box iframe").attr("src", "reissueVoucher.html?orderNo="+orderNo+"&mobile="+mobile);
	            $('#layout_box').window({
	                title: "补发凭证"
	            });
	        },
	        Enable: function(id){
	         	$.messager.confirm("系统提示", "确定该撤消订单？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "updateOrder.do",
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
	        orderStatus:function(value, rowData, index){
	        	if(value == '1'){
	        		return '创建订单';
	        	}else if(value == '2'){
	        		return '锁座成功';
	        	}else if(value == '3'){
	        		return '地面出票成功';
	        	}else if(value == '4'){
	        		return '凭证成功';
	        	}else if(value == '5'){
	        		return '锁座失败';
	        	}else if(value == '6'){
	        		return '地面出票失败';
	        	}else if(value == '7'){
	        		return '凭证失败';
		        }else if(value == '8'){
	        		return '已撤消';
	        	}else if(value == '9'){
	        		return '出票中';
	        	}
			},
			orderStatusPay:function(value){
				if(value == '1'){
	        		return '未支付';
	        	}else if(value == '2'){
	        		return '支付成功';
	        	}else if(value == '3'){
	        		return '部分支付';
	        	}
			},
			orderCheckStatus:function(value){
				if(value == '0'){
	        		return '待退款审核';
	        	}else if(value == '1'){
	        		return '退款已审核';
	        	}else if(value == '-1'){
	        		return '退款审核拒绝';
	        	}
			},
			orderType:function(value){
				if(value == '0'){
	        		return '退款';
	        	}else if(value == '1'){
	        		return '通兑票';
	        	}else if(value == '2'){
	        		return '选座';
	        	}
			},
	        isMultPay:function(value, rowData, index){
	        	if(value == '0'){
	        		return '否';
	        	}else if(value == '1'){
	        		return '是';
	        	}
	        },
			orderDetail:function(value,rowData){ 
				return "<a class='easyui-linkbutton' onclick='openDetail("+value+")'>"+value+"</a>";
			},
			normalFinalAmount:function(value, rowData, index){
				if(value != ''){
					if(rowData.productType=='2' || rowData.productType=='7'){
						return value+"/点";
					}else if(rowData.productType=='3'){
						return value+"/次";
					}else if(rowData.productType=='6'){
						return value+"/张";
					}else{						
							return value+"/元";
					}
				}
			}
		};
		$(function(){
			datalist.Init();
		});
		
		function openDetail(id){
			$("#layout_box1 iframe").attr("src", "orderDetail.html?id="+id);
            $('#layout_box1').window({
                title: "查看订单详情",
                maximized:true
            });
		}
		
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
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>