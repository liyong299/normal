<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">标注</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
		<input id="id" name="id" value="${accCinema.id }"  type="hidden" />
		<input id="id" name="provider" value="${accCinema.provider }"  type="hidden" />   
        <div class="item"><label>标准影院编码：</label><easyui_ext:textBox id="code" name="code"  value="${accCinema.code }"/></div>
        <div class="item"><label>非标准影院编码：</label><easyui_ext:textBox id="extcinemacode" name="extcinemacode" readonly="true" value="${accCinema.extcinemacode }"  /></div>
        <div class="item"><label>影院名称：</label><easyui_ext:textBox id="name" name="name"  readonly="true" value="${accCinema.name }"  /></div>
        
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="doEdit()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>


<easyui:templateOverride name="script">
	<script>	   
	
	function doEdit() {
		$.ajax({
            cache: true,
            type: "POST",
            url:"../acccinema/Editacccinema.do",
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