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
			<easyui_ext:searchItem name="凭证号"><easyui_ext:textBox id="voucherno"  name="voucherno" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="订单号"><easyui_ext:textBox id="orderno" name="orderno" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="status" name="status"  data="${statusList }" /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="有效期始"><easyui:inputDate id="begintime"></easyui:inputDate></easyui_ext:searchItem>
			<easyui_ext:searchItem name="有效期止"><easyui:inputDate id="endtime"></easyui:inputDate></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="voucher_query_datalist" striped="true" rownumbers="true" url="queryVoucher.do" fit="true" pageSize="30" pagination="true"  >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="voucher_query_searchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="voucher_query_exportBtn" text="导出列表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/>
			    </easyui:facet>
			   <easyui:columns frozen="true">
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="voucherno" width="80" title="凭证号"/>
			        <easyui:column field="third_voucherno" width="80" title="第三方取票号"/>
			        <easyui:column field="orderno" width="80" title="订单编号"/>
			        <easyui:column field="channelname" width="80" title="渠道名称"/>
			        <easyui:column field="statusvalue" width="80" title="状态" />
			    </easyui:columns>
			    <easyui:columns>
			        <easyui:column field="cinemaname" width="80" title="影院名称"/>
			        <easyui:column field="hallname" width="80" title="影厅名称"/>
			        <easyui:column field="seat" width="80" title="座位信息"/>
			        <easyui:column field="filmname" width="80" title="影片名称"/>
			        <easyui:column field="seqtime" width="80" title="放映时间"/>
			         <easyui:column field="createtime" width="80" title="下发时间"/>
			        <easyui:column field="begintime" width="80" title="有效期始"/>
			        <easyui:column field="endtime" width="80" title="有效期止"/>
			        <easyui:column field="showtypevalue" width="80" title="兑换类型"/>
			        <easyui:column field="pricetype" width="80" title="计价类型"/>
			         <easyui:column field="validtime" width="80" title="验证时间"/>
			         <easyui:column field="issettl" width="80" title="是否结算" formatter="datalist.PrintFlag"/>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="延期" onclick="datalist.Delay" param="rowData.id,rowData.status"  condition="rowData.status==1"/>
			        <easyui_ext:handlerColumn text="冻结" onclick="datalist.Freeze" param="rowData.id,rowData.voucherno,rowData.status" condition="rowData.status==1"/>
			        <easyui_ext:handlerColumn text="作废" onclick="datalist.Invalid" param="rowData.id,rowData.voucherno,rowData.status" condition="rowData.status==1"/>
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
				  _this._temp.list_box =  $('#voucher_query_datalist');
			},
	        Search: function () {

	            $('#voucher_query_datalist').datagrid('load', $("#search_box").serializeObject());
	        },
	        Delay:function(id,status){//延期
	        	if(status!="1"){
	        		parent.Tips.Error("只有未使用的凭证才可以延期，该凭证当前状态不允许延期操作");
	        	}else {
	        		datalist._temp.layout_box.find("iframe").attr("src", "delayVoucher.html?id=" + id);
		        	datalist._temp.layout_box.window({
		                title: "凭证延期"
		            });
	        	}
	        },
	        Freeze:function(id,voucherno,status){//冻结
	        	if(status=="-1"){
	        		parent.Tips.Error("该凭证已被冻结,操作无效");
	        	}else if(status=="-2"){
	        		parent.Tips.Error("该凭证已被作废,操作无效");
	        	}else{
	        		$.messager.confirm("消息提醒","您确认冻结该条凭证记录吗？",function(r){
		        		if(r){
		        			$.ajaxRequest({
		    	         		url: "freezeVoucher.do",
		    	         		para: {id:id,voucherno:voucherno,status:-1},
		    	         		success: function(result){
		    	         			if(result.isSuccess){
		    	         				datalist._temp.list_box.datagrid("reload");
		    	             			Tips.Success("成功");
		    	             		}
		    	         			else{
		    	         				parent.Tips.Error(result.message);
		    	         			}
		    	         		}
		    	         	});
		        		}
		        	});
	        	}
	        },
	        Invalid:function(id,voucherno,status){//作废
	        	if(status=="-2"){
	        		parent.Tips.Error("该凭证已被作废,操作无效");
	        	}else{
	        		$.messager.confirm("消息提醒","您确认作废该条凭证记录吗？",function(r){
		        		if(r){
		        			$.ajaxRequest({
		    	         		url: "invalidVoucher.do",
		    	         		para: {id:id,voucherno:voucherno,status:-2},
		    	         		success: function(result){
		    	         			if(result.isSuccess){
		    	         				datalist._temp.list_box.datagrid("reload");
		    	             			Tips.Success("成功");
		    	             		}
		    	         			else{
		    	         				parent.Tips.Error(result.message);
		    	         			}
		    	         		}
		    	         	});
		        		}
		        	});
	        	}

	        },
	        Export:function(){
	        	var  voucherno = $("#voucherno").textbox("getValue");
				var orderno = $("#orderno").textbox("getValue");
				var status = $("#status").combobox("getValue");
				var url = "../voucher/exportVoucher.do?voucherno=" + voucherno + "&orderno=" + orderno + "&status=" + status;
				window.open(url,'凭证列表','');
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