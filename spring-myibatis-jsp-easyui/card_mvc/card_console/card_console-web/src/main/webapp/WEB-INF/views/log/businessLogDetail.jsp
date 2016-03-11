<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">查看详情</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
		<legend>描述信息</legend>
		<span>
			<textarea id="expStackTrace" class="textbox-text validatebox-text" placeholder="" style="margin-left: 0px; margin-right: 0px; height:300px; width: 555px;">${memo}</textarea>
		</span>
	</fieldset>
	<%-- <fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
		<legend>错误来源</legend>
		<span>
			<textarea id="expStackTrace" class="textbox-text validatebox-text" placeholder="" style="margin-left: 0px; margin-right: 0px; height: 430px; width: 540px;">
						${expStackTrace}
			</textarea>
		</span>
	</fieldset> --%>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>