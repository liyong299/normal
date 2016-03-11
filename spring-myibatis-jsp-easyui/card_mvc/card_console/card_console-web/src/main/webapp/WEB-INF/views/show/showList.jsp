<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="渠道排期编号"><easyui_ext:textBox id="channelShowCode" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院排期编号"><easyui_ext:textBox id="cinemaShowCode" value="" ></easyui_ext:textBox></easyui_ext:searchItem>	
				<easyui_ext:searchItem name="销售渠道"><easyui_ext:comboBox id="channelCode" data="${channelList}" panelMaxHeight="200"></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院编号"><easyui_ext:textBox id="cinemaCode" value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaName" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="电影编号"><easyui_ext:textBox id="filmCode" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="电影名称"><easyui_ext:textBox id="filmName" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="兑换类型"><easyui_ext:comboBox id="showType" data="${showTypeList}" panelMaxHeight="200"></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="排期状态"><easyui_ext:comboBox id="showStatus" data="${statusList}" panelMaxHeight="200"></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="放映时间"><easyui:inputDate id="showTime_Start" name="showTime_Start" value="${begintime}"  />到:<easyui:inputDate id="showTime_End" name="showTime_End" value="${endtime}"  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="创建时间"><easyui:inputDate id="createTime_Start" name="createTime_Start" value=""  />到:<easyui:inputDate id="createTime_End" name="createTime_End" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="30" pagination="true" singleSelect="true" fitColumns="true">
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()" />
					<easyui_ext:facetButton id="btn_Process" text="同步排期" plain="true" iconCls="icon-cog_go"  onclick="Process()" />		
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="channelShowCode" title="渠道排期编号"/>
			        <easyui:column field="channelCode" title="渠道编号"/>
			        <easyui:column field="channelName" title="渠道名称"/>
			        <easyui:column field="cinemaShowCode" title="影院排期编号"/>
			        <easyui:column field="cinemaCode" title="影院编码"/>
			        <easyui:column field="cinemaName" title="影院名称"/>
			        <easyui:column field="hallName" title="影厅名称"/>
			        <easyui:column field="filmCode" title="电影编号"/>
			        <easyui:column field="filmName" title="电影名称"/>
			        <easyui:column field="language" title="放映语言"/>
			        <easyui:column field="showTime" title="放映时间"/>
			        <easyui:column field="stopSellTime" title="停售时间"/>
			        <easyui:column field="showType" title="兑换类型" formatter='showtypeFormat'/>
			        <easyui:column field="duration" title="时长（分钟）"/>
			        <easyui:column field="stdPrice" title="标准价"/>
			        <easyui:column field="minPrice" title="最低价"/>
			        <easyui:column field="settlePrice" title="销售价"/>
			        <easyui:column field="submitPrice" title="结算价"/>
			        <easyui:column field="status" title="状态" formatter="statusFormat"/>  
			        <easyui:column field="createTime" title="创建时间"/>  
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<div id="layout_box" style="overflow:hidden" data-options="width: 560,height: 350,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	

</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
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
	        Add: function(){
	        },
	        Deleteasyui: function(){  	
	        }
		}
	
	    function Process(){
	    	$("#layout_box iframe").attr("src", '../show/toProcessShow.html');
            $('#layout_box').window({
                title: "同步排期"
            });
         // 刷新页面
         //  $('#layout_box').datagrid('reload');
	    }
	
		function statusFormat(value, rowData, index){
			if(rowData.status=="0"){
				return "下架";
			}
			else if(rowData.status=="1"){
				return "上架";
			}
			else if(rowData.status=="2"){
				return "失效";
			}
		    else{
			   return rowData.status;
			}
		}
	
		function showtypeFormat(value, rowData, index){
			if(rowData.showType=="0"){
				return "2D";
			}
			else if(rowData.showType=="1"){
				return "3D";
			}
			else if(rowData.showType=="2"){
				return "IMAX2D";
			}
			if(rowData.showType=="3"){
				return "IMAX3D";
			}
			else if(rowData.showType=="4"){
				return "中国巨幕2D";
			}
			else if(rowData.showType=="5"){
				return "中国巨幕3D";
			}
			if(rowData.showType=="99"){
				return "自定义";
			}
		    else{
			   return rowData.showType;
			}
		}
		
	
		$(function(){
			datalist.Init();
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>