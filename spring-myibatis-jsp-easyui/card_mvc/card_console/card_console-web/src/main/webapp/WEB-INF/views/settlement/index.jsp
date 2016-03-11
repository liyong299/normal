<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">终端出票管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="valid_query_main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >
			<easyui_ext:searchItem name="凭证号"><easyui_ext:textBox id="voucherno"  name="voucherno" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="订单号"><easyui_ext:textBox id="orderno" name="orderno" value=""></easyui_ext:textBox></easyui_ext:searchItem>
			<easyui_ext:searchItem name="凭证类型"><easyui_ext:comboBox id="vouchertype" name="vouchertype" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>" data="${voucherTypeList }" /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="放映类型"><easyui_ext:comboBox id="goodsshowtype" name="goodsshowtype" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>" data="${showTypeList }" /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="验证类型"><easyui_ext:comboBox id="validtype" name="validtype" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>" data="${validTypeList }" /></easyui_ext:searchItem>
			<easyui_ext:searchItem name="开始时间"><easyui:inputDate id="begintime"></easyui:inputDate></easyui_ext:searchItem>	
			<easyui_ext:searchItem name="结束时间"><easyui:inputDate id="endtime"></easyui:inputDate></easyui_ext:searchItem>	
					
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="queryValidListByPage.do" fit="true" pageSize="30" pagination="true"  singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="earchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<%-- <easyui_ext:facetButton id="exportBtn" text="导出报表" plain="true" iconCls="icon-page_excel"  onclick="datalist.Export()"/> --%>
			    </easyui:facet>
			   <easyui:columns frozen="true">
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="id" width="80" title="序号"/>
			        <easyui:column field="voucherno" width="80" title="凭证号"/>
			        <easyui:column field="vouchertypename" width="80" title="凭证类型"/>
			        <easyui:column field="orderno" width="80" title="订单号"/>
			        <easyui:column field="validno" width="80" title="验证编码" />
			    </easyui:columns>
			    <easyui:columns>
			        <easyui:column field="validtypename" width="80" title="验证类型"/>
			        <easyui:column field="validtime" width="80" title="验证时间"/>
			        <easyui:column field="settlementdate" width="80" title="结算日期"/>
			        <easyui:column field="distributorName" width="80" title="出票渠道"/>			       
			        <%-- <easyui:column field="paychannleid" width="80" title="支付渠道"/> --%>
			        <easyui:column field="deviceid" width="80" title="设备编号"/>
			        <easyui:column field="tradeno" width="80" title="交易编码"/>
			        <easyui:column field="srctradeno" width="80" title="原交易流水号"/>
			        <easyui:column field="consumerSitesNo" width="80" title="影院编号"/>
			         <easyui:column field="consumerSitesName" width="80" title="影院名称"/>
			       <%--  <easyui:column field="goodsid" width="80" title="商品ID"/> --%>
			         <easyui:column field="goodsTypeValue" width="80" title="商品类型"/>
			         <easyui:column field="goodsattribute" width="80" title="商品属性"/>			          
			         <easyui:column field="goodsShowTypeValue" width="80" title="商品放映类型"/>
			         <easyui:column field="goodsnum" width="80" title="商品数量"/>
			         <easyui:column field="settlementprice" width="80" title="结算单价" formatter="MoneyFormatYuan"/>
			         <easyui:column field="settlementAllPrice" width="80" title="结算总价" formatter="MoneyFormatYuan"/>
			         <easyui:column field="saleprice" width="80" title="销售单价" formatter="MoneyFormatYuan"/>
			         <easyui:column field="saleAllPrice" width="80" title="销售总价" formatter="MoneyFormatYuan"/>
			         <easyui:column field="realSalePrice" width="80" title="真实售价" formatter="MoneyFormatYuan"/>
			         <easyui:column field="consumeAmount" width="80" title="消费值" />
			         <%-- <easyui:column field="settlOrgzainationId" width="80" title="结算机构ID"/> --%>
			         <easyui:column field="publOrgzainationId" width="80" title="发卡机构"/>
			         <easyui:column field="settlFlag" width="80" title="是否结算" formatter="datalist.SettlFlag"/>
			          <%-- <easyui:column field="resetFlag" width="80" title="是否重置" formatter="datalist.ResetFlag"/> --%>
			           <easyui:column field="exchangeFlag" width="80" title="是否多对一" formatter="datalist.ExchangeFlag"/>
			            <easyui:column field="reprintFlag" width="80" title="是否重打印" formatter="datalist.PrintFlag"/>
			             <easyui:column field="reprintcount" width="80" title="重打印次数" />
			       <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="重置打印" onclick="datalist.RePrint" param="rowData.id" />
			        <easyui_ext:handlerColumn text="凭证号" onclick="datalist.View" param="rowData.id" />

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
	        RePrint:function(id){//重打印
	        	
	        	$.messager.confirm("消息提醒","您确认重置打印该条验证记录吗？",function(r){
	        		if(r){
	        			$.ajaxRequest({
	    	         		url: "rePrint.do",
	    	         		para: {id:id,reprintFlag:0},
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
	        },
	        View:function(id){//查看
	        	$.ajaxRequest({
	         		url: "viewVoucherNo.do",
	         		para: {id:id},
	         		success: function(result){
	         			if(result.isSuccess){
	         				$.messager.show({
	         					title:"查看凭证号",
	         					msg:result.message,
	         					timeout:0,
	         					showType:'show',
	         					style:{
	         						right:'',
	         						bottom:''
	         					}
	         				});	             			
	             		}
	         			else{
	         				Tips.Success(result.message);
	         			}
	         		}
	         	});
	        },
	        Export:function(){

	        },	
	        ResetFlag: function (value, rowData, rowIndex) {//设置颜色
                if (rowData.voucherno) {
                    if (value == -1) {
                        return "<div class='icon-cross' style='width:20px;height:20px;margin:0 auto;'></div>";
                    }
                    else {
                        return "<div class='icon-ok' style='width:20px;height:20px;margin:0 auto;'></div>";
                    }
                }
            },
            PrintFlag: function (value, rowData, rowIndex) {//设置颜色
                if (rowData.voucherno) {
                    if (value == -1) {
                        return "<div class='icon-cross' style='width:20px;height:20px;margin:0 auto;'></div>";
                    }
                    else {
                        return "<div class='icon-ok' style='width:20px;height:20px;margin:0 auto;'></div>";
                    }
                }
            },
            SettlFlag: function (value, rowData, rowIndex) {//设置颜色
                if (rowData.voucherno) {
                    if (value == 1) {
                        return "<div class='icon-cross' style='width:20px;height:20px;margin:0 auto;'></div>";
                    }
                    else {
                        return "<div class='icon-ok' style='width:20px;height:20px;margin:0 auto;'></div>";
                    }
                }
            },
            ExchangeFlag: function (value, rowData, rowIndex) {//设置颜色
                    if (rowData.voucherno) {
                        if (value == 0) {
                            return "<div class='icon-cross' style='width:20px;height:20px;margin:0 auto;'></div>";
                        }
                        else {
                            return "<div class='icon-ok' style='width:20px;height:20px;margin:0 auto;'></div>";
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