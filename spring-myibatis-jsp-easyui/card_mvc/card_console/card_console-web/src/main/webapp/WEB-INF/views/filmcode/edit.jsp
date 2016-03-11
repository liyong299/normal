<%@taglib prefix="easyui" uri="org.topteam/easyui"%>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<div>
		<form id="saveForm" method="post" action="<%=request.getContextPath()%>/film/edit.do">
			 <input name="id" class="easyui-validatebox"  type="hidden"  value="${Film.id }"  />
			<div>
				<label style="width: 100px" for="itemName">非标准影片编码：<em>*</em></label>
				<input name="filmno" class="easyui-validatebox" readonly="true"
					type="text"  value="${Film.filmno }"  validType="length[0,32]" />
			</div>
			<div>
				<label for="itemName">影片名称 ：<em>*</em></label> <input
					name="filmname" class="easyui-validatebox" readonly="true"
					type="text"  value="${Film.filmname }" validType="length[0,32]" />
			</div>
			<div>
				<label for="itemName">上映时间：<em>*</em></label> <input
					name="publishdate" class="easyui-validatebox"  readonly="true"
					type="text"  value="${Film.publishdate }" validType="length[0,32]" />
			</div>
			<div>
				<label for="itemName">上映类型：<em>*</em></label> <input
					name="showtype" class="easyui-validatebox" readonly="true"
					type="text"  value="${Film.showtype }" validType="length[0,32]" />
			</div>
			<div>
				<label for="itemName">标准影片编码：<em>*</em></label> <input
					name="normcode" class="easyui-validatebox" 
					type="text"  value="${Film.normcode }" validType="length[0,32]" />
			</div>
			<div>
				<label for="itemName">接入商类型：<em>*</em></label> <input
					name="provider" class="easyui-validatebox"  readonly="true"
					type="text"  value="${Film.provider }" validType="length[0,32]" />
			</div>
			<div>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<easyui:button id="text-btn1" text="保存" onclick="doSave()" />
				&nbsp;&nbsp;
				<easyui:button id="text-btn2" text="取消" onclick="close()"/>
			</div>
		</form>
	</div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	function doSave() {	
		$.ajax({
            cache: true,
            type: "POST",
            url:"../film/edit.do",
            data:$('#saveForm').serialize(),// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
               if(data.success){
            	   //$.messager.alert('信息提示','操作成功','success',null);
            	   //parent.refreshTab("添加用户");
            	   parent.Tips.Success("操作成功");
            	   parent.datalist._temp.layout_box.window("close");
               }else{
            	   parent.Tips.Error("操作失败");
               }            
            }
        });
	}
	</script>
</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp"%>