<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">影院产品结算总表</easyui:templateOverride>
<easyui:templateOverride name="head">
<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="valid_query_main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >
		    <easyui_ext:searchItem name="影院编码"><easyui_ext:textBox id="cinemano"  name="cinemano" ></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaName"  name="cinemaName" ></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="业务类型"><easyui_ext:comboBox id="goodstype" name="goodstype" width="<%= com.mopon.helpers.ui.combobox.Width.SHORT %>" data="${goodsTypeList }" /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="所在区域">
			        <input id="provinceNo" name="provinceNo" />
                    <input id="cityno" name="cityno" />
			</easyui_ext:searchItem>
			<easyui_ext:searchItem name="结算时间起"><easyui:inputDate id="begintime" name="begintime"></easyui:inputDate></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="结算时间止"><easyui:inputDate id="endtime" name="endtime"></easyui:inputDate></easyui_ext:searchItem>	
		</easyui_ext:searchCondition>
		
		
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="queryList.do"  fit="true" pageSize="30" pagination="true"  singleSelect="true"  showFooter="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="earchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="exportBtn" text="导出列表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			   <easyui:columns >
			        <easyui:column field="ck" checkbox="true" />
			        <easyui:column field="cinemano" title="影院编码"  />
			        <easyui:column field="cinemaName" title="影院名称" />
			        <easyui:column field="cityName" title="地区" />
			        <easyui:column field="goodstypeStr" title="商品类型" />
			         <easyui:column field="goodsshowtypevalue" title="放映类型" />
			        <easyui:column field="settlmentDate" title="结算时间" />
			         <easyui:column field="settlmentAmount" title="结算金额"  formatter="MoneyFormatYuan"/>			        
			         <easyui:column field="settlPrice" title="结算单价(每张/元)" formatter="MoneyFormatYuan" />
			         <easyui:column field="settlNum" title="结算数量(张)" />
			         <easyui_ext:formatterColumn title="操作" param="value, rowData, index">			       
			        <easyui_ext:handlerColumn text="明细" onclick="datalist.Display" param="'rowData.cinemano','rowData.cinemaName','rowData.settlmentDate','rowData.settlPrice','rowData.goodsshowtype'" condition="rowData.cinemaName,rowData.cityName"/>
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
	function goodstypeFormatter(value, rowData, index){
		if(value == "1"){
			return "选座票";
		}else if(value == "2"){
			return "单家通兑票";
		}else if(value == "3"){
			return "多家通兑票";
		}else if(rowData.cinemano == "本页合计" || rowData.cinemano == "总计"){
			return "";
		}
		return value;
	}
	
	function settlPriceFormat(value, rowData, index){
        if(rowData.cinemano == "本页合计" || rowData.cinemano == "总计"){
			return "";
		}if(value == 0){
			return "";
		}
		return value/100;
	}
	
	function settlmentAmountFormat(value, rowData, index){
		return value/100;
	}
	
	function settlNumFormat(value, rowData, index){
		if(value == 0){
			return "";
		}
		return value;
	}
	
	function settlmentDateFormat(value, rowData, index){
		return value.split(" ")[0];
	}
	
	/* function fomart(value, rowData, index){
		if(rowData.cinemano == "本页合计" || rowData.cinemano == "总计"){
			return "";
		}else{
			var data = "'" + rowData.cinemano + "'" +  "," + "'" + rowData.cinemaName + "'" + "," + "'" + rowData.settlmentDate + "'"+ "," + "'"+rowData.settlPrice+ "'" +"," + "'"+rowData.goodsshowtype+ "'";
			return "<a class=\'g-mlr-5\' onclick=\"datalist.Display(" + data + ")\">明细</a>";
		}
	} */
	
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
	        Display: function(cinemano, cinemaName, settlmentDate,settlPrice,goodsshowtype){
	        	settlmentDate = settlmentDate.split(" ")[0];
	        	var url = encodeURI(encodeURI("../rptSettlVouchervalidbase/showSettlVouchervalidbaseDetail.do?cinemano=" + cinemano + "&cinemaName=" + cinemaName + "&settlmentDate=" + settlmentDate+"&settlPrice="+settlPrice+"&goodsshowtype="+goodsshowtype));
	            parent.openTabrefresh("影院" + cinemaName + "验证记录表", url, true);
	        },
	        Export: function () {
	            $.messager.confirm("系统提示", "确定导出数据？", function (b) {
	                if (b) {
	                    cinemano=$("#cinemano").val();
	                    cinemaName=$("#cinemaName").val();
	                    goodstype=$("#goodstype").combobox("getValue");
	                    begintime=$("#begintime").datebox("getValue");
	                    endtime=$("#endtime").datebox("getValue");
	                    cityno=$("#cityno").combobox("getValue");
	                    var url = "../cinemaSettlProductDaily/exportRptCinemaSettlProDaily.do?cinemano=" + cinemano + "&cinemaName=" + cinemaName+ "&goodstype=" + goodstype
	                    + "&begintime=" + begintime+ "&endtime=" + endtime + "&cityno=" + cityno;
	        			window.open(url,'影院产品结算总表','');
	                }
	            });
	        },
	        fomart:function(cinemano, cinemaName, settlmentDate,settlPrice,goodsshowtype){
	    		if(cinemano == "本页合计" || cinemano == "总计"){
	    			return "";
	    		}else{
	    			var data = "'" + cinemano + "'" +  "," + "'" + cinemaName + "'" + "," + "'" + settlmentDate + "'"+ "," + "'"+settlPrice+ "'" +"," + "'"+goodsshowtype+ "'";
	    			return "<a class=\'g-mlr-5\' onclick=\"datalist.Display(" + data + ")\">明细</a>";
	    		}
	    	}
	};
	
	$(function(){
		Area.Init("provinceNo", "cityno", "areaNo");
		datalist.Init();
	})
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>