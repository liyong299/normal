<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
    <easyui:layout id="main" fit="true">
       <form  id="updateForm"  class="edit_box"  action="" >
					<table align="center" width="480px" class="edit_tr">
						<tr>
							<td align="right">微信产品类型：</td>
							<td><input class="easyui-textbox" name="wxgoodstype"
								style="width: 60%; height: 25px" value=""></td>
						</tr>
						<tr>
							<td width="150" height="30" align="right">兜有影院编号：</td>
							<td><input class="easyui-textbox" name="cinemano" 	style="width: 60%; height: 25px" value="">
								<easyui:button id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="Popoutcinema()"></easyui:button></td>
						</tr>
						
						<tr>
							<td align="right">兜有商品ID：</td>
							<td><input name="goodsid" class="easyui-textbox"
								style="width: 60%; height: 25px" value=""></td>
						</tr>
						<tr>
							<td align="right">兜有商品类型：</td>
							<td><input name="goodstype" class="easyui-textbox"
								style="width: 60%; height: 25px" value=""></td>
						</tr>
					</table>
				        <div class="item item_row button">
				            <a class="easyui-linkbutton" onclick="doSave()">保存</a>
				            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
				        </div>
		</form>
		</easyui:layout>
		
		<div id="layout_box" style="overflow:hidden" data-options="width: 500,height: 400,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
			<easyui_ext:searchCondition id="search-form">	
				<easyui_ext:searchItem name="影院编号"><easyui_ext:textBox id="cinemano" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="cinemaname" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
			</easyui_ext:searchCondition>
			<easyui:datagrid id="datalist" url="../cinema/view.do" fit="true" pageSize="10" pagination="true" singleSelect="true" fitColumns="true">
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="Search()" />
					<easyui:button id="btn_check" text="选择" plain="true" iconCls="icon-add"  onclick="Check()" />		
					</easyui:facet>
					<easyui:columns>
				        <easyui:column field="ck" checkbox="true"/>
				        <easyui:column field="cinemano" title="影院编码"/>
				        <easyui:column field="cinemaname" title="影院名称"/>
				        <easyui:column field="address" title="影院地址"/>
			        </easyui:columns>
			</easyui:datagrid>
		</div>
		
</easyui:templateOverride>
<easyui:templateOverride name="script">
		<script>
		
		function Popoutcinema()
		{
			$('#layout_box').window({
	            title: "选择影院"
	        });
		}
		
		function Search()
		{
			 $('#datalist').datagrid('load', $(".search_box").serializeObject());
		}
		
		function Check()
		{
			var obj = $('#datalist').datagrid('getSelections')[0];
			if(obj == null){
				$.messager.alert('信息提示','请选择影院！','success',null);
				return;
			}
			$('#updateForm').form('load', {
				cinemano: obj.cinemano
                });
			$('#layout_box').window("close");
		}
		
	function doSave() {	
		$.ajax({
            cache: true,
            type: "POST",
            url:"../cgmapping/savewxgsmap.do",
            data:$('#updateForm').serialize(),// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
               if(data.success){
            	   $.messager.alert('信息提示','操作成功','success',function(){            		   
            	   });          	   
               }else{
            	   $.messager.alert('信息提示',data.errMsg);
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