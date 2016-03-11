<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">第三方卡对账报表</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >		
			<easyui_ext:searchItem name="卡名称"><easyui_ext:comboBox  id="cardBin" data="${thirdcardinfoList}"></easyui_ext:comboBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="所属结算公司"><easyui_ext:comboBox  id="settlOrgzainationId" data="${settlOrgnList}"></easyui_ext:comboBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="开始时间"><easyui:inputDate id="begintime"></easyui:inputDate></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="结束时间"><easyui:inputDate id="endtime"></easyui:inputDate></easyui_ext:searchItem>	
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="queryThirdCardSettlList.do" fit="true" pageSize="30" pagination="true"   showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="searchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="exportBtn" text="导出列表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			   <easyui:columns>
			   		<easyui:column field="ck" checkbox="true"/>			   		
			        <easyui:column field="distributorName" width="80" title="卡名称"/>
			        <easyui:column field="orderno" width="80" title="订单号" />
			        <easyui:column field="goodsTypeValue" width="80" title="商品类型" />
			        <easyui:column field="voucherno" width="80" title="卡号" />
			        <easyui:column field="tradeno" width="80" title="交易流水号" />
			        <easyui:column field="saleAllPrice" width="80" title="消费金额" formatter="MoneyFormatYuan"/>
			        <easyui:column field="settlementAllPrice" width="80" title="结算金额" formatter="MoneyFormatYuan"/>
			        <easyui:column field="goodsid" width="80" title="商品名称" />
			        <easyui:column field="consumerSitesName" width="80" title="影院名称" />
			        <easyui:column field="validtime" width="80" title="验证时间" />
			       
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
	        Search: function () {
	        	
	            $('#datalist').datagrid('load', $("#search_box").serializeObject());
	        },	        
	        Export:function () {	        	   
	        	var cardBin =  $("#cardBin").combobox("getValue");
	        	var settlOrgzainationId =  $("#settlOrgzainationId").combobox("getValue");	        
				var begintime = $("#begintime").textbox("getValue");				
				var endtime = $("#endtime").textbox("getValue");				
				var url = "../thirdCardSettlDetails/exportThirdCardSettlDetails.html?begintime=" + begintime + "&endtime=" + endtime+ "&cardBin=" + cardBin+ "&settlOrgzainationId=" + settlOrgzainationId;
				
				window.open(url,'第三方卡对账报表',''); 
	        }
	};
	
	$(function(){
		datalist.Init();
	});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>