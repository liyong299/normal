<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>attendance</title>
<link href="/resources/uploadify/uploadify.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="/resources/jquery.min.js"></script>
<script type="text/javascript"
	src="/resources/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#deal_success").hide();
		$('#file_upload').uploadify({
			'swf': '/resources/uploadify/uploadify.swf',
			'uploader' : '/servlet/UploadHandleServlet',
			'cancelImg' : '/resources/uploadify/cancel.png',
			'queueID' : 'fileQueue',
			'auto' : true,
			'buttonText' : 'select',
			'simUploadLimit' : 1,
			'queueSizeLimit' : 1,
			'fileExt' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp;*.zip;*.rar;*.7z,*.xlsx',
			onUploadSuccess : function(file, data, response)  {
				//转换为json对象
				alert(data);
				$("#deal_success").show();
				$("#result_file").html(data);
			},
			onSelect : function() {
			},
			onError : function(event, queueID, fileObj) {
				alert("文件:" + fileObj.name + "上传失败");
			}
		});
	});
</script>
</head>
<body>

	upload :
	<input type="file" name="file_upload" id="file_upload" />
	<div id="fileQueue"></div>
	<div id="deal_success" >
	    <span>sucess!</span>
	    <div id="result_file"></div>
	</div>
</body>

</html>
