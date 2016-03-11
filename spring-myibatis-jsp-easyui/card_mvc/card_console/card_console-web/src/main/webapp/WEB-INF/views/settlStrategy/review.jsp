<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>兜有院线通后台管理系统 - </title>
    <link href="<%=request.getContextPath()%>/resources/css/common/strategy.css" rel="stylesheet"/>

	<link href="<%=request.getContextPath()%>/resources/css/common/common.css" rel="stylesheet" type="text/css"/> 
    <link href="<%=request.getContextPath()%>/resources/css/base.css" rel="stylesheet" type="text/css"/> 
		<link href="<%=request.getContextPath()%>/resources/css/themes/easyui.css" rel="stylesheet" type="text/css"/>
		<link href="<%=request.getContextPath()%>/resources/css/themes/icon.css" rel="stylesheet" type="text/css"/>
		<link href="<%=request.getContextPath()%>/resources/css/themes/color.css" rel="stylesheet" type="text/css"/>

    <script src="<%=request.getContextPath()%>/resources/scripts/jquery-1.11.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jquery-migrate-1.2.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jquery.easyui.min.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/easyui-lang-zh_CN.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jquery.serialize-object.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jeasyui.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/base.js"></script>
		<script src="<%=request.getContextPath()%>/resources/scripts/jquery-ui.js"></script>

    <script type="text/javascript">
        var settlType = "";
        var cinemaNo = "${strategy.cinemaNo}";
        var id = "${strategy.id}";

        $(function () {
            //初始化数据
            load();

            //初始化控件
            init();
        });

        var load = function () {
              $("#name").textbox("setValue", "${strategy.name}");
              $("#settlTypeStr").textbox("setValue", "${strategy.settlTypeStr}");
              $("#validDate").textbox("setValue", "${strategy.validDate}");
              $("#statusStr").textbox("setValue", "${strategy.statusStr}");
              settlType = "${strategy.settlType}";
              cinemaNo = "${strategy.cinemaNo}";
              id = "${strategy.id}";
              $("#ruleBox").attr("src", "<%=request.getContextPath()%>/settlStrategy/reviewRule.html?id="+id);
        }

        var init = function () {
            $("#name").textbox("readonly");
            $("#settlTypeStr").textbox("readonly");
            $("#validDate").textbox("readonly");
            $("#statusStr").textbox("readonly");
        }

        function review(flg){
        	var flag = "${flag}";
        	var id = "${strategy.id}";
        	var url = "";
        	 var status = "";
        	 if(flag=="1"){
        		if(flg){
        		 status="3";
        		}else{
        		status="-2"
        		} 
        	 }else{
        		 if(flg){
        			 status="1"; 
        		 }else{
        			 status="-3";
        		 }
        	 }
        	 var data = {
            			id:id,
            			status:status
                 };
        	 $.ajaxRequest({
         		url: "updateReview.do",
         		para: data,
         		success: function(result){
         			if(result.isSuccess){
         				parent.datalist._temp.list_box.treegrid("reload");
 	        			parent.Tips.Success("操作成功");
 	        			Cancel();
 	        		}
         			else{
         				parent.Tips.Error(result.message);
         			}
         		}
         	});
        	 
        }
        
        function Cancel(){
			parent.datalist._temp.layout_box.window("close");
		}
    </script>

</head>
<body class="easyui-layout">
    
<div data-options="region:'center',border:false">
    <form class="edit_box" style="padding-bottom:0;">
        <div class="item">
            <label>策略名称：</label><input class="easyui-textbox" data-options="width: '200'" id="name" name="name"></input>
        </div>
        <div class="item">
            <label>定价类型：</label><input class="easyui-textbox" data-options="width: '200'" id="settlTypeStr" name="settlTypeStr"></input>
        </div>
        <div class="item">
            <label>有效期限：</label><input class="easyui-textbox" data-options="width: '200'" id="validDate" name="validDate"></input>
        </div>
        <div class="item">
            <label>状态：</label><input class="easyui-textbox" data-options="width: '200'" id="statusStr" name="statusStr"></input>
        </div>
        <iframe id="ruleBox" style="width: 100%; height: 340px; border: none;"></iframe>
        <div class="item item_row button">
            <a class="easyui-linkbutton"  onclick="review(true)">通过</a>
            <a class="easyui-linkbutton"  onclick="review(false)">拒绝</a>
            <a class="easyui-linkbutton" onclick="Cancel()">取消</a>
        </div>
    </form>
</div>

</body>
</html>