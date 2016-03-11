<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">设置风险控制</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
		<input type="hidden" value="${actId}" id="actId" />
		<input type="hidden" value="${riskId}" id="riskId" />
        <div class="item item_row">
        	<label>活动风险控制：</label>
        	<label><input type="radio" name="riskType" value="1" checked="checked"/>数量控制</label>
        	<label><input type="radio" name="riskType" value="2" />金额控制</label>
        </div>
        <div class="item item_row">
        	<label>活动总量：</label>
        	<easyui_ext:textBox id="allNum" value="${allNum}" required="true" validType="integer" />/张<span class="star">*</span>
        </div>
        <div class="item item_row">
        	<label>活动总金额：</label>
        	<easyui_ext:textBox id="allAmount" value="${allAmount}" required="true" validType="currency" />/元<span class="star">*</span>
        </div>
        <div class="item  item_row">
        	<label>每日放量：</label>
        	<easyui_ext:textBox id="dailyNum" value="${dailyNum}" required="true"  />/张<span class="star">* 输入 0 为无限制</span>
        </div>
        <div class="item  item_row">
        	<label>每日下单总额：</label>
        	<easyui_ext:textBox id="dailyAmount" value="${dailyAmount}" required="true" validType="currency" />/元<span class="star">* 输入 0 为无限制</span>
        </div>
        <div class="item  item_row">
        	<label>每笔订单数量：</label>
        	<easyui_ext:textBox id="perOrderNum" value="${perOrderNum}" required="true" validType="integer" />/笔<span class="star">* 输入 0 为无限制</span>
        </div>
        <div class="item  item_row">
        	<label>身份限制：</label>

        	<easyui_ext:comboBox id="rightsType" name="rightsType"
					data="${actRightsTypeList }" value="" /><span class="star">*</span>
        </div>
        <div class="item  item_row">
        	<label>参与次数：</label>
        	<easyui_ext:textBox id="joinTimes" value="${joinTimes}" required="true" validType="integer" />/次<span class="star">* 输入 0 为无限制</span>
        </div>
        <div class="item  item_row">
        	<label>参与周期：</label>

        	<easyui_ext:comboBox id="joinTimesType" name="joinTimesType"
					data="${actJoinTimesTypeList }" value="" /><span class="star">*</span>
        </div>

        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	$(function () {

		$.extend($.fn.validatebox.defaults.rules,{
			integer : {// 验证整数
		        validator : function(value) {
		            return (/^[+]?[-]?[0-9]+\d*$/i.test(value))&&(0<=value);
		        },
		        message : '请输入整数'
		    },
		    currency : {// 验证货币
		        validator : function(value) {
		            return /^\d+(\.\d+)?$/i.test(value);
		        },
		        message : '货币格式不正确'
		    }
		});

		if ('null'==$('#allAmount').val() || ''==$('#allAmount').val() || '0.00'==$('#allAmount').val()) {
			$('#allNum').parent('div').show();
			$('#dailyNum').parent('div').show();
			$('#allAmount').parent('div').hide();
			$("#allAmount").textbox({required:false});
			$('#dailyAmount').parent('div').hide();
			$("#dailyAmount").textbox({required:false});
		} else{
			$("input[name='riskType'][value='2']").attr("checked","checked");
			$('#allNum').parent('div').hide();
			$("#allNum").textbox({required:false});
			$('#allAmount').parent('div').show();
			$('#dailyNum').parent('div').hide();
			$("#dailyNum").textbox({required:false});
			$('#dailyAmount').parent('div').show();
		}
		$('input[name="riskType"]').change(function(){

			$("#allAmount").textbox("setValue","0");
			$("#dailyAmount").textbox("setValue","0");
			$("#allNum").textbox("setValue","0");
			$("#dailyNum").textbox("setValue","0");
			$("#perOrderNum").textbox("setValue","0");
			$("#joinTimes").textbox("setValue","0");
			$("#rightsType").combobox("setValue","");
			$("#joinTimesType").combobox("setValue","");

			if(1==$(this).val()){
				$('#allNum').parent('div').show();
				$("#allNum").textbox({required:true});
				$('#allAmount').parent('div').hide();
				$("#allAmount").textbox({required:false});

				$('#dailyNum').parent('div').show();
				$("#dailyNum").textbox({required:true});
				$('#dailyAmount').parent('div').hide();
				$("#dailyAmount").textbox({required:false});

			}else{
				$('#allNum').parent('div').hide();
				$("#allNum").textbox({required:false});
				$('#allAmount').parent('div').show();
				$("#allAmount").textbox({required:true});

				$('#dailyNum').parent('div').hide();
				$("#dailyNum").textbox({required:false});
				$('#dailyAmount').parent('div').show();
				$("#dailyAmount").textbox({required:true});
			}
		});
	});
		function Save(){
			var flag = $('#info').form('validate');
			if (!flag){
				return;
			}
	        var temp = $('input[name="riskType"]:checked').val();
	        if (1==temp) {
				$("#allAmount").textbox("setValue","0");
	        } else {
				$("#allNum").textbox("setValue","0");
	        }
	        var data = {
	        	id: $('#riskId').val(),
	        	actId: $('#actId').val(),
	        	allNum: $('#allNum').textbox("getValue"),
	        	allAmount: $('#allAmount').textbox("getValue"),
	        	dailyNum: $('#dailyNum').textbox("getValue"),
	        	dailyAmount: $('#dailyAmount').textbox("getValue"),
	        	perOrderNum: $('#perOrderNum').textbox("getValue"),
	        	rightsType:$('#rightsType').combobox("getValue"),
	        	joinTimes:$('#joinTimes').textbox("getValue"),
	        	joinTimesType:$('#joinTimesType').combobox("getValue")
            };

           $.ajaxRequest({
        		url: "updateRisk.do",
        		para: data,
        		success: function(result){
        			if(result.isSuccess){
	        			parent.Tips.Success(result.message);
	        			Cancel();
	        		}
        			else{
        				parent.Tips.Error(result.message);
        			}
        		}
        	});
		}

		function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>