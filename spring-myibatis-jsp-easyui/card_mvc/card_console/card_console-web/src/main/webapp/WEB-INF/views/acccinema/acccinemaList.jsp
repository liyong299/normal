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
				<easyui_ext:searchItem name="标准影院编码"><easyui_ext:textBox id="code"  value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="非标准影院编码"><easyui_ext:textBox id="extcinemacode"  value=""></easyui_ext:textBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="name"  value=""></easyui_ext:textBox></easyui_ext:searchItem>	    
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
			        <easyui:column field="code" title="标准影院编码"/>
			        <easyui:column field="extcinemacode" title="非标准影院编码"/>
			         <easyui:column field="name" title="影院名称" />
			        <easyui:column field="hallCount" title="影厅数量"/>
			    </easyui:columns>
			    <easyui:columns>
<%-- 			        <easyui:column field="province" title="省份"  /> --%>
<%-- 			        <easyui:column field="city" title="城市"/> --%>
<%-- 			        <easyui:column field="county" title="辖区"/> --%>
<%-- 			        <easyui:column field="countycode" title="辖区编码" /> --%>
<%-- 			        <easyui:column field="address" title="影院地址"/> --%>
<%-- 			        <easyui:column field="logo" title="影院logo"  /> --%>
<%-- 			        <easyui:column field="url" title="影院网址"/> --%>
<%-- 			        <easyui:column field="tel" title="客服电话"/> --%>
<%-- 			        <easyui:column field="devicepos" title="终端位置说明"/> --%>
<%-- 			        <easyui:column field="deviceimg" title="终端图片说明"  /> --%>
<%-- 			        <easyui:column field="grade" title="综合评分"/> --%>
<%-- 			        <easyui:column field="intro" title="简介"/> --%>
<%-- 			         <easyui:column field="busLine" title="公交线路"/> --%>
<%-- 			        <easyui:column field="longitude" title="经度"  /> --%>
<%-- 			        <easyui:column field="latitude" title="纬度"/> --%>
<%-- 			        <easyui:column field="feature" title="特色"/>	 --%>
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
	        	$("#layout_box iframe").attr("src", '../acccinema/acccinemaedit.html?id=' + id);
	            $('#layout_box').window({
	                title: "编辑影院信息"
	            });
	        }
		};
		
		$(function(){
			datalist.Init();
		});
		
	</script>
	</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>