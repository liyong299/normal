<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="updateForm" class="edit_box" action="../file/saveFile.do">
		<input name="id" value="${file.id }"  type="hidden" />
		<div class="item">
			<label for="itemName">影片评分 ：<em>*</em></label> 
			<input id="score" name="score" value="${file.score }" class="easyui-validatebox" required="true" type="text"/>
		</div>
		<div class="item">
			<label for="itemName">推荐排序 ：<em>*</em></label> 
			<input id="sortno" name="sortno" value="${file.sortno }" class="easyui-validatebox" required="true" type="text"/>
		</div>
		<div class="item item_row button">
			<a class="easyui-linkbutton" onclick="doEdit()">保存</a>
			<a class="easyui-linkbutton" onclick="Cancel()">取消</a>
		</div>
	</form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	function doEdit() {	
		var isok = true;
		var name = $("#score").val();
		var sortno=$("#sortno").val();
        if (name == "") {
            isok = false;
            if (!isok) {
                parent.Tips.Error("影片评分不能为空！");
            }

        }else if(name>10||name<1){
        	 isok=false;
        	  if (!isok) {
	                parent.Tips.Error("请输入1至10以内的评分！");
	            }
	    
	    }
        
        if (sortno == "") {
           isok = false;
           parent.Tips.Error("推荐排序不能为空！");
        }else if(isNaN(sortno)){  
        	isok = false;
			parent.Tips.Error("推荐排序必须输入数字！");
		}else if(sortno>99 ||sortno<0){
       	 	isok=false;
	   	  	if (!isok) {
               parent.Tips.Error("请输入0至99以内的推荐排序！");
           }
   
   		}
        
        if(!isok){
        	return false;
        }
        
		$.ajax({
            cache: true,
            type: "POST",
            url:"../film/editFile.do",
            data:$('#updateForm').serialize(),// 你的formid
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
		parent.datalist._temp.layout_box_edit.window("close");
	}
	
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>