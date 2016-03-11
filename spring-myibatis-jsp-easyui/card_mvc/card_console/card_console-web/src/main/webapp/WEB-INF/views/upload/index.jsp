<%@taglib prefix="easyui" uri="org.topteam/easyui"%>
<%@taglib prefix="easyui_ext" uri="com.mopon/jeasyui_extensions"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="false"%>

<easyui:templateOverride name="title">上传文件</easyui:templateOverride>
<easyui:templateOverride name="head">

<link href="<%=request.getContextPath()%>/resources/scripts/jquery-uploadify/uploadify.css" rel="stylesheet" type="text/css"/>

</easyui:templateOverride>
<easyui:templateOverride name="body">

<div id="queue" style="hight:100px"></div>
                    <input id="file_upload" name="file_upload" type="file" multiple="true">
                    <p ><a href="javascript:$('#file_upload').uploadify('upload', '*')">上传文件</a>
| <a href="javascript:$('#file_upload').uploadify('stop')">停止上传!</a> </p>
</easyui:templateOverride>

<easyui:templateOverride name="script">
<script src="<%=request.getContextPath()%>/resources/scripts/jquery-uploadify/jquery.uploadify.js"></script>
<script src="<%=request.getContextPath()%>/resources/scripts/jquery-uploadify/jquery.uploadify.min.js"></script>
<script>
			$(function(){
			    $("#file_upload").uploadify({//上传文件
			    	'method'   : 'post',
			         'debug':'true',//开发者模式
			         'uploader'       : 'uploadify.do', //文件上传的请求
			         'swf':'../resources/scripts/jquery-uploadify/uploadify.swf',//动态控件
			         'cancelImg'      : '../resources/scripts/jquery-uploadify/uploadify-cancel.png',    //取消图片
			         'queueID'        : 'queue',//与下面的id对应    文件上传队列显示的地方
			         'fileDesc'    : 'rar文件或zip文件',
			          'fileExt' : '*.rar;*.zip;*.jpg;*.png;*.exe;*.gif', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
			            'auto'           : false,  //是否自动上传
			         'multi'          :    false,  //是否多文件上传
			         'simUploadLimit' : 2,
			         'progressData ': 'percentage',//显示上传进度方式

			         'buttonText'     : '上传图片',//文件选择按钮名称
			         'onUploadSuccess' : function(file, data, response) {
			             alert('文件:' + file.name + ',' + data);
			         },
			         'onUploadError' : function(file, errorCode, errorMsg, errorString) {
			             alert('文件: ' + file.name + ',' + errorString);
			         }
			     });
			});
</script>
</easyui:templateOverride>
<%@include file="/WEB-INF/views/share/include.jsp" %>