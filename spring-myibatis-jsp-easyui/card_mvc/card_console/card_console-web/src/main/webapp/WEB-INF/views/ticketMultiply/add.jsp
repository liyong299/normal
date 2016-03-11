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

<easyui:templateOverride name="title">添加多家通兑票</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
        <div class="items">
        	<label style="width: 120px" for="itemName">通兑票名称<em>*</em>：</label> 
			<input name="name" value="${saleCommonTicket.name }" class="easyui-textbox easyui-validatebox" missingMessage="通兑票名称不能为空" required="true" type="text" validType="ajaxVerification['checkName.do']" />
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">简要备注：</label> 
			<input name="memo" value="${saleCommonTicket.memo }" class="easyui-validatebox easyui-textbox" type="text" />
        </div>

         <div  class="items">
        	<label style="width: 120px" for="itemName">定价类型<em>*</em>：</label> 
			<input name="price_type" value="0" required="true" type="radio" <c:if test="${saleCommonTicket.price_type == 0 || saleCommonTicket.price_type==null}">checked</c:if> />常规价
			<input name="price_type" value="1" required="true" type="radio"  <c:if test="${saleCommonTicket.price_type == 1 }">checked</c:if> />忙时价
			<input name="price_type" value="2" required="true" type="radio"  <c:if test="${saleCommonTicket.price_type == 2 }">checked</c:if> />闲时价
        </div>
        <%-- <div  class="items">
        	<label style="width: 120px" for="itemName">是否特殊结算价：</label> 
			<input name="is_special_price" value="1" type="checkbox" <c:if test="${saleCommonTicket.is_special_price == 1 }">checked</c:if> />
			<easyui:selectBooleanCheckbox id="is_special_price">
				<easyui:eventListener event="click" listener="Selected"></easyui:eventListener>
			</easyui:selectBooleanCheckbox>
        </div> --%>
         <div  class="items">
        	<label style="width: 120px" for="itemName">销售价<em>*</em>：</label> 
			<input name="sale_price" value="${saleCommonTicket.sale_price}" class="easyui-textbox easyui-validatebox" required="true" type="text" validType="pnumber" missingMessage="销售价不能为空"/>：元
        </div>
         <div  class="items"  id="settlDiv" >
        	<label style="width: 120px" for="itemName">结算价<em>*</em>：</label> 
			<input name="settl_price" value="${saleCommonTicket.settl_price}" class="easyui-textbox easyui-validatebox"  type="text" validType="pnumber" />
        </div>
         <div  class="items">
        	<label style="width: 120px;" for="itemName">有效期<em>*</em>：</label> 
			<div style="display:inline-block;">
				固定天数<input id="valid_type1" name="valid_type" value="0"  type="radio" class="vradio" onclick="saleSingleCheck()" <c:if test="${saleCommonTicket.valid_type == 0 || saleCommonTicket.valid_type ==null}">checked</c:if> /> <input name="valid_days" id="valid_days" value="${saleCommonTicket.valid_days }" class="easyui-textbox" type="text"  validType="pint"/>
				<br />
				时间范围<input id="valid_type2" name="valid_type" value="1"  type="radio" class="vradio" onclick="saleSingleCheck()" <c:if test="${saleCommonTicket.valid_type == 1 }">checked</c:if>> <input id="valid_stime" type="text" name="valid_stime" value="${ saleCommonTicket.valid_stime}" class="easyui-datebox" ></input> 到:<input id="valid_etime" name="valid_etime" value="${ saleCommonTicket.valid_etime}" type="text" class="easyui-datebox" ></input> 
			
			</div>
        </div>
         <div class="items">
        	<label style="width: 120px" for="itemName">销售渠道：</label> 
			<select id="channel_type" class="easyui-combobox" name="channel_type" style="width:200px;" value="${saleCommonTicket.channel_type }">   
			    <option value="0">全部渠道</option>   
			    <option value="1">限制渠道</option>   
			</select> 
        </div>         
         <div  class="items">
        	<label style="width: 120px" for="itemName">关联模版<em>*</em>：</label> 
        	
        	<select id="template_id" class="easyui-combobox" name="template_id" style="width:200px;" value="${saleCommonTicket.template_id }">   
        	<c:forEach items="${templateList }" var="template">
        	 <option value="${template.value }">${template.text }</option>   
        	</c:forEach>
			</select> 
			
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">POS机位置<em>*</em>：</label> 
        	<textarea rows="3" cols="40" class="textbox easyui-validatebox" required="true" name="pos_position">${saleCommonTicket.pos_position }</textarea>
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">凭证下发辅助说明<em>*</em>：</label> 
			<textarea rows="3" cols="40" class="textbox easyui-validatebox" required="true" name="validate_memo">${saleCommonTicket.validate_memo }</textarea>
        </div>
        <div  class="items">
        	<label style="width: 120px" for="itemName">凭证打印说明<em>*</em>：</label> 
			<textarea rows="3" cols="40" class="textbox easyui-validatebox" required="true" name="print_memo">${saleCommonTicket.print_memo }</textarea>
        </div>
        <div  class="items">
        	<label style="width: 120px" for="itemName">网站显示备注<em>*</em>：</label> 
			<textarea rows="3" cols="40" class="textbox easyui-validatebox" required="true" name="web_memo">${saleCommonTicket.web_memo }</textarea>
        </div>
        <input type="hidden" name="status" value="1">
         <div class="item item_row button" style="text-align: center">
            <a class="easyui-linkbutton" onclick="doSave()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var example={tabTitle:'多家通兑票管理',url:'${pageContext.request.contextPath }/ticketMultiply/list.html'} 
	function doSave() {	
		if($('#info').form('validate')){
		if(!validFn()){
				$.messager.alert('提示','选择的有效期类型没有输入值');
		}else if(!validTemplate()){
			$.messager.alert('提示','请选择关联模板');
		}else{
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:"${pageContext.request.contextPath }/ticketMultiply/save.do",
	            data:$('#info').serialize(),// 你的formid
	            dataType: "json",
	            async: false,
	            error: function(request) {
	                alert("Connection error");
	            },
	            success: function(data) {
	               if(data.success){
	            	 // $.messager.alert('信息提示','操作成功','success',null);
	            	   parent.refreshTab(example);
	            	   parent.$('#tabs').tabs('close', '添加多家通兑票');

	               }else{
	            	   alert(data.errMsg);
	               }            
	            }
	        }) 
		}
			
		}
	}
$(function(){
	// 验证通兑票名称是否存在
	$.extend($.fn.validatebox.defaults.rules,{
		ajaxVerification: { 
	        validator: function(value, url){
	        	var flag = true;
	        	$.ajax({
	                type: "POST",
	                url:url,
	                data:{parm:value},// 你的formid
	                dataType: "json",
	                async: false,
	                error: function(request) {
	                    alert("Connection error");
	                    flag = false;
	                },
	                success: function(data) {
	                   if(data){
	                	flag = data.success;
	                   }else{$("#valid_stime")
	                	   alert('请求失败');
	                	   flag = false;
	                   }            
	                }
	            }); 
	        	return flag;
	        },
	        message: '通兑票名称已存在'
	    }   
		    
		  });
	
	 $("#settlDiv").hide(); 
		  
		    $("#valid_stime").datebox({disabled:true});
			$("#valid_etime").datebox({disabled:true});
			$("#valid_days").textbox({disabled:false});

});
function Cancel(){
	 parent.$('#tabs').tabs('close', '添加多家通兑票');
}
//有效期类型验证
function validFn(){   
	var result = true;
 	var valid_type_list = $('input[name="valid_type"]');
 	for(var i=0;i<valid_type_list.length;i++){
 		if($(valid_type_list[i]).prop('checked')){
 			if($(valid_type_list[i]).val() == 0){
 				// 选择天数有效
 				var value = $('input[name="valid_days"]').val();
 				result = $.trim(value).length > 0;
 			}else if($(valid_type_list[i]).val() == 1){
 				// 选择时间段
 				var value = $('input[name="valid_stime"]').val();
 				var value1 = $('input[name="valid_etime"]').val();
 				result = $.trim(value).length > 0 && $.trim(value1).length > 0;
 			}
 		}
 	}
     return result;//$.trim(value).length<=0;
}
//模板类型验证
function validTemplate(){
	var result = true;
	var value = $("#template_id").combobox("getValue");
	if(value==""){
		result =false;
	}
	
	return result;
}

 function Selected(){
	 var obj = $("#is_special_price").attr("value");
	 $("#settlDiv").toggle(); //切换元素的可见状态。如果元素是可见的，切换为隐藏的；如果元素是隐藏的，切换为可见的
 }
 
 function saleSingleCheck(){
	    
	    	
	     	var valid_type_list = $('input[name="valid_type"]');
	     	for(var i=0;i<valid_type_list.length;i++){
	     		if($(valid_type_list[i]).prop('checked')){
	     			if($(valid_type_list[i]).val() == 0){
	     				// 选择天数有效
	     				$("#valid_stime").datebox("setValue","");
	     				$("#valid_etime").datebox("setValue","");
	     				
	     				$("#valid_stime").datebox({disabled:true});
	     				$("#valid_etime").datebox({disabled:true});
	     				$("#valid_days").textbox({disabled:false});
	     			}else if($(valid_type_list[i]).val() == 1){
	     				// 选择时间段
	     				$("#valid_days").textbox("setValue","");
	     				
	     				$("#valid_stime").datebox({disabled:false});
	     				$("#valid_etime").datebox({disabled:false});
	     				$("#valid_days").textbox({disabled:true});
	     			}
	     		}
	     	}
		   
	    }

	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>