<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加用户</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<form id="info" class="edit_box"  method="post" action="">
		<input id="templateid" type="hidden"  value=""/>
		<div class="item item_row"><label>模板标识：</label><easyui_ext:textBox id="templatekey"  readonly="readonly"/></div>
        <div class="item item_row"><label>模板名称：</label><easyui_ext:textBox id="templatename" /><span class="star">*</span></div>        
        <div class="item item_row"><label>定义模板：</label><easyui_ext:comboBox id="templatedefine"  data="${definelist}"/><span class="star">*</span></div>
        <div class="item item_row"><label>模板类型：</label><easyui_ext:comboBox id="templatetype" data="${typelist}"/><span class="star">*</span></div>
        <div class="item item_row"><label>业务类型：</label>
        <easyui_ext:comboBox id="templateclass" data="${classlist}" >
        	<easyui:eventListener  event="onSelect" listener="Select"/>
        </easyui_ext:comboBox></div>
        <div class="item item_row"><label>插入关键字：</label><div id="keyword1"></div> </div>
        <div class="item item_row"><label>短信内容：</label><easyui_ext:textBox id="templatecontent" height="200px" width="300px" multiline="true"/></div>
        <div class="item item_row"><label>插入关键字：</label><div id="keyword2"></div></div>
        <div class="item item_row"><label>彩信内容：</label><easyui_ext:textBox id="templatecontent1" height="200px" width="300px" multiline="true"/></div>
        <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		function Save(){
			var templateid = $("#templateid").val();
			var templatename = $("#templatename").textbox("getValue");
			if(templatename == ""){
				 $("#templatename").textbox("showError", "模板名不能为空！");
			}
			var templatekey = $("#templatekey").textbox("getValue");
			if(templatekey == ""){
				 $("#templatekey").textbox("showError", "模板标识不能为空！");
			}
			var templatedefine = $("#templatedefine").combobox("getValue");
			if(templatedefine == ""){
				 $("#templatedefine").combobox("showError", "定义模板不能为空！");
			}
			var templatetype = $("#templatetype").combobox("getValue");
			if(templatetype == ""){
				 $("#templatetype").combobox("showError", "模板类型不能为空！");
			}
			var templateclass = $("#templateclass").combobox("getValue");
			if(templateclass == ""){
				 $("#templateclass").combobox("showError", "动作不能为空！");
			}
			var templatecontent = $("#templatecontent").textbox("getValue");
			if(templatecontent == ""){
				 $("#templatecontent").textbox("showError", "短信内容不能为空！");
			}
			var templatecontent1 = $("#templatecontent1").textbox("getValue");
			if(templatecontent1 == ""){
				 $("#templatecontent1").textbox("showError", "彩信内容不能为空！");
			}
			
			var data={
						templateid:templateid,
						templatekey:templatekey,
						templatename:templatename,
						templatedefine:templatedefine,
						templatetype:templatetype,
						templateclass:templateclass,
						templatecontent:templatecontent,
						templatecontent1:templatecontent1
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
		function Select(record){
			var data ={templateclass:record.value?record.value:record} ;
			var htmls ="";
			$.ajaxRequest({
        		url: "queryTmplAttrList.do",
        		para: data,
        		success: function(result){
        			$.each(result,function(index,value){
        				htmls += "<a id='"+index+"'>"+value+"</a>";        				
        			});
        			
        			$("#keyword1").html(htmls); 
        			$("#keyword2").html(htmls); 
        		}
        	});
		   /* $("#keyword1").html("<a>["+record.text+"]</a>"); */
		}
		
		$(function(){
			$("#templateid").val("${baseBaseTemplate.templateid}");
			$("#templatename").textbox("setValue","${baseBaseTemplate.templatename}");
			$("#templatekey").textbox("setValue","${baseBaseTemplate.templatekey}");
			$("#templatecontent").textbox("setValue","${baseBaseTemplate.templatecontent}");
			$("#templatecontent1").textbox("setValue","${baseBaseTemplate.templatecontent1}");
			
			$("#keyword1 a").live("click",function(){				
				var content1 = $("#templatecontent").textbox("getValue");
				content1 +=$(this).attr("id")
				$("#templatecontent").textbox("setValue",content1);
			});
			$("#keyword2 a").live("click",function(){				
				var content2 = $("#templatecontent1").textbox("getValue");
				content2 +=$(this).attr("id")
				$("#templatecontent1").textbox("setValue",content2);
			});
			
			Select($("#templateclass").combobox("getValue"));
		});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>