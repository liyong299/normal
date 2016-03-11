<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>


<easyui:templateOverride name="title">凭证管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="voucher_query_main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >
			<easyui_ext:searchItem name="凭证号"><easyui_ext:textBox id="voucherNo"  name="voucherNo" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="订单号"><easyui_ext:textBox id="orderNo" name="orderNo" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<%-- <easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="usestatus" name="usestatus" width="<%= com.mopon.helpers.ui.combobox.Width.SHORT %>" data="${statusList }" /></easyui_ext:searchItem> --%>
			<easyui_ext:searchItem name="有效期始"><easyui:inputDate id="startTime"></easyui:inputDate></easyui_ext:searchItem>
			<easyui_ext:searchItem name="有效期止"><easyui:inputDate id="endTime"></easyui:inputDate></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="voucher_query_datalist" striped="true" rownumbers="true" url="queryVoucher.do" fit="true" pageSize="30" pagination="true"  >
				<easyui:facet name="toolbar">
					<easyui:button id="voucher_query_searchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
			    </easyui:facet>
			   <easyui:columns frozen="true">
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="voucherNo" width="80" title="凭证号"/>
			        <easyui:column field="orderNo" width="80" title="订单编号"/>
			        <easyui:column field="startTime" width="120" title="有效期始"/>
			        <easyui:column field="endTime" width="120" title="有效期止" />
			    </easyui:columns>
			    <easyui:columns>
			        <easyui:column field="vouchername" width="80" title="凭证名称"/>
			        <easyui:column field="goodsId" width="80" title="商品ID"/>
			        <easyui:column field="salePrice" width="80" title="销售价格" formatter="MoneyFormatYuan"/>
			         <easyui:column field="clientName" width="80" title="渠道名称"/>
			         <easyui:column field="cinemano" width="80" title="影院编码"/>
			         <easyui:column field="acid" width="80" title="活动编码"/>
			         <easyui:column field="filmno" width="80" title="影片编码"/>
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
				  _this._temp.list_box =  $('#voucher_query_datalist');
			},
	        Search: function () {

	        	var data ={
	        			voucherNo:$("#voucherNo").textbox("getValue")==""?0:$("#voucherNo").textbox("getValue"),
	        			orderNo:$("#orderNo").textbox("getValue"),
	        			startTime:$("#startTime").textbox("getValue"),
	        			endTime:$("#endTime").textbox("getValue")
	        	}
	            $('#voucher_query_datalist').datagrid('load', data);
	        },
	        Resend:function(){//重发

	        },
	        Delay:function(id,status){//延期

	        },
	        Freeze:function(id,voucherno,status){//冻结

	        },
	        Invalid:function(id,voucherno,status){//作废

	        },
	        formatter: function(value, rowData, index){
	        	var html = "";
     
	        	return html;
	        }
	};

	$(function(){
		datalist.Init();
	});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>