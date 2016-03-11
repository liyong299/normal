<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加用户</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
        <div class="item"><label>角色名：</label><easyui_ext:textBox id="name" /><span class="star">*</span></div>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		function Save(){
			var isok = true;
			
			var name = $("#name").textbox("getValue");
	        if (name == "") {
	            $("#name").textbox("showError", "角色名不能为空！");
	            if (isok) {
	                parent.Tips.Error("角色名不能为空！");
	            }
	            isok = false;
	        }
	        else if (name.length > 20) {
	            $("#name").textbox("showError", "角色名不能超过20个字符！");
	            if (isok) {
	                parent.Tips.Error("角色名不能超过20个字符！");
	            }
	            isok = false;
	        }
	        else {
	            $("#name").textbox("closeError");
	        }
	        
	        if(!isok){
	        	return false;
	        }
	        
	        var data = {
       			name: name
            };
	        
           $.ajaxRequest({
        		url: "add.do",
        		para: data,
        		success: function(result){
        			if(result.isSuccess){
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
			parent.datalist._temp.layout_box.window("close");
		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>