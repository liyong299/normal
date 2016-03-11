<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<style>
	em{color:red;}
	.items{
		padding:5px;
		margin-left:20px;
	}
	.items label{
		display:inline-block;
	}
</style>

<easyui:templateOverride name="title">信息审核多家通兑票</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
        <div class="items">
        	<label style="width: 120px" for="itemName">通兑票名称：</label> 
			<input name="name" value="${saleCommonTicket.name }" class="easyui-textbox easyui-validatebox" missingMessage="通兑票名称不能为空" required="true" type="text" readonly="readonly"/>
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">简要备注：</label> 
			<input name="remark" value="${saleCommonTicket.memo }" class="easyui-validatebox easyui-textbox" type="text" />
        </div>

         <div  class="items">
        	<label style="width: 120px" for="itemName">定价类型：</label> 
			<input name="price_type" value="0" required="true" type="radio" <c:if test="${saleCommonTicket.price_type == 0 || saleCommonTicket.price_type==null}">checked</c:if> />常规价
			<input name="price_type" value="1" required="true" type="radio"  <c:if test="${saleCommonTicket.price_type == 1 }">checked</c:if> />忙时价
			<input name="price_type" value="2" required="true" type="radio"  <c:if test="${saleCommonTicket.price_type == 2 }">checked</c:if> />闲时价
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">销售价：</label> 
			<input name="sale_price" value="${saleCommonTicket.sale_price}" class="easyui-textbox easyui-validatebox" required="true" type="text" validType="length[0,32]" missingMessage="销售价不能为空"/>：元
        </div>
         <%-- <div  class="items">
        	<label style="width: 120px" for="itemName">结算价：</label> 
			<input name="settl_price" value="${saleCommonTicket.settl_price}" class="easyui-textbox easyui-validatebox" required="true" type="text" validType="length[0,32]" missingMessage="结算价不能为空"/>
			
        </div> --%>
         <div  class="items">
        	<label style="width: 120px;" for="itemName">有效期：</label> 
			<div style="display:inline-block;">
				固定天数<input id="valid_type1" name="valid_type" value="0"  type="radio" class="vradio" <c:if test="${saleCommonTicket.valid_type == 0 || saleCommonTicket.valid_type ==null}">checked</c:if> /> <input name="valid_days" id="valid_days" value="${saleCommonTicket.valid_days }" class="easyui-textbox" type="text" />
				<br />
				时间范围<input id="valid_type2" name="valid_type" value="1"  type="radio" class="vradio" <c:if test="${saleCommonTicket.valid_type == 1 }">checked</c:if>> <input id="dd" type="text" name="valid_stime" value="${ saleCommonTicket.valid_stime}" class="easyui-textbox" ></input> 到:<input id="dd" name="valid_etime" value="${ saleCommonTicket.valid_etime}" type="text" class="easyui-textbox" ></input> 
			
			</div>
        </div>
         <%-- <div class="items">
        	<label style="width: 120px" for="itemName">销售渠道：</label> 
			<select id="cc" class="easyui-combobox" name="channelType" style="width:200px;" value="${saleCommonTicket.settlType }">   
			    <option value="0">全部渠道</option>   
			    <option value="1">限制渠道</option>   
			</select> 
        </div> --%>
         <%-- <div  class="items">
        	<label style="width: 120px" for="itemName">是否特殊结算价：</label> 
			<input name="is_special_price" value="1" type="checkbox" <c:if test="${saleCommonTicket.is_special_price == 1 }">checked</c:if> />
        </div> --%>
         <div  class="items">
        	<label style="width: 120px" for="itemName">关联模版：</label> 
			<select id="template_id" class="easyui-combobox" name="template_id" style="width:200px;" value="${saleCommonTicket.template_id }">   
        	<c:forEach items="${templateList }" var="template">
        	 <option value="${template.value }" <c:if test="${template.value== saleCommonTicket.template_id}">selected='selected'</c:if>>${template.text }</option>   
        	 
        	</c:forEach>
			</select>
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">POS机位置：</label> 
        	<textarea disabled rows="3" cols="40" class="textbox easyui-validatebox" name="pos_position">${saleCommonTicket.pos_position }</textarea>
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">凭证下发辅助说明：</label> 
			<textarea disabled rows="3" cols="40" class="textbox easyui-validatebox" name="validate_memo">${saleCommonTicket.validate_memo }</textarea>
        </div>
        <div  class="items">
        	<label style="width: 120px" for="itemName">凭证打印说明：</label> 
			<textarea disabled rows="3" cols="40" class="textbox easyui-validatebox" name="print_memo">${saleCommonTicket.print_memo }</textarea>
        </div>
        <div  class="items">
        	<label style="width: 120px" for="itemName">网站显示备注：</label> 
			<textarea disabled rows="3" cols="40" class="textbox easyui-validatebox" name="web_memo">${saleCommonTicket.web_memo }</textarea>
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">审核批注：</label> 
			<textarea rows="3" cols="40" class="textbox easyui-validatebox" name="memo"  required="true" missingMessage="审批备注不能为空" validType="lengthCheck(150)">${saleCommonTicket.memo }</textarea>
        </div>
      
        <input type="hidden" name="code" value="${saleCommonTicket.code}">
        
         <div class="item item_row button" style="text-align: center">
            <a class="easyui-linkbutton" onclick="Save(2)">信息审核通过</a>
            <a class="easyui-linkbutton" onclick="Save(-1)">信息审核拒绝通过</a>
           <a class="easyui-linkbutton" onclick="Cancel()">返回</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var example={tabTitle:'多家通兑票管理',url:'${pageContext.request.contextPath }/ticketMultiply/list.html'} 
	var basePath = "${pageContext.request.contextPath}";
	
	//提交审核
	function Save(status){
		if($('#info').form('validate')){
			var params = {"memo":$('textarea[name="memo"]').val(),code:$('input[name="code"]').val(),status:status};
			var url ="/ticketMultiply/infoAudit.do";
			console.info(JSON.stringify(params));
			$.post(basePath+url,params,function(data){
				data = JSON.parse(data);
					if(data["success"]){
				   parent.refreshTab(example);
		           parent.$('#tabs').tabs('close', '信息审核');
					}
			});
		}
	}
	
	//返回
	function Cancel(){
	parent.$('#tabs').tabs('close', '信息审核');
	}
	
	//验证审核内容
	$(function(){
		// taxtarea检验
		$.extend($.fn.validatebox.defaults.rules,{
		   lengthCheck:{
		    validator:function(value,param){    
		     	
			     return value.length<=param;
		    },
		    message:'内容不能超过150个字符'
		   }
		  });
		$("input").attr("disabled","disabled");
	});

</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>