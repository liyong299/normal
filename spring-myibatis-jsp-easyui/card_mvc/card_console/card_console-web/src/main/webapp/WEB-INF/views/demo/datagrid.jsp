<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">
				<easyui_ext:searchItem name="所属院线"><easyui_ext:comboBox id="inputtext1" width="<%= com.mopon.helpers.ui.combobox.Width.SHORT %>" data="<%= com.mopon.helpers.ui.combobox.Data.getDemoList() %>" /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院编号"><easyui_ext:textBox id="inputtext2" width="<%= com.mopon.helpers.ui.textbox.Width.SHORT %>" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院名称"><easyui_ext:textBox id="inputtext3" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="接入商"><easyui_ext:comboBox id="inputtext4" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="影院状态"><easyui:comboBox id="inputtext5" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="启售状态"><easyui:comboBox id="inputtext6" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="业务类型"><easyui:comboBox id="inputtext7" value=""  /></easyui_ext:searchItem>
				<easyui_ext:searchItem name="所在区域"><easyui_ext:textBox id="inputtext8" width="<%= com.mopon.helpers.ui.textbox.Width.LONG %>" value=""  /></easyui_ext:searchItem>
		</easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="getlist.do" fit="true" pageSize="30" pagination="true" singleSelect="true" >
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search" />
			        <easyui:button id="btn_add" text="添加" plain="true" iconCls="icon-add" />
			        <easyui:button id="btn_enable" text="启用" plain="true" iconCls="icon-tick" />
			        <easyui:button id="btn_disable" text="禁用" plain="true" iconCls="icon-stop" />
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="id" title="ID"/>
			        <easyui:column field="name" title="名称"/>
			        <easyui:column field="age" title="年龄"/>
			        <easyui:column field="address" title="地址"/>
			        <easyui:column field="_handler" title="操作" formatter="function(value, rowData, index){return '<a>查看</a>';}" />
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
		var datalist = {
			Init: function () {
	            var _this = List;
	
	            _this._temp.list_box = $("#list-grid");
	            _this._temp.layout_box = $("#layout_box");
	            _this._temp.layout_productbox = $("#layout_productbox");
	        },
	        Search: function () {
	            $('#list-grid').datagrid('load', $(".search_box").serializeObject());
	        },
	        Add: function(){
	        },
	        Deleteasyui: function(){
	        }
		};
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>