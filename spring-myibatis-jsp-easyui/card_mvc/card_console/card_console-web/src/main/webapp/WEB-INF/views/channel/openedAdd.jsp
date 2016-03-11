<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

<easyui:templateOverride name="title">添加渠道关联影院</easyui:templateOverride>
<easyui:templateOverride name="head">
<style type="text/css">
    .fieldset{
    	width: 95%; margin:0 auto; margin-top:2px; border:1px dashed #AAA;
    } 
    
    .edit_box2 {overflow:hidden;padding-top:0px;padding-bottom: 25px }
	.edit_box2 .item { float: left; margin: 5px 0; width: 350px; min-height: 24px; line-height: 22px }
	.edit_box2 .item_row { width: 100% }
	.edit_box2 .item label:first-child { float: left; padding-right: 5px; width: 130px; line-height: 22px; display: inline-block; text-align: right }
	.edit_box2 .item > span.text { float: left; line-height: 20px }
	.edit_box2 .item span.star { margin-left: 5px; line-height: 22px; color: #c00 }
	.edit_box2 .button { position: fixed; box-sizing: border-box; bottom: 0; margin: 0; padding: 5px; background-color: #e6e6e6; border-top: 1px solid #d4d4d4; text-align: right }
	.edit_box2 .button span.message { float:left; color:#c00 }
	.edit_box2 .button a { min-width: 50px; }
	.edit_box2 .text-error { background-color: #fff3f3; border-color: #ffa8a8; color: #000; }
	.edit-box2{overflow:hidden}
	.edit-box2 .bottom{box-sizing: border-box; bottom: 0; margin: 0; padding: 5px; background-color: #e6e6e6; border-top: 1px solid #d4d4d4; text-align: right }
	.edit-box2  .item { float: left; margin: 5px 0; width: 350px; min-height: 24px; line-height: 22px }
	.edit-box2 .item_row { width: 100% }
	.edit-box2 .item label:first-child { float: left; padding-right: 5px; width: 130px; line-height: 22px; display: inline-block; text-align: right }
	.edit-box2 .text-error { background-color: #fff3f3; border-color: #ffa8a8; color: #000; }
	   
	table.gridtable {
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
	}
	table.gridtable th {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #dedede;
	}
	table.gridtable td {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #ffffff;
	}
		       
</style>  
</easyui:templateOverride>
<easyui:templateOverride name="body">
    <form  id="addCinemaForm" class="edit_box2"  action="../channel/savaCinema.do">
    
        <div>
   			<input id="channelno" name="channelno" type="hidden" value="${channelno}" />
		</div>
		
		<div id="tb" style="padding:5px;height:auto;background-color:#e6e6e6;">
			<div style="margin-bottom:5px">
				区域：<input id="provinceno" name="provinceno" /> <input id="cityno" name="cityno" /><input id="countyno" name="countyno" />
			</div>
			<div>
				影院编码:<input id="cinemano" class='easyui-textbox' style='width: 200px;' >	
				影院名称:<input id="cinemaname" class='easyui-textbox' style='width: 200px;'>
				<a href="#" id="btnSearch" class="easyui-linkbutton" iconCls="icon-search" onclick="Search()">查询</a>
			</div>
		</div>
		
         <div id="dataTable" style="height:450px;overflow: auto;">
<!--        	    <table class="gridtable"> -->
<!--        	    	<tr> -->
<!--        	    		<th width="30">序号</th> -->
<!--        	    		<th width="30"><input id="checkall"  name="checkall"    type="checkbox"  onclick="SelectAll()"/></th> -->
<!--        	    		<th>影院编码</th> -->
<!--        	    		<th>影院名称</th> -->
<!--        	    	</tr> -->
<%--        	    	<c:forEach items="${baseCinemaList}" var="baseCinemaList"> --%>
<!--         	    <tr> -->
<!--         	    	<td></td> -->
<%--         	    	<td width="30"><input id="cinemano_${baseCinemaList.cinemano}"  name="cinemano"  value=" ${baseCinemaList.cinemano}"   type="checkbox" /></td> --%>
<%-- 					<td>${baseCinemaList.cinemano} </td> --%>
<%--         	    	<td>${baseCinemaList.cinemaname} </td> --%>
<!--         	    </tr> -->
<%--         	    </c:forEach> --%>
<!--        	    </table> -->
        </div>
			
         <div class="item item_row button">
            <a class="easyui-linkbutton" onclick="Save()">保存</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script src="<%=request.getContextPath()%>/resources/scripts/common/area.js"></script>
	<script>
	$(Area.Init("provinceno", "cityno", "countyno"));
	var data=${baseCinemaList};
	var checkedCinemano='';
	
	//table加载
	$(function(){
// 		createShowingTable(data);   
 		loadChannelCinema();
	});
	
	function createShowingTable(data){
		  $("#dataTable").html('');  
		  //获取后台传过来的jsonData,并进行解析
		  var dataArray = data;//$.parseJSON(data.jsonData);
		  //此处需要让其动态的生成一个table并填充数据
		  var tableStr = "<table  class='gridtable'>";
		  tableStr = tableStr + "<tr><th width='30'>序号</th>	<th width='30'><input id='checkall'  name='checkall' type='checkbox'  onclick='SelectAll()'/></th> <th>影院编码</th> <th>影院名称</th></tr>";
		  var len = dataArray.length;
		  for(var i=0 ;i<len ; i++){	
			  var count= i+1;
			  tableStr = tableStr + "<tr><td>"+ count +"</td>"+
			  							"<td width='30'><input id='cinemano_"+dataArray[i].cinemano+"'  name='cinemano'  value='" +dataArray[i].cinemano+"'   type='checkbox' onclick='singleCheck(this);'/></td>"+
			  							"<td>"+dataArray[i].cinemano + "</td>"+"<td>"+dataArray[i].cinemaname +"</td></tr>";	 
			  }
			  tableStr = tableStr + "</table>";
			  //将动态生成的table添加的事先隐藏的div中.
			  $("#dataTable").html(tableStr);  
	 }
	
	//选中改渠道已关联的影院
	function loadChannelCinema()
	{
		var baseChannelCinema = ${baseChannelCinema};  	
		if(baseChannelCinema!=null && baseChannelCinema!=""){
			for(i=0;i<baseChannelCinema.length;i++){
				$("#cinemano_" + baseChannelCinema[i].cinemano).click();
				checkCinema(baseChannelCinema[i].cinemano);
			}
		}
	}
	
	//查询影院
	 function Search(){  
	        var paradata = {
	        		provinceno: $("#provinceno").combobox('getValue'),
	        		cityno:  $("#cityno").combobox('getValue'),
	        		countyno:  $("#countyno").combobox('getValue'),
	        		cinemano: $('#cinemano').val(),
	        		cinemaname: $('#cinemaname').val()
	            };
			
			$.ajax({
	            cache: true,
	            type: "POST",
	            url:"searchCinema.do",
	            data:paradata,
	            dataType: "json",
	            async: false,
	            error: function(request) {
	                alert("查询失败!");
	            },
	            success: function(result) {
	               if(result.success){
	           			createShowingTable(result.data);   
	        			loadChannelCinema();
	               }else{
	            	   alert(result.errMsg);
	               }            
	            }
	        });	        
		}
		
		function Save(){
			var isok = true;
			
	        var id = $("#channelno").val();
	        if (checkedCinemano == ''||checkedCinemano==null) {
	            if (isok) {
	                parent.Tips.Error("未关联影院！");
	            }
	            isok = false;
	        } 
	        
	        
	        if(!isok){
	        	return false;
	        }
	        
	        var data = {
	        	id:id,
	        	chanelNo:checkedCinemano.substring(0,checkedCinemano.length-1)
            };
	
	        $.ajax({
	            cache: true,
	            type: "POST",
	            url:"savaCinema.do",
	            data:data,// 你的formid
	            dataType: "json",
	            async: false,
	            error: function(request) {
	                alert("保存失败!");
	            },
	            success: function(result) {
	               if(result.success){
	            	    parent.datalist._temp.list_box.datagrid("reload");
	        			parent.Tips.Success("成功");
	        			Cancel();
	               }else{
	            	   alert(result.errMsg);
	               }            
	            }
	        });
		}
		
		function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}
		
		function SelectAll(){
			var checkAll = $("#checkall").attr("checked");
			if(checkAll){	
				$.each($("[id^=cinemano_]"),function(i,n){
					$(n).attr("checked",true);
					checkCinema($(n).val());
				});
		
			}else{
				$.each($("[id^=cinemano_]"),function(i,n){
					$(n).attr("checked",false);
					unCheckCinema($(n).val());
				});
			}		
		}
		
		function checkCinema(cinemano){
			if(cinemano !=""){
				var tempCinemano = ','+ cinemano +',';
				if((','+checkedCinemano).indexOf(tempCinemano)==-1)
					checkedCinemano = checkedCinemano + cinemano +',';
			}
		}
		
		function unCheckCinema(cinemano){
			cinemano = cinemano +',';
			if(checkedCinemano.indexOf(cinemano)>-1)
				checkedCinemano = checkedCinemano.replace(cinemano,"");
		}
		
        function singleCheck(obj){	
        	if(obj.checked) { 
        		checkCinema(obj.value);
			} 
			else { 
				unCheckCinema(obj.value); 
			}
        }
		
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>