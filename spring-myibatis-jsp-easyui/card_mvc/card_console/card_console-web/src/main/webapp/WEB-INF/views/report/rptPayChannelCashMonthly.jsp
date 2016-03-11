<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">线上业务收款渠道统计表</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >				
			<easyui_ext:searchItem name="开始时间"><easyui:inputDate id="begintime"></easyui:inputDate></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="结束时间"><easyui:inputDate id="endtime"></easyui:inputDate></easyui_ext:searchItem>			
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="queryRptPayChannelCash.do" fit="true" pageSize="30" pagination="true"   showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="searchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="exportBtn" text="导出列表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			   <easyui:columns>
			   		<easyui:column field="ck" checkbox="true"/>			   		
			        <easyui:column field="payChannelName" width="80" title="收款渠道"/>
			        <easyui:column field="saleAmount" width="80" title="平台销售金额" formatter="MoneyFormat"/>
			        <easyui:column field="bankAmount" width="80" title="银行收款金额" formatter="MoneyFormat"/>
			        <easyui:column field="manualAmount" width="80" title="调帐" formatter="MoneyFormat"/>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="标注" onclick="datalist.Manul" param="rowData.id" condition="rowData.payChannelName != '本页合计：' && rowData.payChannelName != '合计：'"/>
			        </easyui_ext:formatterColumn>
			     </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
	<div id="layout_box" style="overflow:hidden" data-options="width: 400,height: 460,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
		<iframe frameborder="0" width="100%" height="100%"></iframe>
	</div>
	
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
	        Search: function() {
	        	
	            $('#datalist').datagrid('load', $("#search_box").serializeObject());
	        },
	        Manul: function(id) {
	        	datalist._temp.layout_box.find("iframe").attr("src", "updateRptPayChannelCashMonthly.html?id=" + id);
	        	datalist._temp.layout_box.window({
	                title: "调帐管理"
	            });
	        },
	        Export:function() {	        	   
				var begintime = $("#begintime").textbox("getValue");				
				var endtime = $("#endtime").textbox("getValue");				
				var url = "../rptPayChannleCash/exportRptPayChannelCashMonthly.do?begintime=" + begintime + "&endtime=" + endtime;				
				window.open(url,'线上业务收款渠道统计表',''); 
	        }
	};
	
	$(function(){
		datalist.Init();
	});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>