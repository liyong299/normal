<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">卡券订单管理</easyui:templateOverride>
<easyui:templateOverride name="head">
<style type="text/css">
        #tab{ border-collapse:collapse; border:solid 1px Black; }
      #tab td{ border:solid 1px Black; padding:5px;}
</style>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<div id="panelShow" class="panel window messager-window" style="border-color:red; display: none; width: 350px; left: 512px; top: 152px; z-index: 9999;">
		<div class="panel-header panel-header-noborder window-header" style="width: 350px;">
			<div class="panel-title"> <font color="red">冻结凭证失败，无法提交退款申请！</font></div>
			<div class="panel-tool"><a class="panel-tool-close" href="javascript:remove();"></a></div>
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
				<easyui_ext:searchItem name="订单号"><easyui_ext:textBox id="orderno" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="商品名称"><easyui_ext:textBox id="goodsname" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="客户名称"><easyui_ext:textBox id="customername" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="status" panelMaxHeight="200" data="<%= com.mopon.helpers.ui.combobox.Data.getCardOrderStatus() %>" /></easyui_ext:searchItem>
				<%-- <easyui_ext:searchItem name="规格"><easyui_ext:comboBox id="changeType" panelMaxHeight="200" data="<%= com.mopon.helpers.ui.combobox.Data.getCardOrderChangeType() %>" /></easyui_ext:searchItem> --%>
				<easyui_ext:searchItem name="产品类型"><easyui_ext:comboBox id="producttypestr" panelMaxHeight="200" data="<%= com.mopon.helpers.ui.combobox.Data.getCardOrderProductType() %>" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="模板"><easyui_ext:comboBox id="templateid" panelMaxHeight="200"  /></easyui_ext:searchItem>
 			    <easyui_ext:searchItem name="同步时间"><easyui:inputDate id="createdatebagin" name="createdatebagin" value=""  />到:<easyui:inputDate id="createdateend" name="createdateend" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist"   rownumbers="true"  url="cardOrderList.do" fit="true" pageSize="30" pagination="true" showFooter="false"  striped="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
					<easyui_ext:facetButton id="btn_export" text="导出列表" plain="true" iconCls="icon-page_excel" onclick="datalist.Export()" />
			    </easyui:facet>
			    <easyui:columns frozen="true">
			        <easyui:column field="orderno" title="订单号" align="center"  width="80"  formatter="datalist.Voucher"  />
			        <easyui:column field="goodsname" title="商品名称" align="left"  width="80" />
			        <easyui:column field="producttype" title="产品类型" align="center"  width="80" formatter="datalist.productType"/>
<%-- 			        <easyui:column field="showtypestr" title="规格" align="center"  width="80" /> --%>
			        <easyui:column field="status"  title="状态"  align="center"  width="80" formatter=" datalist.orderStatus"/>
			     
			      </easyui:columns>
			     <easyui:columns >
			        <easyui:column field="amount" title="数量" align="right"  width="80"  />
			        <easyui:column field="singleamount" title=" 单张面额/点/张"   align="right"  width="100"  formatter="datalist.singleAmount"/>
			         <easyui:column field="singlerealamount" title=" 单张实收金额(元)"   align="right"  width="100" />
			         <easyui:column field="customername" title="客户名称" align="center"  width="80"  />
			        <easyui:column field="salesname" title="业务员名称" align="center"  width="80"  />
			        <easyui:column field="providername" title="销售机构" align="center"  width="80"  />
			        <easyui:column field="publname" title="发卡机构" align="center"  width="80"  />
			         <easyui:column field="salediscount" title="销售折扣" align="right"  width="80" />
			         <easyui:column field="startdate"  title="有效期起"  align="center"  width="130" formatter="datalist.dateChangeYMD" />
			         <easyui:column field="enddate"  title="有效期止"  align="center"  width="130"  formatter="datalist.dateChangeYMD"  />
			          <easyui:column field="templatename"  title="模板名称"  align="center"  width="130"  formatter="datalist.Detail"   />
			          <easyui:column field="createddate"  title="同步时间"  align="center"  width="130"   formatter="datalist.dateChangeYMDHMS"  />
			          <easyui:column field="checkdate"  title="审核时间" align="center"  width="130"   formatter="datalist.dateChangeYMDHMS"  />
			          <easyui:column field="checker" align="center"  width="80"   title="审核人" />
			         <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			         	<easyui_ext:handlerColumn text="设置规则" onclick="datalist.Bind" param="rowData.orderno,'rowData.goodsname','rowData.producttype'" condition="rowData.status != '2'  && rowData.status != '3'   "/>
			         	<easyui_ext:handlerColumn text="信息审核" onclick="datalist.InfoCheck" param="rowData.orderno" condition="rowData.status == '2' "/>
			        	<easyui_ext:handlerColumn text="财务审核" onclick="datalist.FinanceCheck" param="rowData.orderno" condition="rowData.status == '3' "/>
			        	<%-- <easyui_ext:handlerColumn text="复制" onclick="datalist.Supplement" param="rowData.orderNo,rowData.mobile" condition="rowData.status == '1'  "/> --%>
			        	<%-- <easyui_ext:handlerColumn text="下架" onclick="datalist.Withdrawn" param="rowData.orderno" condition="rowData.status == '1' "/> --%>
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box1" style="overflow:hidden" data-options="closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false,maximized:true"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	<div id="layout_box" style="overflow:hidden" data-options="width: 550,height: 430,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = datalist;
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_box1 = $("#layout_box1");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null,
	        	layout_box1: null
	        },
	        Voucher:function(value,rowData){ 
				var html="<a class=\'g-mlr-5\' onclick=\'datalist.OrderVoucher(\"" + rowData.orderno + "\")\'>"+value+"</a>";
				return html;
			},
	        OrderVoucher: function(orderno) {
	        	$("#layout_box iframe").attr("src", "orderVoucher.html?orderno=" + orderno);
	            $('#layout_box').window({
	                title: "凭证列表"
	            });
	        },
			Detail:function(value,rowData){ 
				var html="<a class=\'g-mlr-5\' onclick=\'datalist.OrderDetail(\"" + rowData.orderno + "\")\'>"+value+"</a>";
				return html;
			},
			OrderDetail: function(orderNo) {
	        	$("#layout_box1 iframe").attr("src", "detail.html?orderno=" + orderNo);
	            $('#layout_box1').window({
	                title: "订单详情"
	            });
	        },
	        Bind: function(orderNo,goodsName,productType) {
	        	$("#layout_box1 iframe").attr("src", "bind.html?orderno=" + orderNo);
	            $('#layout_box1').window({
	                title: "设置规则"
	            });
	        },
	        InfoCheck:function(orderNo){//信息审核
	        	$("#layout_box iframe").attr("src", "check.html?orderno="+orderNo+"&checktype=InfoCheck");
	            $('#layout_box').window({
	                title: "信息审核"
	            });
	        },
	        FinanceCheck:function(orderNo){//财务审核
	        	$("#layout_box iframe").attr("src", "check.html?orderno="+orderNo+"&checktype=FinanceCheck");
	            $('#layout_box').window({
	                title: "财务审核"
	            });
	        },

	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        InitTemplateCombobox:function(){
	        $('#templateid').combobox({
	            url: 'getListTemplate.do', 
	           valueField: 'templateid',
	           textField: 'templatename'
	       });
	        },
	        Withdrawn: function(id){
	         	$.messager.confirm("系统提示", "确定下架此订单？", function (b) {
	                 if (b) {
	                	 $.ajaxRequest({
	     	        		url: "withdrawn.do",
	     	        		para: { 
	     	        			orderno: id,
	     	        			status:-1
	     	        			},
	     	        		success: function(result){
	     	        			if(result.isSucceed){
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
		        		return '已关联';
		        	}else if(value == '2'){
		        		return '待信息审核';
		        	}else if(value == '3'){
		        		return '待财务审核';
		        	}else if(value == '-1'){
		        		return '未关联';
		        	}else if(value == '-2'){
		        		return '信息审核拒绝';
		        	}else if(value == '-3'){
		        		return '财务审核拒绝';
		        	}
			},
				productType:function(value){
					if(value=='0'){
						return '第三方预付卡';
		        	}else if(value == '1'){
		        		return '现金卡';
		        	}else if(value == '2' ){
		        		return '点卡';
		        	}else if(value == '3'){
		        		return '电影兑换卡';
		        	}else if(value == '4'){
		        		return '通兑票';
		        	}else if(value == '5'){
		        		return '现金券';
		        	}else if(value == '6'){
		        		return '兑换券';
		        	}else if(value == '7'){
		        		return '点券';
		        	}
			},
			dateChangeYMD:function(value){
				if(value!=null){
					return new Date(value.time).Format("yyyy-MM-dd");
				}
			},
			dateChangeYMDHMS:function(value){
				if(value!=null){
					return new Date(value.time).Format("yyyy-MM-dd hh:mm:ss");
				}
			},
			singleAmount:function(value, row) {
			  if (row.ProductTypeName == "2" || row.ProductTypeName == "7") {
	                return value + " 点";
	            }
	            else if (row.producttype == "3" || row.ProductTypeName == "4" || row.ProductTypeName == "6") {
	                return value + " 张";
	            }
	            else {
	                return value + " 元";
	            }
	            return value;
		},

			Export:function(){
				$.messager.confirm("系统提示", "确定导出数据？", function (b) {
	                if (b) {
	                	orderNo=$("#orderno").textbox("getValue");
	                	goodsName=encodeURIComponent(encodeURIComponent($("#goodsname").textbox("getValue")));
	                	customerName=encodeURIComponent(encodeURIComponent($("#customername").textbox("getValue")));
	                	status = $("#status").combobox("getValue");
	                	producttypeStr= $("#producttypestr").combobox("getValue");
	                	templateId= $("#templateid").combobox("getValue");
	                	createdateBagin=$("#createdatebagin").datebox("getValue");
	                	createdateEnd=$("#createdateend").datebox("getValue");
	                    var url = "exportCardOrderList.html?orderno=" + orderNo + "&goodsname=" + goodsName+ "&customername=" + customerName
	                    + "&status=" + status+ "&producttypestr=" + producttypeStr+ "&templateid=" + templateId+ "&createdatebagin=" + createdateBagin
	                    + "&createdateend=" + createdateEnd;
	        			window.open(url,'导出列表','');
	                }
	            });
			},
		};
		$(function(){
			datalist.Init();
			datalist. InitTemplateCombobox();
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
		function remove(){
			$("#panelShow").hide();
			$("#tab").children().remove();
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>