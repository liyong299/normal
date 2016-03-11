<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="singleSale_query_main" fit="true">
		<easyui_ext:searchCondition id="singleSale_query_search_form">
				<easyui_ext:searchItem name="通兑票名称"><easyui_ext:textBox id="singleSale_query_name" name="name" value="" width="150" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="通兑票类型"><easyui_ext:comboBox id="ticket_type" name="ticket_type" data="${ticketType }" value="" width="60"  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院"><easyui_ext:comboBox id="singleSale_query_cinemano" name="cinemano" data="${cinemaList }" value="" width="150"  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="singleSale_query_datalist" striped="true" url="${pageContext.request.contextPath }/sale/querySingleSale.do?status=3" fit="true" pageSize="10" singleSelect="true" fitColumns="true" pagination="true">
				<easyui:facet name="toolbar">
					<easyui:button id="singleSale_query_searchBtn" text="查询" plain="true" iconCls="icon-search" />	
			        <easyui:button id="btn_choose" plain="true" text="选中" iconCls="icon-ok"  onclick="datalist.Choose()"/>
			    </easyui:facet>
			   <easyui:columns frozen="true">
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="name" align="center" width="80" title="通兑票名称"/>
			        <easyui:column field="code" align="center" width="80" title="通兑票编码"/>
			        <easyui:column field="show_type" align="center" width="80" title="规格" formatter="function(value,row,index){
							if(value == '0'){
								return '2D';
							}else if(value == '1'){
								return '3D';
							}else if(value == '2'){
								return 'IMAX2D';
							}else if(value == '3'){
								return 'IMAX3D';
							}else if(value == '4'){
								return '中国巨幕2D';
							}else if(value == '5'){
								return '中国巨幕3D';
							}else {
								return value;
							}
							
						}"/>
			        <easyui:column field="status" align="center" width="80" title="状态" formatter="function(value,row,index){
							if(value == '0'){
								return '待编辑';
							}else if(value == '1'){
								return '待信息审核';
							}else if(value == '-1'){
								return '信息审核拒绝';
							}else if(value == '2'){
								return '待财务审核';
							}else if(value == '-2'){
								return '财务审核拒绝';
							}else if(value == '3'){
								return '上架';
							}else if(value == '-3'){
								return '下架';
							}else {
								return value;
							}
							
						}"/>
			    </easyui:columns>
			    <easyui:columns>
			        <easyui:column field="is_special_price" align="center" width="80" title="是否特殊结算" formatter="function(value, rowData, index){
			        	if(value == 0){
			        		return '否';
			        	}else if(value== 1){
			        		return '是';
			        	}else{
			        		return value;
			        	}
			        }"/>
			        <easyui:column field="settl_type" align="center" width="80" title="定价类型" formatter="function(value, rowData, index){
			        	if(value == 0){
			        		return '常规价';
			        	}else if(value == 1){
			        		return '忙时价';
			        	}else if(value == 2){
			        		return '闲时价';
			        	}else{
			        		return value;
			        	}
			        }"/>
			        <easyui:column field="valid_days" align="center" width="80" title="有效期(天)"/>
			        <easyui:column field="valid_stime" align="center" width="80" title="有效期起"/>
			        <easyui:column field="valid_etime" align="center" width="80" title="有效期止"/>
			        <easyui:column field="cinemaname" align="center" width="80" title="兑换影院"/>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		// 请求根路径
		var basePath = "${pageContext.request.contextPath}";
		var datalist = {
			Init: function () {
	            var _this = List;
	            _this._temp.list_box = $("#list-grid");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_productbox = $("#layout_productbox");
	        },
	        Search: function () {
	            $('#list-grid').datagrid('load', $(".search_box").serializeObject());
	        },
	        Choose: function () {
	        	var obj = $('#singleSale_query_datalist').datagrid('getSelections')[0];
	        	var html = '<input type="hidden" id="goodsId" value="'+obj.code+'" /><span id="goodsName">'+obj.name+'</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
				'<a href="javascript:void(0);" onclick="$(this, parent.document).parent().html(\'\');" >删除</a>';
	        	$("#voucher_div", parent.document).html(html);
	        }
		};
		$(function(){
			// 查询按钮
			$('#singleSale_query_searchBtn').on('click',function(event){
				var params = $("#singleSale_query_search_form").serializeObject();
				$('#singleSale_query_datalist').datagrid({
					queryParams: params
				});
				$('#singleSale_query_datalist').datagrid('reload');
			});
			
		});
		
		function closeWin(){
			 $('#singleSale_edit_div').window('close');
			 // 刷新页面
			 $('#singleSale_query_datalist').datagrid('reload');
		}
		
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>