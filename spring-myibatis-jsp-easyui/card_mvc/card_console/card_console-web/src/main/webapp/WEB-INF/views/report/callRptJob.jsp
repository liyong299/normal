<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">结算任务调用</easyui:templateOverride>
<easyui:templateOverride name="head">
</easyui:templateOverride>
<easyui:templateOverride name="body">
    <br>		
	<div>
	    <span>结算任务:</span><easyui:comboBox id="jobName"  style="width:220px" data="${reportJobs }"/>
	    <span>结算日期:</span><easyui:inputDate id="excuteDate" name="excuteDate"></easyui:inputDate>
	    <a class="easyui-linkbutton" onclick="excuteJob()">执行</a>
	</div>
	<br>
	<div>
	    <span>备注:（日结算任务，将结算所选择结算日期的数据，月结算任务，将结算所选择结算日期所在月份的数据）</span>
	</div>
	
</easyui:templateOverride>

<easyui:templateOverride name="script">
	<script>	
	    var flag = 0
	    function excuteJob(){
	    	if(flag == 1){
	    		alert("当前结算任务正在执行中，请稍后");
	    		return;
	    	}
	    	flag = 1;
	    	var jobName = $("#jobName").combobox("getText");
	    	var excuteDate = $("#excuteDate").datebox("getValue");
	    	if(jobName == ""){
	    		alert("请选择要执行的结算任务");
	    		return;
	    	}
	    	if(excuteDate == ""){
	    		alert("请选择执行任务日期");
	    		return;
	    	}
	    	$.ajax({
                type: 'post',
                dataType: 'json',
                url: "../rptJobDetail/callRptJob.do",
                data: { jobName: jobName, excuteDate: excuteDate },
                async: false,
                success: function (result) {
                	flag = 0;
                    if (result.isSucceed) {
                        alert("结算成功");
                    }
                    else {
                        alert(result.message);
                    }
                },
                error: function () {
                },
                complete: function () {
                }
            });
	    }
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>