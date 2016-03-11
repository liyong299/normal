<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">	
	        	<easyui_ext:searchItem name="接入商编号"><easyui_ext:textBox id="accessproviderno" value=""  /></easyui_ext:searchItem>
	        	<easyui_ext:searchItem name="接口名称"><easyui_ext:textBox id="apiname" value=""  /></easyui_ext:searchItem>
	        	<easyui_ext:searchItem name="描述"><easyui_ext:textBox id="memo" value=""  /></easyui_ext:searchItem><br/>	      
	        	<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="logstatus" url="getvstate.do" method="get" valueField="vcode"  textField="vtext" ></easyui_ext:comboBox></easyui_ext:searchItem>
				<easyui_ext:searchItem name="记录时间"><easyui:inputDate id="logdatefrom"></easyui:inputDate><span>到</span><easyui:inputDate id="logdateto"></easyui:inputDate></easyui_ext:searchItem>
	    </easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="getacclist.do" fit="true" pageSize="30" pagination="true" singleSelect="true" method="get">
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search" onclick="btnsearch()" />			       
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="logid" title="ID" width="50px"/>
			        <easyui:column field="accessproviderno" title="接入商编号" width="120px"/>
			        <easyui:column field="apiname" title="接口名称" width="150px" />
			        <easyui:column field="apiurl" title="接口地址" width="270px" />
			        <easyui:column field="memo" title="描述"  width="150px"/>			      
			        <easyui:column field="logstatus" title="状态"  width="80px"/>
			        <easyui:column field="rectime" title="记录时间"  width="150px"/>
			        <easyui:column field="_handler" title="操作"  formatter="LookDetail" />
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	<easyui:window id="logdetais" style="width:488px;height:450px;width: 488px; left: 339px; top: 150px;"  title="详情" modal="true" closed="true">	
		
	<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
        <legend>接口地址</legend>         
         <textarea class="textbox-text validatebox-text"  placeholder="" style="margin-left: 0px; margin-right: 0px; height: 40px; width: 460px;" name="apiurl" id="apiurl">
         </textarea>
    </fieldset>
		
		
	<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
			<legend>接口描述</legend>
			<textarea class="textbox-text validatebox-text"  placeholder="" style="margin-left: 0px; margin-right: 0px; height: 60px; width: 460px;" name="apimemo" id="apimemo">
         	</textarea>
		</fieldset>
		<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
			<legend>入参</legend>
			<textarea class="textbox-text validatebox-text"  placeholder="" style="margin-left: 0px; margin-right: 0px; height: 80px; width: 460px;" name="apiinparam" id="apiinparam">
         	</textarea>
		</fieldset>
		<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
			<legend>出参</legend>
			<textarea class="textbox-text validatebox-text"  placeholder="" style="margin-left: 0px; margin-right: 0px; height: 90px; width: 460px;" name="apioutparam" id="apioutparam">
         	</textarea>
		</fieldset>
	</easyui:window>
	
</easyui:templateOverride>

<easyui:templateOverride name="script">
	<script type="text/javascript">
		function btnsearch(){			
			$('#datalist').datagrid('load', $(".search_box").serializeObject());			
		}		
		function LookDetail(value, rowData, index){
			return '<a href="javascript:void(0)" onclick="showDetail('+rowData.logid+');">查看</a>';
		}		
		function showDetail(logid)
		{			
	        var url = '../syslogs/getacclogdetail.do';
	        $.get(url, { 'logid': logid, '_': Math.random() }, function (data) {	        	
	            	var result = eval('(' + data + ')');	
	            	if(result)
	            		{
		            		$("#apiurl").val( result.apiurl)
			                $("#apimemo").val( result.memo)
			                $("#apiinparam").val(result.inparam)
			                $("#apioutparam").val(result.outparam)
	            			$("#logdetais").window('open');
	            		}
	        });

		}
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>