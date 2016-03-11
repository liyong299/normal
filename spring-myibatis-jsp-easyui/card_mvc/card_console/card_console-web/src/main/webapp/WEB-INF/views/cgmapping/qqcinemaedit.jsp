<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
     <easyui:layout id="main" fit="true">
       <form  id="updateForm"  class="edit_box"  action="" >
       				<input type="hidden" name="id" value="${qcmmap.id }"/>
					<table align="center" width="480px" class="edit_tr">
						<tr>
							<td width="242" height="30" align="right">QQ影院编号：</td>
							<td><input class="easyui-textbox" name="qqcinemano"
								style="width: 60%; height: 25px" value="${qcmmap.qqcinemano}"></td>
						</tr>
						<tr>
							<td width="242" height="30" align="right">兜有影院编号：</td>
							<td><input class="easyui-textbox" name="cinemano"
								style="width: 60%; height: 25px" value="${qcmmap.cinemano}">
								<easyui:button id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="Popoutcinema()"></easyui:button></td>
						</tr>
						<tr>
							<td align="right">影院名称：</td>
							<td><input class="easyui-textbox" name="cinemaname"
								style="width: 60%; height: 25px" value="${qcmmap.cinemaname}"></td>
						</tr>
						<tr>
							<td align="right">院线名称：</td>
							<td><input name="cinemalinename" class="easyui-textbox"
								style="width: 60%; height: 25px" value="${qcmmap.cinemalinename}"></td>
						</tr>
						<tr>
							<td align="right">省份名称：</td>
							<td><input name="provinceno" class="easyui-textbox"
								style="width: 60%; height: 25px" value="${qcmmap.provinceno}"></td>
						</tr>
						<tr>
							<td align="right">城市名称：</td>
							<td><input name="cityno" class="easyui-textbox"
								style="width: 60%; height: 25px" value="${qcmmap.cityno}"></td>
						</tr>
						<tr>
						<tr>
							<td align="right">辖区名称：</td>
							<td><input class="easyui-textbox" name="areano"
								style="width: 60%; height: 25px" value="${qcmmap.areano}"></td>
						</tr>
						<tr>
							<td width="20%" align="right">联系电话：</td>
							<td><input class="easyui-textbox" name="telephone"
								style="width: 60%; height: 25px" value="${qcmmap.telephone}"></td>
						</tr>
						<tr>
							<td align="right">影院地址：</td>
							<td><input name="address" class="easyui-textbox"
								style="width: 80%; height: 40px" value="${qcmmap.address}"></td>
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
				        <easyui:column field="cinemaname" title="影院名称" width="200px"/>
				         <easyui:column field="cinemalinename" title="所属院线"/>
			        	<easyui:column field="province" title="所属省" hidden="true"/>
			        	<easyui:column field="city" title="所属市" hidden="true"/>
			        	<easyui:column field="county" title="所属区" hidden="true"/>
				        <easyui:column field="address" title="影院地址" hidden="true"/>
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
				cinemano: obj.cinemano,
				cinemaname:obj.cinemaname,
				cinemalinename:obj.cinemalinename,
				provinceno:obj.province,
				cityno:obj.city,
				areano:obj.county,
				address:obj.address
                });
			$('#layout_box').window("close");
		}
		
		
	function doSave() {	
		$.ajax({
            cache: true,
            type: "POST",
            url:"../cgmapping/saveqqcmmap.do",
            data:$('#updateForm').serialize(),// 你的formid
            dataType: "json",
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
               if(data.success){
            	   $.messager.alert('信息提示','操作成功','success',function(){
            		   //parent.refreshTab("影院管理");
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