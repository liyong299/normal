<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">编辑用户</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
		<input id="id" type="hidden" value="" />
        <div class="item"><label>登录名：</label><easyui_ext:textBox id="name" readonly="true" /><span class="star">*</span></div>
        <div class="item"><label>用户名：</label><easyui_ext:textBox id="fullName" /><span class="star">*</span></div>
        <div class="item"><label>密码：</label><easyui_ext:textBox id="password" /><span class="star">*</span></div>
        <div class="item"><label>角色：</label><easyui_ext:comboBox id="roleID" data="<%= com.mopon.helpers.ui.combobox.Data.getRoleList(false) %>" panelMaxHeight="100" /></div>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>


<easyui:templateOverride name="script">
	<script>
		$(function(){
			$("#id").val("${id}");
			$("#name").textbox("setValue", "${name}");
			$("#fullName").textbox("setValue", "${fullName}");
			$("#roleID").combobox("setValue", "${roleID}");
		});
	
		function Save(){
			var isok = true;
	        
			var id = $("#id").val();
	        var fullName = $("#fullName").textbox("getValue");
	        if (fullName == "") {
	            $("#fullName").textbox("showError", "用户名不能为空！");
	            if (isok) {
	                parent.Tips.Error("用户名不能为空！");
	            }
	            isok = false;
	        }
	        else if (fullName.length > 20) {
	            $("#fullName").textbox("showError", "用户名不能超过20个字符！");
	            if (isok) {
	                parent.Tips.Error("用户名不能超过20个字符！");
	            }
	            isok = false;
	        }
	        else {
	            $("#fullName").textbox("closeError");
	        }
	        
	        var password = $("#password").textbox("getValue");
	        if (password == "") {
	            $("#password").textbox("showError", "密码不能为空！");
	            if (isok) {
	                parent.Tips.Error("密码不能为空！");
	            }
	            isok = false;
	        }
	        
	        var roleID = $("#roleID").combobox("getValue");
	        
	        if(!isok){
	        	return false;
	        }
	        
	        var data = {
	        	id: id,
       			name: name,
       			fullName: fullName,
       			password: password,
       			roleID: roleID
            };
	        
           $.ajaxRequest({
        		url: "edit.do",
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