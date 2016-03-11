<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>


<easyui:templateOverride name="title">院线通残值报表</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >
			<easyui_ext:searchItem name="销售渠道"><easyui_ext:comboBox id="distributorId" data="${channelList}"></easyui_ext:comboBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="订单号"><easyui_ext:textBox id="orderno" name="orderno" value=""></easyui_ext:textBox></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="开始时间"><easyui:inputDate id="begintime"></easyui:inputDate></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="结束时间"><easyui:inputDate id="endtime"></easyui:inputDate></easyui_ext:searchItem>			
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="queryRptOrderScrapDaily.do" fit="true" pageSize="30" pagination="true"   showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="searchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="exportBtn" text="导出列表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			   <easyui:columns>
			   		<easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="orderno" width="80" title="订单号"/>
			        <easyui:column field="distirbutorname" width="80" title="销售渠道"/>
			        <easyui:column field="orderTime" width="80" title="订单到期日"/>
			        <easyui:column field="orderPrice" width="80" title="订单单价"/>
			        <easyui:column field="orderNum" width="80" title="订单数量" />			   
			        <easyui:column field="useNum" width="80" title="已消费数量"/>
			        <easyui:column field="expireNum" width="80" title="过期未使用数量"/>
			        <easyui:column field="expireAmount" width="80" title="过期未使用金额"/>
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
	        	var  distributorId = $("#distributorId").combobox("getValue");	        	
				var orderno = $("#orderno").textbox("getValue");				
				var begintime = $("#begintime").textbox("getValue");				
				var endtime = $("#endtime").textbox("getValue");
				
				var url = "../rptOrderScrapDaily/exportRptOrderScrapDaily.do?distributorId=" + distributorId + "&orderno=" + orderno + "&begintime=" + begintime + "&endtime=" + endtime;
				
				window.open(url,'院线通残值报表',''); 
	        }
	};
	
	$(function(){
		datalist.Init();
	});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>