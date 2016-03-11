<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">院线通收入成本配比表</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >
			<easyui_ext:searchItem name="所属结算公司"><easyui_ext:comboBox id="settlOrgzainationId" data="${settlOrgnList}"></easyui_ext:comboBox></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="开始时间"><easyui:inputDate id="begintime"></easyui:inputDate></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="结束时间"><easyui:inputDate id="endtime"></easyui:inputDate></easyui_ext:searchItem>			
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="queryRptMarginMonthly.do" fit="true" pageSize="30" pagination="true"   showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="searchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="exportBtn" text="导出列表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			   <easyui:columns>
			   		<easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="monthDate" width="80" title="月份"/>
			        <easyui:column field="settlOrgzainationName" width="80" title="客户所属公司"/>
			        <easyui:column field="customerType" width="80" title="客户类型"/>
			        <easyui:column field="orderAmount" width="80" title="订单金额" formatter="MoneyFormat"/>
			        <easyui:column field="settlementAmount" width="80" title="结算金额" formatter="MoneyFormat"/>
			        <easyui:column field="margin" width="80" title="利润" formatter="MoneyFormat"/>	
			     </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
</easyui:templateOverride>

<easyui:templateOverride name="script">
	<script>
	var datalist = {
			_temp: {	        	
	        	layout_box: null,
	        	list_box:null
	        },
			Init:function(){
				var _this = datalist;
				  _this._temp.layout_box = $("#layout_box");
				  _this._temp.list_box =  $('#datalist');
			},
	        Search: function () {
	        	
	            $('#datalist').datagrid('load', $("#search_box").serializeObject());
	        },
	        Export:function () {
	        	var  settlOrgzainationId = $("#settlOrgzainationId").combobox("getValue");	   
				var begintime = $("#begintime").textbox("getValue");				
				var endtime = $("#endtime").textbox("getValue");
				
				var url = "../rptMarginMonthly/exportRptMarginMonthly.do?settlOrgzainationId=" + settlOrgzainationId + "&begintime=" + begintime + "&endtime=" + endtime;
				
				window.open(url,'院线通收入成本配比表',''); 
	        }
	};
	
	$(function(){
		datalist.Init();
	});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>