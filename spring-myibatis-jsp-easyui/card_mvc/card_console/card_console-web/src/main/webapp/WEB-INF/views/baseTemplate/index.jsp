<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">模板管理</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="valid_query_main"  fit="true">
		<easyui_ext:searchCondition id="search_box" >
			<easyui_ext:searchItem name="模板名称"><easyui_ext:textBox id="templatename"  name="templatename" value=""></easyui_ext:textBox></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center"  border="false">
			<easyui:datagrid id="datalist" striped="true" rownumbers="true" url="queryList.do" fit="true" pageSize="30" pagination="true"  singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui_ext:facetButton id="searchBtn" text="查询" plain="true" iconCls="icon-search"  onclick="datalist.Search()"/>
					<easyui_ext:facetButton id="addBtn" text="添加" plain="true" iconCls="icon-add"  onclick="datalist.Add()"/>					
			    </easyui:facet>
			   <easyui:columns frozen="true">
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="templateid" width="50" title="模板编号"/>			        
			        <easyui:column field="templatename" width="80" title="模板名称"/>
			        <easyui:column field="templatekey" width="80" title="模板标识"/>
			        <easyui:column field="templatetypename" width="80" title="模板类型"/>
			         <easyui:column field="templateclassname" width="80" title="业务类型"/>
			        <easyui:column field="templatedefine" width="80" title="模板属性" formatter="function(value,row,index){
			        if(value =='0'){
			        	return '默认';
			        }else if(value=='1'){
			        	return '自定义';
			        }
			        }"/>			        
			    </easyui:columns>
			    <easyui:columns>
			   		<easyui:column field="templatecontent" width="200" title="短信内容"/>
			        <easyui:column field="templatecontent1" width="200" title="彩信内容" />
			        <easyui:column field="status" width="80" title="模板状态" formatter="function(value,row,index){
			        if(value =='0'){
			        	return '为审核';
			        }else if(value=='1'){
			        	return '已审核';
			        }}"/>
			        <easyui:column field="createtime" width="80" title="创建时间"/>			       	       
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        <easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="'rowData.templateid'" />
			        <easyui_ext:handlerColumn text="删除" onclick="datalist.Delete" param="'rowData.templateid'" />
			        </easyui_ext:formatterColumn>
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
	<div id="layout_box" style="overflow:hidden" data-options="width: 600,height: 600,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false">
		<iframe frameborder="0" width="100%" height="100%"></iframe>
	</div>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	var datalist = {
			_temp: {	        	
	        	layout_box: null,
	        	list_box:null
	        },
			Init:function(){
				var _this = datalist;
				  _this._temp.layout_box = $("#layout_box");
				  _this._temp.list_box =  $('#datalist');
			},
	        Search: function () {	        	
	            $('#datalist').datagrid('load', $("#search_box").serializeObject());
	        },
	        Add:function(id){//添加
	        	$("#layout_box iframe").attr("src", "add.html");
	            $('#layout_box').window({
	                title: "添加模板"
	            });
	        },
	        Edit:function(id){//编辑	        	
	        	$("#layout_box iframe").attr("src", "edit.html?templateid="+id);
	            $('#layout_box').window({
	                title: "添加模板"
	            });
	        },
	        Delete:function(id){//删除
	        	$.messager.confirm("消息提醒","您确认删除该条模板记录吗？",function(r){
	        		if(r){
	        			$.ajaxRequest({
	    	        		url: "delete.do",
	    	        		para: {templateid:id},
	    	        		success: function(result){
	    	        			if(result.isSuccess){
	    	        				datalist._temp.list_box.datagrid("reload");
	    		        			Tips.Success("成功");	    		        			
	    		        		}
	    	        			else{
	    	        				Tips.Error(result.message);
	    	        			}
	    	        		}
	    	        	});
	        		}
	        	});
	        	
	        },
	        formatter: function(value, rowData, index){
	        	var html = "";         	
	        	
	        	html += "<a class=\'g-mlr-5\' onclick=\'datalist.Edit(" + rowData.templateid + ")\'>编辑</a>";
	        	html += "<a class=\'g-mlr-5\' onclick=\'datalist.Delete(" + rowData.templateid + ")\'>删除</a>";
	        	
	        	return html;
	        } 
	};
	
	$(function(){
		datalist.Init();
	});
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>