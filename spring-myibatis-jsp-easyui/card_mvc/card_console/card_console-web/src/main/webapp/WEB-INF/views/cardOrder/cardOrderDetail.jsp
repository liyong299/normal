<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> --%>
<easyui:templateOverride name="title">订单信息</easyui:templateOverride>
<easyui:templateOverride name="head">
 <style type="text/css">
    .border-table {
        border-collapse: collapse;
        border: none;
    }
    .border-table td {
        border: solid #000 1px;
    }
    .fieldset{
    	width: 95%; margin:0 auto; margin-top:5px; border:1px dashed #AAA;
    }
</style>
</easyui:templateOverride>

<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
	<div align="left">
			<fieldset class="fieldset">
			<legend>订单信息:</legend>
			<table>
				<tr>
				    <td align="right">订单号:</td>
				    <td><easyui_ext:textBox id="orderNo" value="${order.orderno}"  readonly="true" /></td>
				    <td align="right">产品名称:</td>
				    <td><easyui_ext:textBox id="orderType" value="${order.goodsname}"  readonly="true" /></td>
				    <td align="right">产品类型:</td>
				  	<td><easyui_ext:textBox id="producttype" value="${producttypestr}"  readonly="true" /></td>
			  </tr>
			</table>
			</fieldset>

			 </div>
			<div align="left">
			<fieldset class="fieldset">
			<legend>绑定模板:</legend>
				<table>
				<tr>
					<td><easyui_ext:comboBox id="template" value="${order.templatename}"  readonly="true" /></td>
				</tr>
				</table>
				<div style="float:left;"> <div id="select-cinema-grid" style="width: 750px;"></div></div>
			</fieldset>
			</div>
			<div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Cancel()">关闭</a>
        </div>
	</form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
    $('#select-cinema-grid').datagrid({
   	 url:'getTempCinemas.do?templateid=${order.templateid}',
       pagination: true,
       singleSelect: true,
       pageSize: 10,
       columns: [[
                  { field: 'cinemano', title: '影院编码', width: 90 },
                  { field: 'cinemaname', title: '影院名称', width: 130 },
                  { field: 'priceShow', title: '价格范围（元）',width: 500 },
       ]]
   });
	var datalist = {
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
		}
	}
    $('#template').combobox({
         url: 'getTemplate.do?productType=${order.producttype}', 
        valueField: 'templateid',
        textField: 'templatename',
        onLoadSuccess: function () {
        },
        onSelect: function (item) {
            var options = $('#select-cinema-grid').datagrid('options');
            options.url = "getTempCinemas.do";
                $('#select-cinema-grid').datagrid('load', {
                    templateid: item.templateid,
                    rad: Math.random()
                }); 
        }
    });
		$(function(){
			$("#templateId").textbox("setValue", "${templateId}");
			$("#templateName").textbox("setValue", "${templateName}");
			$("#productType").combobox("setValue", "${productType}");
			if("${templateType}"==="0")
			{
				$("input[name='templateType'][value=0]").attr("checked",true);
			}
			else
			{
				$("input[name='templateType'][value=1]").attr("checked",true);
			}
		});
		function Check(status){
	        var data={
	        		templateId:templateId.value,
	        		status:status
	        };
	        $.ajaxRequest({
	        	url:"check.do",
	        	para:data,
	        	success:function(result){
	        	if(result.isSucceed){
	    		   	parent.datalist._temp.list_box.datagrid("reload");
	        		parent.Tips.Success("成功");
	        		Cancel();
	        	}
	        	else{
	        		parent.Tips.Error(result.message);
	        		}
	        	}
	        });
		}
		function Cancel(){
			parent.datalist._temp.layout_box1.window("close");
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>