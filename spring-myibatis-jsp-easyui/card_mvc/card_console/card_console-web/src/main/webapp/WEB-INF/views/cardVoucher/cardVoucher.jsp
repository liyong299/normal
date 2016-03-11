<%@taglib prefix="easyui" uri="org.topteam/easyui"%>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>


<easyui:templateOverride name="title">卡券订单</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search_form">
			<easyui_ext:searchItem name="凭证号">
				<easyui_ext:textBox id="voucherno" name="voucherno" value=""></easyui_ext:textBox>
			</easyui_ext:searchItem>
			<easyui_ext:searchItem name="订单号">
				<easyui_ext:textBox id="orderno" name="orderno" value=""></easyui_ext:textBox>
			</easyui_ext:searchItem>
			<%-- <easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="usestatus" name="usestatus" width="<%= com.mopon.helpers.ui.combobox.Width.SHORT %>" data="${statusList }" /></easyui_ext:searchItem> --%>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true"
				url="cardVoucherList.do" fit="true" pageSize="30" pagination="true">
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true"
						iconCls="icon-search" onclick="datalist.Search()" />
				</easyui:facet>
				<easyui:columns frozen="true">
					<easyui:column field="ck" checkbox="true" />
					<easyui:column field="voucherseq" width="80" title="序号" />
					<easyui:column field="orderno" width="80" title="订单号"
						formatter="datalist.Voucher" />
					<easyui:column field="ordername" width="80" title="商品名称" />
					<easyui:column field="vouchertype" width="80" title="产品类型"
						formatter="datalist.productType" />
					<easyui:column field="voucherno" width="80" title="凭证号"
						formatter="encryption" />
				</easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>

	<div id="layout_box" style="overflow: hidden"
		data-options="width: 400,height: 460,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
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
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        }, 
	        
			 Voucher:function(value,rowData){ 
					var html="<a class=\'g-mlr-5\' onclick=\'datalist.cardVoucher(\"" + rowData.orderno + "\")\'>"+value+"</a>";
					return html;
				},
				productType:function(value){
					if(value=='0'){
						return '第三方预付卡';
		        	}else if(value == '1'){
		        		return '现金卡';
		        	}else if(value == '2' ){
		        		return '点卡';
		        	}else if(value == '3'){
		        		return '电影兑换卡';
		        	}else if(value == '4'){
		        		return '通兑票';
		        	}else if(value == '5'){
		        		return '现金券';
		        	}else if(value == '6'){
		        		return '兑换券';
		        	}else if(value == '7'){
		        		return '点券';
		        	}
			},

	};
	function encryption(value) {
        if (value.length > 0) {
            return "******" + value.substring(6, value.length);
        }
        $(function(){
    		datalist.Init();
    	});
    } 
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp"%>