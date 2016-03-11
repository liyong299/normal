<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
		<easyui_ext:searchItem name="分销商名称"><easyui_ext:textBox id="distributorName"  value=""  /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="收款渠道"><easyui_ext:textBox id="paychannelname"  value=""  /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="结算时间"><easyui:inputDate id="settlmentDate_start" name="settlmentDate_start" value=""  />到:<easyui:inputDate id="settlmentDate_end" name="settlmentDate_end" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true" singleSelect="true" showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
					<easyui_ext:facetButton id="btn_export" text="导出列表"  plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="distributorName" title="分销商名称"/>
			        <easyui:column field="channelname" title="出票渠道"/>
			        <easyui:column field="paychannelname" title="收款渠道"/>
			        <easyui:column field="orderType" title="订单类型" formatter="orderTypeFormat"/>
			        <easyui:column field="ticketNum" title="数量" />
			        <easyui:column field="saleAmount" title="平台销售金额" />
			        <easyui:column field="settlmentDate" title="结算时间"/>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	function orderTypeFormat(value, rowData, index){
		if(rowData.orderType=="0"){
			return "退款订单";
		}
		if(rowData.orderType=="1"){
			return "通兑票订单";
		}
		if(rowData.orderType=="2"){
			return "选座订单";
		}else{
		   return rowData.orderType;
		}
	}
		var datalist = {
			Init: function () {
	            var _this = List;
	
	            _this._temp.list_box = $("#list-grid");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_productbox = $("#layout_productbox");
	        },
	        Search: function () {
	        	var dateStart = $("#settlmentDate_start").val();
	        	 $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){	        	
	        },
	        Export: function () {
	            $.messager.confirm("系统提示", "确定导出数据？", function (b) {
	                if (b) {
	                	distributorName=$("#distributorName").val();
	                	paychannelname=$("#paychannelname").val();
	                	settlmentDate_start=$("#settlmentDate_start").textbox("getValue");
	                	settlmentDate_end=$("#settlmentDate_end").textbox("getValue");
	                    var url = "../RptDistributorCashMonthly/exportRptDistributorCashMonthly.do?distributorName=" + distributorName + "&paychannelname=" + paychannelname+ "&settlmentDate_start=" + settlmentDate_start
	                    + "&settlmentDate_end=" + settlmentDate_end;
	        			window.open(url,'分销商收款统计表','');
	                }
	            });
	        },
	        Deleteasyui: function(){
	        }
		};
       
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>