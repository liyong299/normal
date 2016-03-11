<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">第三方卡结算报表</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >		
			<easyui_ext:searchItem name="卡名称"><easyui_ext:comboBox  id="cardBin" data="${thirdcardinfoList}"></easyui_ext:comboBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="开始时间"><easyui:inputDate id="begintime" value="${begintime}"></easyui:inputDate></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="结束时间"><easyui:inputDate id="endtime" value="${endtime}"></easyui:inputDate></easyui_ext:searchItem>		
			<easyui_ext:searchItem name="收款金额"><easyui_ext:textBox id="realAmount"></easyui_ext:textBox></easyui_ext:searchItem>		
			<easyui_ext:searchItem name="对账金额"><easyui_ext:textBox id="checkAmount"></easyui_ext:textBox></easyui_ext:searchItem>	
				
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="queryThirdCardSettlMonthly.do" fit="true" pageSize="30" pagination="true"   showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="searchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="exportBtn" text="导出列表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			   <easyui:columns>
			   		<easyui:column field="ck" checkbox="true"/>			   		
			        <easyui:column field="cardName" width="80" title="卡名称"/>
			        <easyui:column field="settlOrgzainationName" width="80" title="所属结算公司" />
			        <easyui:column field="orderAmount" width="80" title="订单金额(已扣除退票款)" formatter="MoneyFormatYuan"/>
			        <easyui:column field="settlementAmount" width="80" title="影院结算金额" formatter="MoneyFormatYuan"/>
			        <easyui:column field="feeRate" width="80" title="分成比例" formatter="MoneyFormat"/>
			        <easyui:column field="fee" width="80" title="分成金额" formatter="MoneyFormat"/>
			        <easyui:column field="checkAmount" width="80" title="对账金额" formatter="MoneyFormat"/>
			        <easyui:column field="manualAmount" width="80" title="调帐" formatter="MoneyFormat"/>
			        <easyui:column field="realAmount" width="80" title="收款金额" formatter="MoneyFormat"/>
			        <easyui:column field="cardMargin" width="80" title="第三方卡毛利" formatter="MoneyFormat"/>
			        <easyui:column field="remark" width="80" title="备注" />
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="标注" onclick="datalist.Manul" param="'rowData.id'"  condition="rowData.cardName != '本页合计：' && rowData.cardName != '合计：'"/>
			        <easyui_ext:handlerColumn text="明细" onclick="datalist.Details" param="'rowData.cardBin'" condition="rowData.cardName != '本页合计：' && rowData.cardName != '合计：'"/>
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
	        Search: function () {
	        	
	            $('#datalist').datagrid('load', $("#search_box").serializeObject());
	        },
	        Manul: function (id) {
	        	datalist._temp.layout_box.find("iframe").attr("src", "updateThirdCardSettle.html?id=" + id);
	        	datalist._temp.layout_box.window({
	                title: "调帐管理"
	            });
	        },
	        Details: function (cardBin) {	        	
	        	var begintime = $("#begintime").textbox("getValue");				
				var endtime = $("#endtime").textbox("getValue");		
	        	parent. openTab("第三方预付卡对账明细", "../thirdCardSettlDetails/initDetails.html?cardbin="+cardBin+"&begintime="+begintime+"&endtime="+endtime);
	        },
	        Export:function () {	        	   
	        	var cardBin =  $("#cardBin").combobox("getValue");
	        	var realAmount =  $("#realAmount").textbox("getValue");
	        	var checkAmount =  $("#checkAmount").textbox("getValue");
				var begintime = $("#begintime").textbox("getValue");				
				var endtime = $("#endtime").textbox("getValue");				
				var url = "../rptThirdCardSettle/exportThirdCardSettlMonthly.html?begintime=" + begintime + "&endtime=" + endtime+ "&cardBin=" + cardBin;
				if(realAmount !=null && realAmount !=""){
					url =url + "&realAmount=" + realAmount;
				}
				if(checkAmount !=null && checkAmount !=""){
					url =url + "&checkAmount=" + checkAmount;
				}
				window.open(url,'线上业务收款渠道统计表',''); 
	        },
	        formatter: function(value, rowData, index){
	        	var html = "";         	
	        	if(rowData.cardName != "本页合计：" && rowData.cardName != "合计：" ){
	        		html += "<a class=\'g-mlr-5\' onclick=\'datalist.Manul(" + rowData.id + ")\'>调帐</a>";
	        		html += "<a class=\'g-mlr-5\' onclick=\'datalist.Details(" + rowData.cardBin + ")\'>查看明细</a>";
	        	}
	        	
	        		
	        	return html;
	        }
	};
	
	$(function(){
		datalist.Init();
	});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>