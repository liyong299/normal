<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
		       <easyui_ext:searchItem name="接入商"><easyui_ext:comboBox id="provider" panelMaxHeight="250"    data="${providerTypeList}" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="标准影片编码"><easyui_ext:textBox id="code"  value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="非标准影片编码"><easyui_ext:textBox id="extfilmcode"  value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影片名称"><easyui_ext:textBox id="name"  value=""></easyui_ext:textBox></easyui_ext:searchItem>	    
		</easyui_ext:searchCondition>	    
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="view.do" fit="true" pageSize="10" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
				<easyui_ext:facetButton id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="datalist.Search()" />			        
			    </easyui:facet>
			    <easyui:columns frozen="true">
			   		<easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="id" title="ID"/>
<%-- 			        <easyui:column field="provider" title="接入商类型"/> --%>
			        <easyui:column field="busnames" title="接入商名称"/>
                    <easyui:column field="extcinemacode" title="影院编码"/>
			        <easyui:column field="code" title="标准影片编码"/>
			        <easyui:column field="extfilmcode" title="非标准影片编码" />
			        <easyui:column field="name" title="影片名称"/>
			        <easyui:column field="showtypes" title="放映类型"  />
			    </easyui:columns>
			    <easyui:columns>
<%-- 			        <easyui:column field="duration" title="时长（分钟）"  /> --%>
      <easyui:column field="publishdate" title="上映日期"/> 
<%-- 			        <easyui:column field="publisher" title="发行商"/> --%>
<%-- 			        <easyui:column field="director" title="导演" /> --%>
<%-- 			        <easyui:column field="intro" title="简介"/> --%>
			        
<%-- 			        <easyui:column field="country" title="发行国家"/> --%>
<%-- 			        <easyui:column field="type" title="影片类型"/> --%>
<%-- 			        <easyui:column field="language" title="影片语言"/> --%>
<%-- 			        <easyui:column field="poster" title="海报"  /> --%>
<%-- 			        <easyui:column field="stills" title="剧照"/> --%>
<%-- 			        <easyui:column field="highlight" title="精彩看点"/> --%>
			        <easyui_ext:formatterColumn title="操作" param="value, rowData, index">
			        	<easyui_ext:handlerColumn text="编辑" onclick="datalist.Edit" param="rowData.id" />			        	
			        </easyui_ext:formatterColumn>														      
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
		<div id="layout_box" style="overflow:hidden" data-options="width: 400,height: 250,closed: false,cache: false,modal: true,minimizable:false,maximizable:true,collapsible:false"><iframe frameborder="0" width="100%" height="100%"></iframe></div>
	</easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = datalist;
	
	            _this._temp.list_box = $("#datalist");
	            _this._temp.layout_box = $("#layout_box");
	        },
	        _temp: {
	        	list_box: null,
	        	layout_box: null
	        },
	        Search: function () {
	            $('#datalist').datagrid('load', $(".search_box").serializeObject());
	        },	       
	        Deleteasyui: function(){
	        },
	        Edit:function(id){
	        	$("#layout_box iframe").attr("src", '../accfilm/accfilmedit.html?id=' + id);
	            $('#layout_box').window({
	                title: "编辑影片信息"
	            });
	        }
		};
		
		$(function(){
			datalist.Init();
		});
		
	</script>
	</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>