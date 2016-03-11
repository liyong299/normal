<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">退款订单管理</easyui:templateOverride>
<easyui:templateOverride name="head">
<style type="text/css">
        #tab{ border-collapse:collapse; border:solid 1px Black; }
      #tab td{ border:solid 1px Black; padding:5px;} 
</style>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<div id="panelShow" class="panel window messager-window" style="border-color:red;display: none; width: 350px; left: 512px; top: 152px; z-index: 9999;">
		<div class="panel-header panel-header-noborder window-header" style="width: 350px;">
			<div class="panel-title">作废凭证失败，审核无法通过！</div>
			<div class="panel-tool"><a class="panel-tool-close" href="javascript:remove()"></a></div>
		</div>
		<div class="messager-body panel-body panel-body-noborder window-body" title="" style="width: 330px;">
			<div>
				<table id="tab" ></table>
			</div>
		</div>
		<div class="panel-title"><font color="red" style="display:block; text-align:center">请前往凭证管理界面修改！</font> </div>
	</div>
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="订单编号"><easyui_ext:textBox id="orderNo" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="用户手机号"><easyui_ext:textBox id="mobile" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="订单状态"><easyui_ext:comboBox id="status"  panelMaxHeight="200"  data="<%= com.mopon.helpers.ui.combobox.Data.getOrderStatus() %>" /></easyui_ext:searchItem>
			    <br>
			    <easyui_ext:searchItem name="支付状态"><easyui_ext:comboBox id="statusPay" data="<%= com.mopon.helpers.ui.combobox.Data.getOrderStatusPay() %>" /></easyui_ext:searchItem>
			    <easyui_ext:searchItem name="出票渠道"><easyui_ext:comboBox id="channleId"   panelMaxHeight="250"  data="${channelTypeList}" /></easyui_ext:searchItem>
			    <easyui_ext:searchItem name="下单时间"><easyui:inputDate id="singleSale_query_create_date_bagin" name="createDateBagin" value=""  />到:<easyui:inputDate id="singleSale_query_create_date_end" name="createDateEnd" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="refundOrderList.do" fit="true" pageSize="30" pagination="true" singleSelect="true" rownumbers="true"  >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			    </easyui:facet>
			    <easyui:columns frozen="true">
			        <easyui:column field="orderNo"  align="center"  width="80"   title="退款编号" />
			        <easyui:column field="originalOrderNo" align="center"  width="80"   title="原订单编号"  formatter="datalist.orderDetail"/>
			        <easyui:column field="normalAmount" align="center"  width="80"   title="订单金额"  formatter="function(value, rowData, index){ if(value !='') return value+'/元'}"/>
			        <easyui:column field="normalFinalAmount" align="center"  width="80"   title=" 付款金额" formatter="datalist.normalFinalAmount"/>
			        <easyui:column field="normalRealPayAmount" align="center"  width="80"   title="实际支付金额" formatter="function(value, rowData, index){ if(value !='') return value+'/元'}"/>
			      </easyui:columns>
			     <easyui:columns >
					<easyui:column field="isMultPay" align="center"  width="80"   title="是否混合支付" formatter="datalist.isMultPay" />
					<easyui:column field="normalDisSettleAmount" align="center"  width="100"   title=" 分销商结算金额"  formatter="function(value, rowData, index){ if(value !='') return value+'/元'}"/>
					<easyui:column field="activityId" align="center"  width="80"   title="活动ID" formatter="numFormat"/>
					<easyui:column field="orderTimeShow" align="center"  width="130"   title="下单时间" />
					<easyui:column field="channelname" align="center"  width="80"   title="出票渠道" />
					<easyui:column field="userName" align="center"  width="80"   title="下单用户编号" />
					<easyui:column field="mobile" align="center"  width="100"   title=" 接收凭证的手机号" />
					<easyui:column field="channelOrderNo" align="center"  width="80"   title="渠道订单号" />
					<easyui:column field="status" align="center"  width="80"   title="状态" formatter=" datalist.orderStatus" />
					 <easyui:column field="statusPay"  align="center"  width="80" title="支付状态" formatter=" datalist.orderStatusPay"/>
					  <easyui:column field="checkStatus"  align="center"  width="80" title="审核状态" formatter=" datalist.orderCheckStatus"/>
					<easyui:column field="remark" align="center"  width="120"   title="备注" />
					 <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			         	<easyui_ext:handlerColumn text="退款审核" onclick="datalist.Enable" param="'rowData.orderNo','rowData.originalOrderNo','rowData.normalFinalAmount'" condition="rowData.checkStatus == '0' "/>
			        	<easyui_ext:handlerColumn text="退款驳回" onclick="datalist.Reject" param="'rowData.originalOrderNo ','rowData.normalFinalAmount','rowData.remark'" condition="rowData.checkStatus == '0' "/>
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
	        Edit: function(orderNo,amount,finalAmount,realPayAmount) {
	        	$("#layout_box iframe").attr("src", "cancelApply.html?orderNo="+orderNo+"&amount="+amount+"&finalAmount="+finalAmount+"&realPayAmount="+realPayAmount);
	            $('#layout_box').window({
	                title: "同意"
	            });
	        },
	        Enable: function(id,originalOrderNo,finalAmount){
	         	$.messager.confirm("系统提示", "确定该订单通过审核吗？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "../order/updateOrder.do",
	     	        		para: { orderNo: id ,originalOrderNo:originalOrderNo,finalAmount:finalAmount},
	     	        		success: function(data){
	     	        			if(data.isSuccess){
	     	        				datalist._temp.list_box.datagrid("reload");
	     		        			Tips.Success("退款审核成功，已作废该订单未使用的凭证！");
	     	        			}else if(data.flag=="1"){
	     	        				datalist._temp.list_box.datagrid("reload");
	     		        			Tips.Success("提交申请成功！");
	     		        			Cancel();	
	     		        		}else{
	     		        			 $("#tab").append("<tr><td align='center' width='120px'>凭证号</td><td align='center' width='190px'>错误信息</td></tr>");
	     		        			 var dataObj=data.rows;    
	     		        			 if(dataObj.length==0){
	     		        				Tips.Error("系统异常，请联系管理员！");
	     		        			 }else{
		     	        		       	 for(var i=0;i<dataObj.length;i++){    
		     	        		            var voucherno=dataObj[i].voucherno;    
		     	        		            var description=dataObj[i].description; 
		     	        		           $("#tab").append("<tr><td align='center' width='120px'>"+voucherno+"</td><td align='center' width='190px'>"+description+"</td></tr>");
		     	        		         }  
		     	        		        $("#panelShow").show();
	     		        			 }
	     		        		}
	     	        		}
	     	        	});
	                 }
	        	 }); 
	        },
	        Reject:function(orderNo,normalFinalAmount,remark){
	        	$("#layout_box iframe").attr("src", "rejectInit.html?orderNo="+orderNo+"&normalFinalAmount="+normalFinalAmount+"&remark="+remark);
	            $('#layout_box').window({
	                title: "驳回申请"
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
		    	if(value == 0 ||value == null){
		    		return "";
		    	}else{
		    		return "<a class='easyui-linkbutton' onclick='openDetail("+value+")'>"+value+"</a>";
		    	}
				//return "<a onclick=queryOrder("+value+")>"+value+"</a>";
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
		function remove(){
			$("#panelShow").hide();
			$("#tab").children().remove();
		}
	</script>


</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>