<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加支付渠道关联影院</easyui:templateOverride>
<easyui:templateOverride name="head">
 <style type="text/css">
    .fieldset{
    	width: 95%; margin:0 auto; margin-top:5px; border:1px dashed #AAA;
    } 
</style> 
</easyui:templateOverride>
<easyui:templateOverride name="body">
    <form  id="savaChannelPayChannel"  class="edit_box" action="../channel/savaChannelPayChannel.do">
    
        <div>
   			<input id="channelno" name="channelno" type="hidden" value="${channelno}" />
		</div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">关联支付渠道：</label>
        	<fieldset class="fieldset">
        	<legend><input id="checkall"  name="checkall"    type="checkbox"  onclick="SelectAll()"/>全选</legend>
	        	<c:forEach items="${payChannelList}" var="payChannelList">
	        	<input id="payChannelId_${payChannelList.payChannelId}" name="payChannelId" value="${payChannelList.payChannelId}" type="checkbox" />${payChannelList.payChannelName}
	        	</c:forEach>
        	</fieldset>
        </div>
			
         <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	$(function(){
		var baseChannelPayChannels = ${baseChannelPayChannels};  	
		if(baseChannelPayChannels!=null && baseChannelPayChannels!=""){
			for(i=0;i<baseChannelPayChannels.length;i++){
				$("#payChannelId_" + baseChannelPayChannels[i].payChannelId).click();
			}
		}
 });
	
	function Save(){
		var isok = true;
		
		var check = $("[id^=payChannelId_]:checked");  //得到所有被选中的checkbox
		var channelChecked='';           
		check.each(function(i){        //循环拼装被选中项的值
			if(i>0){
			channelChecked = channelChecked+','+$(this).val();
			}else{
				channelChecked = $(this).val();
			}
		});
		
        if (channelChecked == '') {
            if (isok) {
                parent.Tips.Error("关联渠道为空！");
            }
            isok = false;
        } 
        
        var id = $("#channelno").val();
        if (channelChecked == ''||channelChecked==null) {
            if (isok) {
                parent.Tips.Error("支付渠道id为空！");
            }
            isok = false;
        } 
        
        var data = {
        	id:id,
        	chanelNo:channelChecked
        };
             
        $.ajax({
            cache: true,
            type: "POST",
            url:"savaChannelPayChannel.do",
            data:data,// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(result) {
               if(result.success){
            	   parent.datalist._temp.list_box.datagrid("reload");
        			parent.Tips.Success("成功");
        			Cancel();
               }else{
            	   alert(result.errMsg);
               }            
            }
        });
	}
	function Cancel(){
		parent.datalist._temp.layout_box.window("close");
	}
	
	function SelectAll(){
		var checkAll = $("#checkall").attr("checked");
		if(checkAll){
				$.each($("[id^=payChannelId_]"),function(i,n){
					$(n).attr("checked",true)
				});
	
		}else{
			$.each($("[id^=payChannelId_]"),function(i,n){
				$(n).attr("checked",false)
			});
		}
			
	}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>