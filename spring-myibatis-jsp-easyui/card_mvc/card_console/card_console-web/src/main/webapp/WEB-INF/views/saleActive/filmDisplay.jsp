<%@taglib prefix="easyui" uri="org.topteam/easyui" %>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false" %>

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
		padding: 5px;
		border-style: solid;
		border-color: #666666;
		background-color: #ffffff;
	}
		       
</style>  

</easyui:templateOverride>
<easyui:templateOverride name="body">
 		<form  id="filmDisplayForm" class="edit_box2">
		     <div id="dataTable" style="height:420;overflow: auto;"></div>
		  <div class="item item_row button">
            <a id="btnSave" class="easyui-linkbutton" onclick="Save()">保存</a>
        </div>
	</form>	
</easyui:templateOverride>
<easyui:templateOverride name="script">
	<script>
	
	var data=${activeGoodList};
	
	$(function(){
 		createShowingTable(data);   
 		$("#checkall").click();
 		if('${isView}' =='1')
 			$("#btnSave").hide();
	});
	
	function createShowingTable(data){
		  $("#dataTable").html('');  
		  //获取后台传过来的jsonData,并进行解析
		  var dataArray = data;//$.parseJSON(data.jsonData);
		  //此处需要让其动态的生成一个table并填充数据
		  var tableStr = "<table id='datalist'  class='gridtable'>";
		  tableStr = tableStr + "<tr><th width='30'>序号</th>	<th width='30'><input id='checkall'  name='checkall' type='checkbox' onclick='SelectAll()'/></th> <th>影片编码</th> <th>影片名称</th><th>放映类型</th></tr>";
		  var len = dataArray.length;
		  for(var i=0 ;i<len ; i++){	
			  var count= i+1;
			  tableStr = tableStr + "<tr><td>"+ count +"</td>"+
			  							"<td width='30'><input id='filmno_"+dataArray[i].filmno+"'  name='filmno'  value='" +dataArray[i].filmno+"'  type='checkbox' /></td>"+
			  							"<td>"+dataArray[i].filmno + "</td>"+"<td>"+dataArray[i].filmName +"</td>"+
			  							"<td>"+
			  							"<input type=\'checkbox\'  name=\'filmshowtype0\' value=\'0\' "+ isCheck(dataArray[i].filmShowType,'0') +" >2D &nbsp;&nbsp;&nbsp;"+
			  							"<input type=\'checkbox\'  name=\'filmshowtype1\' value=\'1\' "+ isCheck(dataArray[i].filmShowType,'1') +" >3D &nbsp;&nbsp;&nbsp;"+
			  							"<input type=\'checkbox\'  name=\'filmshowtype2\' value=\'2\' "+ isCheck(dataArray[i].filmShowType,'2') +" >IMAX2D &nbsp;&nbsp;&nbsp;"+
			  							"<input type=\'checkbox\'  name=\'filmshowtype3\' value=\'3\' "+ isCheck(dataArray[i].filmShowType,'3') +" >IMAX3D &nbsp;&nbsp;&nbsp;"+
			  							"<input type=\'checkbox\'  name=\'filmshowtype4\' value=\'4\' "+ isCheck(dataArray[i].filmShowType,'4') +" >中国巨幕2D &nbsp;&nbsp;&nbsp;"+
			  							"<input type=\'checkbox\'  name=\'filmshowtype5\' value=\'5\' "+ isCheck(dataArray[i].filmShowType,'5') +" >中国巨幕3D &nbsp;&nbsp;&nbsp;"+
			  							"</td>"+
			  						 "</tr>";	 
			  }
			  tableStr = tableStr + "</table>";
			  //将动态生成的table添加的事先隐藏的div中.
			  $("#dataTable").html(tableStr);  
	 }
	
	function isCheck(showTypeStr, showType){
		
		if(showTypeStr.indexOf(showType) > -1)  
			return "checked='true'";
		else
			return "";
	}
	
	function SelectAll(){
		var checkAll = $("#checkall").attr("checked");
		if(checkAll){	
			$.each($("[id^=filmno_]"),function(i,n){
				$(n).attr("checked",true);
				//checkCinema($(n).val());
			});
	
		}else{
			$.each($("[id^=filmno_]"),function(i,n){
				$(n).attr("checked",false);
				//unCheckCinema($(n).val());
			});
		}		
	}
	
	
	function Save(){
    	var checkStr="";
    	var selCount=0;
    	var isOk = true;
    	
   		var filmnos = "";    
   		var tableObj = $('#datalist'); 
   		//遍历Table的所有Row 
   		for (var i = 1; i < tableObj[0].rows.length; i++) {//i=1 ,从tr开始，0是th
   			var showTypes="";
   			 if(tableObj[0].rows[i].cells[1].children[0].checked){
	  			filmnos = tableObj[0].rows[i].cells[1].children[0].value;
	   			for(var j = 0; j < 6; j++){
		   			if(tableObj[0].rows[i].cells[4].children[j].checked)
		   				showTypes = showTypes + tableObj[0].rows[i].cells[4].children[j].value + ",";
		   		}
	    		if(showTypes == ""){
	      				alert('请选择影片放映类型。');
	      				isOk = false;
	      				return isOk;
	   			}
	   			else	
	   				showTypes = showTypes.substring(0, showTypes.length - 1);
	 			
	   			checkStr = checkStr + filmnos +"." + showTypes + "|";
	   			selCount += 1;
   			 }
   		}  
       		
       	
		if(selCount>0){
            checkStr = checkStr.substring(0, checkStr.length - 1);
	        	var html = "<input type='hidden' id='filmSelStr' value='"+checkStr+"' />已选择影片<span id='selCount'>"+ selCount +"</span>部&nbsp;&nbsp;&nbsp;&nbsp;" +
				"<a href='javascript:void(0);' onclick='showFilmDisplay()' >详情</a>";
				// "<a href='javascript:void(0);' onclick='showFilmDisplay(&apos;"+checkStr+"&apos;)' >详情</a>";
        	$("#film_div", parent.document).html(html);
        	Cancel();
		}else{
			$("#film_div", parent.document).html("");
		}
	}
	
	function Cancel(){
		parent.iframe._temp.layout_box.window("close");
	}
		
	</script>
</easyui:templateOverride>

<%@include file="/WEB-INF/views/share/include.jsp" %>