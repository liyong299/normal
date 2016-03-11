<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery-1.11.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
.sub {
margin-left: 70px;
width: 70px;
height: 30px;
font-size: 14px;
}
.warpe{
width: 460px;
float:left;
margin-right:17px;
margin-bottom:17px;
padding:40px 0px 10px;
border-radius: 13px;
font-size: 14px;
}
td {
margin: 100px;
}
body{
color: #444;
font-size: 13px;
}
</style>
<script>
    function submitForm(){
    	
    	  var cinemaCode = $("#cinemaCode").val();
    	  var channelCode = $("#channelCode").val();
    	  var type = $('input[name="suffix"]:checked').val();
  		  var url = '<%=request.getContextPath()%>/interfaceApi/sign/toSign.html';
  		  var dataPar = "method=queryHalls."+type+"&channelCode="+channelCode+"&cinemaCode="+cinemaCode;
  		  $.ajax({
  			url:url,
  			async:false,
  			type:"POST",
  			dataType:"json",
  			data:dataPar,
  			success:function(data){
  				 
  				dataPar += "&sign="+data.sign;
  				 $.ajax({
  		  			url:'<%=request.getContextPath()%>/interfaceApi/ticket/v2.html',
  		  			async:false,
  		  			type:"POST",
  		  			dataType:"json",
  		  			data:dataPar,
  		  			success:function(data){
  		  				if(type=="json"){
  		  					var json = JSON.stringify(data, null, "\t");
  		  				 	$("#txtareaBack").text(json);
  		  				}else
  		  			    	$("#txtareaBack").text(data);
  		  			},
  		  			error:function (error) {
                           $("#txtareaBack").text(error.responseText);
  		  			}
  		  			});
  			},
  			error:function (error) {
  					msgAlert('信息提示',error.responseText,'warning',null);
  			}
  			});
    	
    }
   
</script>
</head>
<body>
<div class="width:100%;height:20%;">
	 <table>
	 	<tr>
	 		<td width="100px">渠道信息：</td>
	 		<td><input type="text" value="0001" id="channelCode"/></td>
	 		<td>&nbsp;</td>
	 		<td>返回格式：</td>
	 		<td><input type="radio" value="xml" name="suffix" checked="checked" />xml
	 		  	<input type="radio" value="json" name="suffix"/> json
	 		</td>
	 	    <td>&nbsp;</td>
	 		<td width="100px">影院编码：</td>
	 		<td><input type="text" value="42013901" id="cinemaCode"/></td>
	 		<td>&nbsp;</td>
	 		<td><input type="button" onclick="submitForm()" value=" 提 交  "/></td>
	 	</tr>
	 </table> 
</div>
<br/>
<b>返回结果</b>
<br/>
<textarea  id="txtareaBack" style="font-size: 10px;width: 100%;height: 720px; resize: none;background-color:aliceblue;color: darkblue;" disabled="disabled"></textarea>
</body>
</html>