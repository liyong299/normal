<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">				
				<easyui_ext:searchItem name="兜有商品编号"><easyui_ext:textBox id="goodsid" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="兜有影院编号"><easyui_ext:textBox id="cinemano" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="兜有商品类型"><easyui_ext:textBox id="goodstype" value="" ></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="QQ产品类型"><easyui_ext:textBox id="qqgoodstype" value="" ></easyui_ext:textBox></easyui_ext:searchItem>				
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="qqgmplist.do" fit="true" pageSize="30" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()" />		
			        <easyui:button id="btn_add" text="添加" plain="true" iconCls="icon-add" onclick="datalist.Add()"></easyui:button>
			        <easyui:button id="btn_edit" plain="true" text="编辑" iconCls="icon-edit"  onclick="editQQgsmap()"></easyui:button>
			        <easyui:button id="btn_enable" text="删除" plain="true" iconCls="icon-delete" onclick="removeQQgsmap()"></easyui:button>
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="id" title="ID"/>
			        <easyui:column field="goodsid" title="兜有商品编号"  width="100px"/>
			        <easyui:column field="goodsname" title="兜有商品名称"  width="100px"/>
			        <easyui:column field="goodstype" title="兜有商品类型"  width="80px"/>
			        <easyui:column field="cinemano" title="兜有影院编号" width="100px"/>
			         <easyui:column field="cinemaname" title="兜有影院名称" width="100px"/>			        
			        <easyui:column field="qqgoodstype" title="QQ产品类型" width="100px"/>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
	<div id="layout_box" style="overflow:hidden" data-options="width: 600,height: 450,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
		<iframe frameborder="0" width="100%" height="100%"></iframe>
	</div>
	
	
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var datalist = {
			Init: function () {
				 	var _this = List;		            
		            _this._temp.layout_box = $("#layout_box");
	        },
	        Search: function () {
	        	//$.messager.alert("Pop info","you click Search!")
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	        	$("#layout_box iframe").attr("src", "../cgmapping/qqgoodsadd.html");
	            $('#layout_box').window({
	                title: "添加QQ产品映射"
	            });
	        },
	        Deleteasyui: function(){
	        }
		};
	
	function removeQQgsmap()
	{
		var obj = $('#datalist').datagrid('getSelections')[0];
		if(obj == null){
			$.messager.alert('信息提示','请选择要删除的QQ产品映射信息！','success',null);
			return;
		}
		var id = obj.id;
		
		$.messager.confirm("提示","确认删除该QQ产品映射吗？",function(data){
			if(data){				
				$.ajax({
					type: "POST",
					data:"id="+id,
					url:"../cgmapping/deleteqqgoods.do" ,			            
		            dataType: "json",
		            async: false,
		            error: function(request) {
		                $.messager.alert("Connection error");
		            },
		            success: function(data) {
		            	if(data.success){
			            	   $.messager.alert('信息提示','操作成功','success',function(){
			            		   //parent.refreshTab("QQ产品映射");
			            	   });				            	  
			               }else{
			            	   $.messager.alert(data.errMsg);
			               }		            	
		            }
				});
				
			}
		});
		
	}
	
	function editQQgsmap()
	{
		var obj = $('#datalist').datagrid('getSelections')[0];
		if(obj == null){
			$.messager.alert('信息提示','请选择要编辑的QQ产品映射信息！','success',null);
			return;
		}
		var id = obj.id;
		
		$("#layout_box iframe").attr("src", "../cgmapping/qqgoodsedit.html?id=" + id);
        $('#layout_box').window({
            title: "编辑QQ产品映射"
        });			
	}
	
	
	</script>
</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp" %>