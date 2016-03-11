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

<easyui:templateOverride name="title">同步排期</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
 <form  id="processForm" class="edit_box"  action="../show/process.do" >
        <div class="items">
	         <label style="width: 120px" for="itemName">影院编号<em>*</em>：</label>
			 <easyui_ext:textBox id="cinemaCode" value=""></easyui_ext:textBox>
		</div>	 
		<div class="items">
			 <label style="width: 120px" for="itemName">销售渠道<em>*</em>：</label>				
			 <easyui_ext:comboBox id="channelCode" data="${channelList}" panelMaxHeight="200"></easyui_ext:comboBox>	
		</div>		
		<div class="items">	
		     <label style="width: 120px;vertical-align: top;" for="itemName">备注<em>*</em>：</label>
			 <label style="width: 350px; color:blue;" id="lblMsg">点下面的按钮进行手动同步！</label> 
		</div>	
      
         <div class="item item_row button"> 
            <a class="easyui-linkbutton" onclick="doProcess()">执行同步</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	 $(function(){
			
	 });
	
	function doProcess() {
		
		$('#lblMsg').html('');
		
		$.ajax({
            cache: true,
            type: "POST",
            url:"../show/process.do",
            data:$('#processForm').serialize(),// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
               if(data.success){
	            	   //parent.Tips.Success("同步成功" + data.resultMsg);
	            	   $('#lblMsg').html("同步成功！ " +"<br/>" + data.resultMsg);
	            	   parent.datalist._temp.list_box.datagrid("reload");
            		 //  Cancel();
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