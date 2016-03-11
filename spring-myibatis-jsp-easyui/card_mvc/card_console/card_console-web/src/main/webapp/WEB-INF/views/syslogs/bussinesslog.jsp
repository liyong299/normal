<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
	<easyui:layout id="main" fit="true">
		<easyui_ext:searchCondition id="search-form">				
	        	<easyui_ext:searchItem name="订单号"><easyui_ext:textBox id="orderno" value=""  /></easyui_ext:searchItem>
	        	<easyui_ext:searchItem name="凭证号"><easyui_ext:textBox id="vouchercode" value=""  /></easyui_ext:searchItem>
	        	<easyui_ext:searchItem name="影院编号"><easyui_ext:textBox id="cinemano" value=""  /></easyui_ext:searchItem><br/>	      
	        	<easyui_ext:searchItem name="状态"><easyui_ext:comboBox id="logstatus" url="getvstate.do" method="get" valueField="vcode"  textField="vtext" ></easyui_ext:comboBox></easyui_ext:searchItem>
	        	<easyui_ext:searchItem name="记录时间"><easyui:inputDate id="logdatefrom"></easyui:inputDate><span>到</span><easyui:inputDate id="logdateto"></easyui:inputDate></easyui_ext:searchItem>
	    </easyui_ext:searchCondition>
		<easyui:layoutUnit region="center" border="false">
			<easyui:datagrid id="datalist" url="getbuslist.do" fit="true" pageSize="30" pagination="true" singleSelect="true" method="get">
				<easyui:facet name="toolbar">
					<easyui:button id="btn_search" text="查询" plain="true" iconCls="icon-search"  onclick="btnsearch()"/>			       
			    </easyui:facet>
			    <easyui:columns>
			        <easyui:column field="ck" checkbox="true"/>
			        <easyui:column field="logid" title="ID" width="50px"/>
			        <easyui:column field="biztype" title="业务类型" width="120px"/>
			        <easyui:column field="bizstep" title="业务步骤" width="120px" />
			        <easyui:column field="orderno" title="订单编号"  width="150px"/>
			        <easyui:column field="vouchercode" title="凭证编号"  width="150px"/>
			        <easyui:column field="mobileno" title="手机号码"  width="120px"/>
			        <easyui:column field="cinemano" title="影院编号"  width="100px"/>
			        <easyui:column field="logstatus" title="状态"  width="80px"/>
			        <easyui:column field="rectime" title="记录时间"  width="150px"/>			        
			        <easyui:column field="_handler" title="操作" formatter="LookDetail" />
			    </easyui:columns>
			</easyui:datagrid>
		</easyui:layoutUnit>
	</easyui:layout>
	
	<easyui:window id="logdetais" style="width:488px;height:450px;width: 488px; left: 339px; top: 150px;"  title="详情" modal="true" closed="true">	
		
	<fieldset style="border: solid 1px; border-color: rgba(0, 0, 0, 0.18);">
        <legend>详情</legend>         
         <textarea class="textbox-text validatebox-text"  placeholder="" style="margin-left: 0px; margin-right: 0px; height: 380px; width: 450px;" name="logmemo" id="logmemo">
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
			 var url = '../syslogs/getbusslogdetail.do';
		        $.get(url, { 'logid': logid, '_': Math.random() }, function (data) {	        	
		            	var result = eval('(' + data + ')');	
		            	if(result)
		            		{			            		
				                $("#logmemo").val( result.memo);				                
		            			$("#logdetais").window('open');
		            		}
		        });
		}
	</script>
</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp" %>