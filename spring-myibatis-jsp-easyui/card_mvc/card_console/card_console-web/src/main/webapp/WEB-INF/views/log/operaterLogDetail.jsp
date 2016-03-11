<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">查看详情</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
		<legend>描述</legend>
		<span>
			<textarea id="memo" class="textbox-text validatebox-text" placeholder="" style="margin-left: 0px; margin-right: 0px; height:150px; width: 540px;">${memo}</textarea>
		</span>
	</fieldset>
	<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
		<legend>数据变更</legend>
		<span>
			<textarea id="dataChangeInfos" class="textbox-text validatebox-text" placeholder="" style="margin-left: 0px; margin-right: 0px; height: 250px; width: 540px;">
${dataChangeInfos}</textarea>
		</span>
	</fieldset>
</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp" %>