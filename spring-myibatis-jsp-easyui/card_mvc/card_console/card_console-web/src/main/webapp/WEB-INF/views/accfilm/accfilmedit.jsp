<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">标注</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
		<input id="id" name="id" value="${accFilm.id }"  type="hidden" />
		<input id="showtypes" name="showtypes" value="${accFilm.showtypes }"  type="hidden" />
		<input id="provider" name="provider" value="${accFilm.provider }"  type="hidden" />
        <div class="item"><label>标准影片编码：</label><easyui_ext:textBox id="code" name="code"  value="${accFilm.code }"/></div>
        <div class="item"><label>非标准影片编码：</label><easyui_ext:textBox id="extfilmcode" name="extfilmcode" readonly="true" value="${accFilm.extfilmcode }"  /></div>
        <div class="item"><label>影片名称：</label><easyui_ext:textBox id="name" name="name"  readonly="true" value="${accFilm.name }"  /></div>
        
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
		 var code = $("#code").textbox("getValue");
	        if (code == "") {
	            $("#code").textbox("showError", "[标准影片编码]不能为空！");
	            if (isok) {
	                parent.Tips.Error("[标准影片编码]不能为空！");
	            }
	            isok = false;
	        }
	        else if (code.length != 12) {
	            $("#code").textbox("showError", "[标准影片编码]必须为12个字符！");
	            if (isok) {
	                parent.Tips.Error("[标准影片编码]必须为12个字符！");
	            }
	            isok = false;
	        }
	        else {
	            $("#code").textbox("closeError");
	        }
	       
	        if(!isok){
	        	return false;
	        }
	        
		$.ajax({
            cache: true,
            type: "POST",
            url:"../accfilm/Editaccfilm.do",
            data:$('#info').serialize(),// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
               if(data.success){
            	   //$.messager.alert('信息提示','操作成功','success',null);
            	   //parent.refreshTab("添加用户");
            	   parent.datalist._temp.list_box.datagrid("reload");
            	   parent.Tips.Success("操作成功");
            	   Cancel();
               }else{
            	   parent.Tips.Error("操作失败");
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