<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">凭证管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
<form id="info" class="edit_box"  method="post" action="">
	 <div class="item"><label>序号：</label><easyui_ext:textBox id="id"  readonly="false"/></div>
        <div class="item"><label>凭证号：</label><easyui_ext:textBox id="voucherno"  readonly="false" /></div>
        <div class="item"><label>状态：</label><easyui_ext:textBox id="status"   readonly="false"/></div>
        <div class="item"><label>有效期始：</label><easyui_ext:textBox id="begintime"  readonly="false"/></div>
        <div class="item"><label>有效期止：</label><easyui_ext:textBox id="endtime"  readonly="false"/></div>
        <div class="item"><label>延期到：</label><easyui:inputDate id="delaytime"></easyui:inputDate> </div>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
</form>
</easyui:templateOverride>

<easyui:templateOverride name="script">
 <script>
 $(function(){
		$("#id").textbox("setValue", "${id}");
		$("#voucherno").textbox("setValue", "${voucherno}");
		$("#status").textbox("setValue", "${status}");
		$("#begintime").textbox("setValue", "${begintime}");
		$("#endtime").textbox("setValue", "${endtime}");
		
	});
 
 function Save(){
	 var id = $("#id").val();
	 var endtime = $("#delaytime").textbox("getValue");
	 
	 var data = {
			 id:id,
			 endtime:endtime
	 }
	 
	 $.ajaxRequest({
 		url: "delayVoucher.do",
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