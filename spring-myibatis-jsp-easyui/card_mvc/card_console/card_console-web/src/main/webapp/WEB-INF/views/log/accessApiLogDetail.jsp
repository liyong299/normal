<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">查看详情</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
		<span>
			<textarea id="expStackTrace" class="textbox-text validatebox-text" placeholder="" style="margin-left: 0px; margin-right: 0px; height:340px; width: 555px;">
接入商编号：${accessProviderNo}
接口名称：${apiName}
接口地址：${apiUrl}
入参：${inParam}
出参：${outParam}
描述：${memo}</textarea>
		</span>
	</fieldset>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>