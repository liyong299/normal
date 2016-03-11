<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加销售策略关联渠道</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
    <form  id="editChanelForm" action="saveChannel.do" >
    
        <div>
   			<input id="saleStrategyId" name="saleStrategyId" type="hidden" value="${saleStrategyId}" />
			<label style="width: 120px" for="itemName">銷售策略名：${strategyName}</label> 
		</div>
         <div  class="items">
        	<label style="width: 120px" for="itemName">关联渠道：</label>
        	<c:forEach items="${baseChannelList}" var="baseChannelList">
        	${baseChannelList.channelname}<input id="baseChannel" name="baseChannel" value="${baseChannelList.channelno}" type="checkbox" />
        	</c:forEach>
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
			var i=0;
			var arrayChannel = ${arrayChannel};
			if(arrayChannel!=null && arrayChannel!=""){
				for(i=0;i<arrayChannel.length;i++){
					$("input[type='checkbox']").each(function(index){
						var value1=$(this).val();
						var value2=arrayChannel[i];
						if(value1==value2){
				       		$(this).val(arrayChannel[i]);
				       		$(this).click();
						}
				    });
				}
			}
        });
		
		function Save(){
			var isok = true;
			
			var check = $("input:checked");  //得到所有被选中的checkbox
			var channelChecked=null;           
			check.each(function(i){        //循环拼装被选中项的值
				if(channelChecked==null){
					channelChecked='|'+$(this).val()+'|';
				}else{
					channelChecked = channelChecked+$(this).val()+'|';
				}
			});
			
	        /* if (channelChecked == '') {
	            if (isok) {
	                parent.Tips.Error("關聯渠道为空！");
	            }
	            isok = false;
	        }  */
	        
	        var id = $("#saleStrategyId").val();
	        if (channelChecked == ''||channelChecked==null) {
	            if (isok) {
	                parent.Tips.Error("銷售策略为空！");
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
	            url:"saveChannel.do",
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
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>