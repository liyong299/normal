<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="分销商编号"><easyui_ext:textBox id="distributorId"  value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="分销商名称"><easyui_ext:textBox id="distributorName" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
		<easyui_ext:searchItem name="收款金额"><easyui:textBox id="realAmount_start" name="realAmount_start" value=""  />到:<easyui:textBox id="realAmount_end" name="realAmount_end" value=""  /></easyui_ext:searchItem>	    
<easyui_ext:searchItem name="对账金额"><easyui:textBox id="checkAmount_start" name="checkAmount_start" value=""  />到:<easyui:textBox id="checkAmount_end" name="checkAmount_end" value=""  /></easyui_ext:searchItem>	
		<easyui_ext:searchItem name="结算时间"><easyui:inputDate id="settlementDate_start" name="settlementDate_start" value=""  />到:<easyui:inputDate id="settlementDate_end" name="settlementDate_end" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="10" pagination="true" singleSelect="true" showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			        <easyui_ext:facetButton id="btn_export" text="导出列表"  plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="distributorId" title="分销商编号"/>
			        <easyui:column field="distributorName" title="分销商名称"/>
			        <easyui:column field="distributeModule" title="分销模式" formatter="distributeModuleFormat"/>
			        <easyui:column field="ticketNum" title="销售数量" />
			        <easyui:column field="orderAmount" title="订单金额" />
			        <easyui:column field="feePrice" title="分成单价" />
			        <easyui:column field="feeRate" title="分成比例" />
			        <easyui:column field="fee" title="分成金额" />
			        <easyui:column field="checkAmount" title="对账金额" />
			        <easyui:column field="manualAmount" title="调帐金额" />
			        <easyui:column field="realAmount" title="收款金额" />
			        <easyui:column field="settlementDate" title="结算时间"/>
			         <easyui:column field="remark" title="备注"/>
			         <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="标注" onclick="datalist.Manual" param="'rowData.id'"  condition="rowData.distributorId != '本页合计' && rowData.distributorId != '总计'"/>
			        <easyui_ext:handlerColumn text="明细" onclick="datalist.Display" param="'rowData.distributorName','rowData.settlementDate'" condition="rowData.distributorId != '本页合计' && rowData.distributorId != '总计'"/>
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 600,height: 400,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	function distributeModuleFormat(value, rowData, index){
		if(rowData.distributeModule=="1"){
			return "金额返点";
		}
		if(rowData.distributeModule=="2"){
			return "数量返点";
		}
		return rowData.distributeModule;	
	}
	
		var datalist = {
			Init: function () {
	            var _this = datalist;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null
	        },
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Manual:function(id){
	        	$("#layout_box iframe").attr("src", '../distrbutor/toEditDistrbutor.html?id=' + id);
	            $('#layout_box').window({
	                title: "标注"
	            });
	        },
	        Display: function (name, date) {
	        	var url = encodeURI(encodeURI("../orderInfo/showOrderInfo.do?distributorName=" + name + "&settlementDate=" + date));
	            parent.openTabrefresh("分销商明细报表", url, true);
	        },
	        formatter: function(value, rowData, index){
	        	var html = ""; 
	        	
	        	if(rowData.distributorName){
	        		var data = "'" + rowData.distributorName + "'" + "," + "'" + rowData.settlementDate + "'";
	        		
		        	html += "<a class=\'g-mlr-5\' onclick=\'editRptDistributorSettlementMonthly(" + rowData.id + ")\'>标注</a>";
		        	html += "<a class=\'g-mlr-5\' onclick=\"datalist.Display(" + data + ")\">明细</a>";
		        		       
		        }
	        	return html;
	        },
	        Export: function () {
	            $.messager.confirm("系统提示", "确定导出数据？", function (b) {
	                if (b) {
	                	distributorId=$("#distributorId").val();
	                	distributorName=$("#distributorName").val();
	                	realAmount_start=$("#realAmount_start").val();
	                	realAmount_end=$("#realAmount_end").val();
	                	checkAmount_start=$("#checkAmount_start").val();
	                	checkAmount_end=$("#checkAmount_end").val();
	                	settlementDate_start=$("#settlementDate_start").textbox("getValue");
	                	settlementDate_end=$("#settlementDate_end").textbox("getValue");
	                    var url = "../distrbutor/exportRptDistributorSettlementMonthly.do?distributorId=" + distributorId + "&distributorName=" + distributorName+ "&realAmount_start=" + realAmount_start
	                    + "&realAmount_end=" + realAmount_end+"&checkAmount_start="+checkAmount_start+"&checkAmount_end="+checkAmount_end+"&settlementDate_start="+settlementDate_start+"&settlementDate_end="+settlementDate_end;
	        			window.open(url,'分销商结算表','');
	                }
	            });
	        },
	        Deleteasyui: function(){
	        },
		};

		$(function(){
			datalist.Init();
		});
		
// 		function querydetail(settlementDate){
// 			var url = "../orderInfo/querydetail.do?settlementDate=" + settlementDate;
// 			//window.open(url,'分销商明细列表','');
// 			parent.openTab("分销商明细列表", url);
// 		}

	               
	</script>
	</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>