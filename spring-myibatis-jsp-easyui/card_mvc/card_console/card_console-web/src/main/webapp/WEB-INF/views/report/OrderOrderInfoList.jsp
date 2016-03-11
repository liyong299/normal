<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="订单编号"><easyui_ext:textBox id="orderNo"  value=""></easyui_ext:textBox></easyui_ext:searchItem>	    
<easyui_ext:searchItem name="下单时间"><easyui:inputDate id="orderTime_start" name="orderTime_start" value=""  />到:<easyui:inputDate id="orderTime_end" name="orderTime_end" value=""  /></easyui_ext:searchItem>	
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="10" pagination="true" singleSelect="true" showFooter="true">
				<easyui:facet name="toolbar">
				<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
			        <easyui_ext:facetButton id="btn_export" text="导出列表"  plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="orderNo" title="订单编号"/>
			        <easyui:column field="channelname" title="分销商名称"/>
			        <easyui:column field="cinemaname" title="影院名称"/>
			        <easyui:column field="orderType" title="订单类型" formatter="orderTypeFormat"/>
			        <easyui:column field="quantity" title="数量"/>
			        <easyui:column field="amount" title="订单金额"  />
			        <easyui:column field="orderTime" title="下单时间"/>
			        <easyui:column field="status" title="付款状态" formatter="statustypeFormat"/>			      
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
		}
		if(rowData.orderNo == "本页合计"){
	        return "";
		}
		if(rowData.orderNo == "总计"){
	        return "";
		}
		return rowData.orderType;
		
	}
	
	function statustypeFormat(value, rowData, index){
    	if(rowData.status == "0"){
    		return "待支付";
    	}
    	if(rowData.status == "1"){
    		return "待出票";
    		}
    	if(rowData.status == "2"){
    		return "已出票";
    		}
       	if(rowData.status == "3"){
    		return "出票失败";
    		}
       	if(rowData.status == "4"){
    		return "待审核";
    		}
       	if(rowData.status == "5"){
    		return "已撤消";
    		}
       	if(rowData.status == "6"){
    		return "已作废";
    		}
       	if(rowData.orderNo == "本页合计"){
    		return "";
    		}
    	if(rowData.orderNo == "总计"){
    		return "";
    		}else{
    		return rowData.status;
    	}
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
	        Deleteasyui: function(){
	        },
	        Export: function () {
	            $.messager.confirm("系统提示", "确定导出数据？", function (b) {
	                if (b) {
	                	orderNo=$("#orderNo").attr("value");
	                	orderTime_start=$("#orderTime_start").textbox("getValue");
	                	orderTime_end=$("#orderTime_end").textbox("getValue");
	                    var url = "../orderInfo/exportOrderOrderInfo.do?orderNo=" + orderNo + "&orderTime_start=" + orderTime_start+ "&orderTime_end=" + orderTime_end;
	        			window.open(url,'分销商明细表','');
	                }
	            });
	        },
		};
		
	</script>
	</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>