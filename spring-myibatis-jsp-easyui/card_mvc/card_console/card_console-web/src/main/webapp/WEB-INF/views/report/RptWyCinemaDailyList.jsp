<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">			
			<easyui_ext:searchItem name="中影订单编号"><easyui_ext:textBox id="orderno"  value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="微影订单编号"><easyui_ext:textBox id="wyorderno"  value=""></easyui_ext:textBox></easyui_ext:searchItem>	    
			<easyui_ext:searchItem name="中影下单时间"><easyui:inputDate id="orderTime_start" name="orderTime_start" value="${begintime}"  />到:<easyui:inputDate id="orderTime_end" name="orderTime_end" value="${endtime}"  /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="中影订单状态"><easyui_ext:comboBox id="status" data="${statusList}" panelMaxHeight="200"></easyui_ext:comboBox></input></easyui_ext:searchItem>
			<easyui_ext:searchItem name="影院编码"><easyui_ext:textBox id="cinemano"  name="cinemano" ></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaname"  name="cinemaname" ></easyui_ext:textBox></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true"  showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />
					<easyui_ext:facetButton id="btn_export" text="导出列表"  plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="orderno" title="中影订单号"/>
			        <easyui:column field="wyorderno" title="微影订单号"/>
			        <easyui:column field="ordertime" title="中影下单时间"/>
			        <easyui:column field="status" title="中影订单状态"  formatter="statusFormat"/>
			        <easyui:column field="amount" title="总金额（元）" />
			        <easyui:column field="cinemano" title="影院编号"/>
			        <easyui:column field="cinemaname" title="影院名称" />
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	function statusFormat(value, rowData, index){
		if(rowData.status=="3"){
			return "地面出票成功";
		}
		else if(rowData.status=="4"){
			return "凭证成功";
		}
		else if(rowData.status=="6"){
			return "地面出票失败";
		}
		else if(rowData.status=="7"){
			return "凭证失败";
		}
		else if(rowData.status=="8"){
			return "已撤销";
		}
		else{
		   return rowData.status;
		}
	}
		var datalist = {
			_temp: {
	        	list_box: null,
	        	layout_box: null
	        },
			Init: function () {
	            var _this = datalist;
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	        },
	        Search: function () {
	        	var orderTimestart = $("#orderTime_start").val();
	        	var orderTimeend = $("#orderTime_end").val();
	        	 $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){	        	
	        },
	        Export: function () {
	            $.messager.confirm("系统提示", "确定导出数据？", function (b) {
	                if (b) {
	                	debugger;
	                	orderno=$("#orderno").val();
	                	wyorderno=$("#wyorderno").val();
	                	orderTimestart=$("#orderTime_start").textbox("getValue");
	                	orderTimeend=$("#orderTime_end").textbox("getValue");
	                	status=$("#status").combobox("getValue");
	                	cinemano=$("#cinemano").val();
	                	cinemaname=$("#cinemaname").val();
	                	
	                    var url = "../rptWyCinemaDaily/exportRptWyCinemaDailyList.do?orderno=" + orderno + "&wyorderno=" + wyorderno+ "&orderTime_start=" + orderTimestart
	                    + "&orderTime_end=" + orderTimeend + "&status=" + status+ "&cinemano=" + cinemano+ "&cinemaname=" + cinemaname;
	        			window.open(encodeURI(encodeURI(url)),'微信对账报表','');
	                }
	            });
	        },
	        Deleteasyui: function(){
	        }
		};
		
		
		 $(function () {  
			 datalist.Init();
		 });
       
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>