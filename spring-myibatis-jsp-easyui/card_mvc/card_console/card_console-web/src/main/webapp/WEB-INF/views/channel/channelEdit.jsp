<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
   <div class="item">
        <form id="updateForm" class="edit_box" action="../channel/saveChannel.do">
            <input name="channelno" value="${channel.channelno }"  type="hidden" />
            <div  class="item">
					<label for="itemName">渠道编号：</label> 
					<easyui_ext:textBox id="channelno" name="channelno"  value="${channel.channelno }"  disabled="true"></easyui_ext:textBox>
			</div>
			<div  class="item">
					<label for="itemName">渠道名称 ：</label> 
					<easyui_ext:textBox id="channelname" name="channelname"  value="${channel.channelname }"  disabled="true"></easyui_ext:textBox>
			</div>
			<div  class="item">
					<label for="itemName">渠道类型 ：</label> 
					<easyui_ext:comboBox id="type" name="type"  value="${channel.type }" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>"  data="<%= com.mopon.helpers.ui.combobox.Data.getTepyList() %>" ></easyui_ext:comboBox>
			</div>			
			<div  class="item">
					<label for="itemName">结算方式：</label> 
					<easyui_ext:comboBox id="settlType" name="settlType"  value="${channel.settlType }" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>"  data="<%= com.mopon.helpers.ui.combobox.Data.getSettlTypeList() %>" ></easyui_ext:comboBox>
			</div>
			<div  class="item">
					<label for="itemName">分成方式：</label> 
					<easyui_ext:comboBox id="dividedType" name="dividedType"  value="${channel.dividedType }" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>"  data="<%= com.mopon.helpers.ui.combobox.Data.getdividedTypeList() %>"/>
			</div>
			<div  class="item">
					<label for="itemName">分成单价：</label> 					
					<easyui_ext:textBox id="unitPrice" name="unitPrice" value="${channel.unitPrice }" validType="pnumber"></easyui_ext:textBox>元
			</div>
			<div  class="item">
					<label for="itemName">分成比例：</label> 
					<easyui_ext:textBox id="rate" name="rate" value="${channel.rate }" validType="pnumber"></easyui_ext:textBox>%
			</div>		
			<div  class="item">
					<label for="itemName">渠道级别：</label> 
					<easyui_ext:comboBox id="channelLevel" name="channelLevel"  value="${channel.channelLevel}" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>"  data="${channelLevelList}" ></easyui_ext:comboBox>
			</div>
			<div  class="item">
					<label for="itemName">父级渠道：</label> 
					<easyui_ext:comboBox id="parentchannelno" name="parentchannelno"  value="${channel.parentChannelNo}" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>"  data="${parentChannelKvpList}" ></easyui_ext:comboBox>
			</div>
			
			
			<div  class="item">
					<label for="itemName">渠道关键字：</label> 
					<easyui_ext:textBox id="channelKey" name="channelKey" value="${channel.channelKey}" ></easyui_ext:textBox>
			</div>
			<div  class="item">
					<label for="itemName">渠道域名地址：</label> 
					<easyui_ext:textBox id="channelUrl" name="channelUrl" value="${channel.channelUrl}" ></easyui_ext:textBox>
			</div>
			<div  class="item">
					<label for="itemName">渠道对接类型：</label> 
					<easyui_ext:comboBox id="dockingType" name="dockingType"  value="${channel.dockingType}" width="<%= com.mopon.helpers.ui.combobox.Width.NORMAL %>"  data="${dockingTypeList}" ></easyui_ext:comboBox>
			</div>	
			
			<div  class="item">
					<label for="itemName">下方凭证方式：</label> 
					<c:forEach var="sendVoucher" items="${sendVoucherList}" varStatus="status">		
					  <c:if test="${status.index==0}">
					  		<input id="sendType_0_${sendVoucher.getValue()}" name="sendType0"  value="${sendVoucher.getValue()}"    type="checkbox" />${sendVoucher.getName()}
					  </c:if>			
        			</c:forEach>
        			
			</div>		
			<div  class="item">
					<label for="itemName">&nbsp;</label> 					
        			<c:forEach var="sendVoucher" items="${sendVoucherList}"  varStatus="status">		
        				<c:if test="${status.index !=0}">			
        					<input id="sendType_1_${sendVoucher.getValue()}" name="sendType1"  value="${sendVoucher.getValue()}"    type="checkbox" />${sendVoucher.getName()}
        				</c:if>
        			</c:forEach>
			</div>						
			<div class="item item_row button">
			<a class="easyui-linkbutton" onclick="doEdit()">保存</a>
			<a class="easyui-linkbutton" onclick="Cancel()">取消</a>
		</div>
        </form>
    </div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	$(function(){		
		 var divided =  $("#dividedType").combobox("getValue");
		 if(divided=='2'){						  
			  $("#rate").textbox({disabled:false});
			  //$("#rate").textbox("setValue","");
			  //$("#unitPrice").textbox("setValue","");					  
			  $("#unitPrice").textbox({disabled:true});
		  }else if(divided=='1'){
			  $("#unitPrice").textbox({disabled:false});
			  //$("#unitPrice").textbox("setValue","");		
			  //$("#rate").textbox("setValue","");
			  $("#rate").textbox({disabled:true});
		  }
		
		 var channelLevel =  $("#channelLevel").combobox("getValue");
		 $("#channelLevel").combobox({ disabled: true });
		 if(channelLevel=='1'){						  
			 $("#parentchannelno").combobox({ disabled: true });
			  $("#parentchannelno").combobox("select","");
		  }
			 //当不下发选中时，清空其他的下发方式			  
			$("[name=sendType0]").click(function(){
				$.each($("[name=sendType1]:checked"),function(i,n){
					$(n).attr("checked",false)
				});
			});
			//当选择了其他下发方式，情况不下发方式
			$("[name=sendType1]").click(function(){
				$.each($("[name=sendType0]:checked"),function(i,n){
					$(n).attr("checked",false)
				});
			});
			
			 $.each($("[name^=sendType]"),function(i,n){
		        	var sendTypes = "${channel.sendVoucherType}";
		        	var sendList = new Array();
		        	sendList = sendTypes.toString().split(",");
		        	for(var k=0;k<sendList.length;k++){
		        		if(sendList[k]== $(n).val()){
		        			$(n).attr("checked",true);
		        		}
		        	}
		        });
	});
		 $("#dividedType").combobox({
			 onChange: function () {
				 var divided =  $("#dividedType").combobox("getValue");
					
					  if(divided=='2'){						  
						  $("#rate").textbox({disabled:false});
						  $("#rate").textbox("setValue","");
						  $("#unitPrice").textbox("setValue","");					  
						  $("#unitPrice").textbox({disabled:true});
					  }else if(divided=='1'){
						  $("#unitPrice").textbox({disabled:false});
						  $("#unitPrice").textbox("setValue","");		
						  $("#rate").textbox("setValue","");
						  $("#rate").textbox({disabled:true});
					  }
			 }
			 }); 
		 
		 $("#channelLevel").combobox({
				 onChange: function () {
					 var channelLevel =  $("#channelLevel").combobox("getValue");
					  if(channelLevel=='1'){	
						  $("#parentchannelno").combobox({ disabled: true }); 
						  $("#parentchannelno").combobox("select","");
					  }else if(channelLevel=='2'){
						  $("#parentchannelno").combobox({ disabled: false }); 
					  }
				 }
			 }); 
	
	function doEdit() {	
		var isok = true;
		var channelname = $("#channelname").val();
        if (channelname == "") {
            isok = false;
            if (!isok) {
                parent.Tips.Error("渠道名称不能为空！");
            }
        }
        
        var type = $("#type").combobox("getValue");
        if(type==""){
        	 isok = false;
             if (!isok) {
                 parent.Tips.Error("请选择渠道类型！");
             }
        }
        
        var settlType = $("#settlType").combobox("getValue");
        if(settlType==""){
        	 isok = false;
             if (!isok) {
                 parent.Tips.Error("请选择结算方式！");
             }
        }
        
        var dividedType = $("#dividedType").combobox("getValue");
        if(dividedType==""){
        	 isok = false;
             if (!isok) {
                 parent.Tips.Error("请选择分成方式！");
             }
        }
        
        var unitPrice = $("#unitPrice").val();
        if (unitPrice <0) {
            isok = false;
            if (!isok) {
                parent.Tips.Error("分成单价不能为负数！");
            }
        }
        var rate = $("#rate").val();
        if (rate<0) {
            isok = false;
            if (!isok) {
                parent.Tips.Error("分成比例不能为负数！");
            }
        }
        
        
        var channelLevel = $("#channelLevel").combobox("getValue");
        if(channelLevel==""){
        	 isok = false;
             if (!isok) {
                 parent.Tips.Error("请选择渠道级别！");
             }
        }
        var parentchannelno = $("#parentchannelno").combobox("getValue");
        if(channelLevel=="2"){
	        if(parentchannelno==""){
	        	 isok = false;
	             if (!isok) {
	                 parent.Tips.Error("请选择父级渠道！");
	             }
	        }
        }
        
        var sendVoucherType="";
        $.each($("[name^=sendType]:checked"),function(i,n){
        	sendVoucherType += $(n).val()+",";
        });
        if(sendVoucherType.length>1){
        	sendVoucherType = sendVoucherType.substring(0,sendVoucherType.length-1);
        }
        
        if(sendVoucherType.length==0){
        	isok = false;
            if (!isok) {
                parent.Tips.Error("请选择凭证下发方式！");
        		}
         }
        var dividedType =  $("#dividedType").combobox("getValue");
        var channelno = $("#channelno").val();
        var settlType = $("#settlType").combobox("getValue");
        var channelKey = $("#channelKey").val();
        var channelUrl = $("#channelUrl").val();
        var dockingType = $("#dockingType").combobox("getValue");
        
        if(!isok){
        	return false;
        }
        
        var data={
        		channelno:channelno,
        		channelname:channelname,
        		type:type,
        		unitPrice:unitPrice,
        		rate:rate,
        		sendVoucherType:sendVoucherType,
        		dividedType:dividedType,
        		settlType:settlType,
        		channelLevel:channelLevel,
        		parentChannelNo:parentchannelno,
           		channelKey:channelKey,
        		channelUrl:channelUrl,
        		dockingType:dockingType
        };
        
		$.ajax({
            cache: false,
            type: "POST",
            url:"../channel/editChannel.do",
            data: data,//$('#updateForm').serialize(),// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            
            success: function(data) {
               if(data.success){
	            	   parent.Tips.Success("保存成功");
	            	   parent.datalist._temp.list_box.datagrid("reload");
            		   Cancel();
	               }else{
	            	   alert(data.errMsg);
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