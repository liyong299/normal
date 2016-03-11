<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">影院验证记录表</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="valid_query_main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >
		    <easyui_ext:searchItem name="影院编码"><easyui_ext:textBox id="consumerSitesNo"  name="consumerSitesNo" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="凭证号"><easyui_ext:textBox id="voucherno"  name="voucherno" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="订单号"><easyui_ext:textBox id="orderno" name="orderno" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="交易流水号"><easyui_ext:textBox id="tradeno"  name="tradeno" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="验证开始时间"><easyui:inputDate id="begintime"></easyui:inputDate></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="验证结束时间"><easyui:inputDate id="endtime"></easyui:inputDate></easyui_ext:searchItem>	
					
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="view.do" fit="true" pageSize="30" pagination="true"  singleSelect="true"  showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="earchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="exportBtn" text="导出列表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			   <easyui:columns frozen="true">
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="voucherno" width="80" title="凭证号"/>
			        <easyui:column field="orderno" width="80" title="订单号"/>
			    </easyui:columns>
			    <easyui:columns>
			        <easyui:column field="consumerSitesNo" width="80" title="影院编码" formatter="numFormat"/>
			        <easyui:column field="consumerSitesName" width="80" title="影院名称"/>
			        <easyui:column field="validtype" width="80" title="验证类型" formatter="validtypeFormat"/>
			        <easyui:column field="validtime" width="80" title="验证时间"/>
			        <easyui:column field="settlementprice" width="80" title="结算单价" formatter="MoneyFormatYuan"/>
			         <easyui:column field="settlementAllPrice" width="80" title="结算总价" formatter="MoneyFormatYuan"/>
			          <easyui:column field="vouchertypename" width="80" title="凭证类型"/>
			        <easyui:column field="tradeno" width="80" title="交易流水号"/>
			        <easyui:column field="srctradeno" width="80" title="原交易流水号"/>
			         <easyui:column field="settlOrgzainationName" width="80" title="结算机构"/>
			         <easyui:column field="deviceid" width="80" title="终端编号"/>
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
	function validtypeFormat(value, rowData, index){
    	if(rowData.validtype == "1"){
    		return "消费";
    	}
    	if(rowData.validtype == "-1"){
    		return "冲正";
    	}else{
    		return rowData.validtype;
    	}
    }	
	
	function numFormat(value){
    	if(value == 0 ||value == null){
    		return "";
    	}else{
    		return value;
    	}
    }
	
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
	        Export: function () {
	            $.messager.confirm("系统提示", "确定导出数据？", function (b) {
	                if (b) {
	                	consumerSitesNo=$("#consumerSitesNo").val();
	                	voucherno=$("#voucherno").val();
	                	orderno=$("#orderno").val();
	                	tradeno=$("#tradeno").val();
	                	begintime=$("#begintime").datebox("getValue");
	                	endtime=$("#endtime").datebox("getValue");
	                    var url = "../rptSettlVouchervalidbase/exportRptSettlVouchervalidbase.do?consumerSitesNo=" + consumerSitesNo + "&voucherno=" + voucherno+ "&orderno=" + orderno
	                    + "&tradeno=" + tradeno+ "&begintime=" + begintime+ "&endtime=" + endtime;
	        			window.open(url,'影院验证记录表','');
	                }
	            });
	        },
            PrintFlag: function (value, rowData, rowIndex) {//设置颜色
                if (rowData.voucherno) {
                    if (value == 1) {
                        return "<div class='icon-ok' style='width:20px;height:20px;margin:0 auto;'></div>";
                    }
                    else {
                        return "<div class='icon-cross' style='width:20px;height:20px;margin:0 auto;'></div>";
                    }
                }
            }
	};
	
	$(function(){
		datalist.Init();
	});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>