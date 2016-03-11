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

<easyui:templateOverride name="title">添加用户</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="salesingleedit_edit_form" class="edit_box"  method="post" action="">
		<input type="hidden" name="code" value="${saleCommonTicket.code }" />
        <div class="items">
        	<label style="width: 120px" for="itemName">通兑票名称<em>*</em>：</label> 
			<input name="name" readonly value="${saleCommonTicket.name }" class="easyui-textbox easyui-validatebox" missingMessage="通兑票名称不能为空" required="true" type="text" validType="length[0,32]"/>
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">简要备注：</label> 
			<input name="remark" value="${saleCommonTicket.remark }" class="easyui-validatebox easyui-textbox" type="text" />
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">通兑类型<em>*</em>：</label>
			<input name="show_type" value="0" required="true" type="radio" disabled="true" <c:if test="${saleCommonTicket.show_type == 0 }">checked</c:if> />2D
			<input name="show_type" value="1" required="true" type="radio" disabled="true"  <c:if test="${saleCommonTicket.show_type == 1 }">checked</c:if> />3D
			<input name="show_type" value="2" required="true" type="radio"  disabled="true"  <c:if test="${saleCommonTicket.show_type == 2 }">checked</c:if> />IMAX2D
			<input name="show_type" value="3" required="true" type="radio"  disabled="true"  <c:if test="${saleCommonTicket.show_type == 3 }">checked</c:if> />IMAX3D
			<input name="show_type" value="4" required="true" type="radio" disabled="true"   <c:if test="${saleCommonTicket.show_type == 4 }">checked</c:if> />中国巨幕2D
			<input name="show_type" value="5" required="true" type="radio" disabled="true"  <c:if test="${saleCommonTicket.show_type == 5 }">checked</c:if> />中国巨幕3D
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">定价类型<em>*</em>：</label> 
			<input name="price_type" value="0" required="true" type="radio"  disabled="true" <c:if test="${saleCommonTicket.price_type == 0 }">checked</c:if> />常规价
			<input name="price_type" value="1" required="true" type="radio" disabled="true"  <c:if test="${saleCommonTicket.price_type == 1 }">checked</c:if> />忙时价
			<input name="price_type" value="2" required="true" type="radio" disabled="true"  <c:if test="${saleCommonTicket.price_type == 2 }">checked</c:if> />闲时价
        </div>
         <div  class="items">
        	<label style="width: 120px;" for="itemName">有效期<em>*</em>：</label> 
			<div style="display:inline-block;">
				固定天数<input name="valid_type" value="0" required="true" type="radio" <c:if test="${saleCommonTicket.valid_type == 0 }">checked</c:if> /> <input name="valid_days" value="${saleCommonTicket.valid_days }" class="easyui-textbox easyui-validatebox" missingMessage="固定天数不能为空"  validType="pint"/>
				<%-- <br />
				时间范围<input name="valid_type" value="1" required="true" type="radio" <c:if test="${saleCommonTicket.valid_type == 1 }">checked</c:if>> <input id="dd" type="text" name="valid_stime" value="${ saleCommonTicket.valid_stime}" class="easyui-datebox" missingMessage="时间范围不能为空"  validType="saleSingleCheck"  /> 到:<input id="dd" name="valid_etime" value="${ saleCommonTicket.valid_etime}" type="text" class="easyui-datebox"  missingMessage="时间范围不能为空"  validType="saleSingleCheck"  /> --%> 
			
			</div>
        </div>
         <%-- <div class="items">
        	<label style="width: 120px" for="itemName">销售渠道<em>*</em>：</label> 
			<select id="cc" class="easyui-combobox" name="channel_type" style="width:200px;" value="${saleCommonTicket.settl_type }">   
			    <option value="0">全部渠道</option>   
			    <option value="1">限制渠道</option>   
			</select> 
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">是否特殊结算价：</label> 
			<input name="is_special_price" value="1" type="checkbox" <c:if test="${saleCommonTicket.is_special_price == 1 }">checked</c:if> />
        </div> --%>
         <div  class="items">
        	<label style="width: 120px" for="itemName">兑换影院<em>*</em>：</label> 
			<input name="cinemaname" readonly value="${saleCommonTicket.cinemaname }" class="easyui-validatebox easyui-textbox" required="true" type="text" missingMessage="兑换影院不能为空" />
        	<input name="cinemano" value="${saleCommonTicket.cinemano }" type="hidden" />
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">POS机位置<em>*</em>：</label> 
        	<textarea rows="3" cols="40" class="textbox easyui-validatebox" name="pos_position" required="true" missingMessage="POS机位置不能为空" validType="lengthCheck(150)">${saleCommonTicket.pos_position }</textarea>
        </div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">凭证下发辅助说明<em>*</em>：</label> 
			<textarea rows="3" cols="40" class="textbox easyui-validatebox" name="validate_memo"  required="true" missingMessage="凭证下发辅助说明不能为空" validType="lengthCheck(150)">${saleCommonTicket.validate_memo }</textarea>
        </div>
        <div  class="items">
        	<label style="width: 120px" for="itemName">凭证打印说明<em>*</em>：</label> 
			<textarea rows="3" cols="40" class="textbox easyui-validatebox" name="print_memo"  required="true" missingMessage="凭证打印说明不能为空" validType="lengthCheck(150)">${saleCommonTicket.print_memo }</textarea>
        </div>
        <div  class="items">
        	<label style="width: 120px" for="itemName">网站显示备注<em>*</em>：</label> 
			<textarea rows="3" cols="40" class="textbox easyui-validatebox" name="web_memo"  required="true" missingMessage="网站显示备注不能为空" validType="lengthCheck(150)">${saleCommonTicket.web_memo }</textarea>
        </div>
         <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var basePath = "${pageContext.request.contextPath}";
		function Save(){
			if($('#salesingleedit_edit_form').form('validate')){
				if(validFn()){
					var params = $('#salesingleedit_edit_form').serializeObject();
					$.post(basePath+'/sale/saveSaleSingle.do',params,function(data){
						data = JSON.parse(data);
						$.messager.alert('提示',data["message"],data["isSuccess"]?'info':'error',function(){
							if(data["isSuccess"]){
								Cancel();// 关闭
							}
						});    
						
					});
				}else{
					$.messager.alert('提示','固定天数或时间范围不能同时为空!');
				}
			}
		}
		
		function Cancel(){
			parent.closeWin();
		}
		
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
			
			/* var validDayObj = $('input[name="valid_days"]').parent().find('input')[0];
			$(validDayObj).on('blur',function(event){
				console.info(1);
				console.info( $('input[name="valid_days"]').validatebox('saleSingleCheck') );
				
			}); */
			
			// 有效期 检验
			$.extend($.fn.validatebox.defaults.rules,{
				saleSingleCheck:{
				    validator:function(value,param){   
				    	var result = true;
				     	var valid_type_list = $('input[name="valid_type"]');
				     	for(var i=0;i<valid_type_list.length;i++){
				     		if($(valid_type_list[i]).prop('checked')){
				     			if($(valid_type_list[i]).val() == 0){
				     				// 选择天数有效
				     				result = $.trim(value).length > 0;
				     			}else if($(valid_type_list[i]).val() == 1){
				     				// 选择时间段
				     				result = $.trim(value).length > 0;
				     			}
				     		}
				     	}
					     return result;//$.trim(value).length<=0;
				    },
				    message:'不能为空'
				   }
				  });
			 
			
		});
		
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
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>