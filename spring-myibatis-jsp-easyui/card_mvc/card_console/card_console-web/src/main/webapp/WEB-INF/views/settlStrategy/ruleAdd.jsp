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
        var array_id = [];

        $(function () {
            //初始化控件
            init();
            
        });

        var createParams = function () {
            var settlType = $('input:radio:checked', parent.document).val();
            var params = [];
            var flag = true;
            $.each(array_id, function () {
                if (settlType == 0) {
                    if (this.settlType == 0) {
                        if ($("#" + this.id).textbox("getValue") == "") {
                            $("#" + this.id).textbox("showError", "请输入结算价格");
                            flag = false;
                        } else if (isNaN($("#" + this.id).textbox("getValue"))) {
                            $("#" + this.id).textbox("showError", "请输入数字");
                            flag = false;
                        } else if (parseFloat($("#" + this.id).textbox("getValue")) == 0) {
                            $("#" + this.id).textbox("showError", "请输入大于0的数");
                            flag = false;
                        } else {
                            $("#" + this.id).textbox("closeError");
                            params.push({
                                id: $("#" + this.id + "_id").val(),
                                strategyId: "",
                                showType: this.showType,
                                type: this.settlType,
                               settlPrice: $("#" + this.id).textbox("getValue")
                            });
                        }
                    }
                } else {
                    if (this.settlType == 1 || this.settlType == 2) {
                        if ($("#" + this.id).textbox("getValue") == "") {
                            $("#" + this.id).textbox("showError", "请输入结算价格");
                            flag = false;
                        } else if (isNaN($("#" + this.id).textbox("getValue"))) {
                            $("#" + this.id).textbox("showError", "请输入数字");
                            flag = false;
                        } else if (parseFloat($("#" + this.id).textbox("getValue")) == 0) {
                            $("#" + this.id).textbox("showError", "请输入大于0的数");
                            flag = false;
                        } else {
                            $("#" + this.id).textbox("closeError");
                            params.push({
                                td: $("#" + this.id + "_id").val(),
                                strategyID: "",
                                showType: this.showType,
                                type: this.settlType,
                                settlPrice: $("#" + this.id).textbox("getValue")
                            });
                                 
                        }
                    }
                }
            });

            return flag ? params : false;
        }

        var init = function () {
        	var settlType = "${settlType}";
        	
            $(".settlType_" + settlType).hide();
            createTextBox();
        }

        var createTextBox = function () {
        	var cinemaNo = "${cinemaNo}";
        	var settlType = "${settlType}";
            $.ajax({
                type: 'post',
                cache: false,
                dataType: 'json',
                url: "../settlStrategy/getDictionary.do",
                data: {
                    cinemaNo:cinemaNo,
                    settlType: settlType
                },
                async: false,
                success: function (data) {
                    if (!data.isSuccess) {
                        parent.parent.Tips.Error(data.Message);
                    } else {
                        $.each(data.rows, function () {
                            var item_0 = $("<div/>").addClass("item");
                            $("<label/>").text(this.goodsShowtypeStr + "：").appendTo(item_0);
                            var textbox_0 = $("<input/>").attr("id", "_0_" + this.goodsShowtype);
                            textbox_0.appendTo(item_0);
                            $("<label/>").text(" 元").appendTo(item_0);
                            $("<span/>").addClass("star").text("*").appendTo(item_0);
                            $("<input/>").attr("id", "_0_" + this.goodsShowtype + "_id").attr("type", "hidden").appendTo(item_0);
                            item_0.appendTo($("#_0"));
                            array_id.push({ id: "_0_" + this.goodsShowtype, showType: this.goodsShowtype, settlType: 0 });


                            var item_1 = $("<div/>").addClass("item");
                            $("<label/>").text(this.goodsShowtypeStr + "：").appendTo(item_1);
                            var textbox_1 = $("<input/>").attr("id", "_1_" + this.goodsShowtype);
                            textbox_1.appendTo(item_1);
                            $("<label/>").text(" 元").appendTo(item_1);
                            $("<span/>").addClass("star").text("*").appendTo(item_1);
                            $("<input/>").attr("id", "_1_" + this.goodsShowtype + "_id").attr("type", "hidden").appendTo(item_1);
                            item_1.appendTo($("#_1"));
                            array_id.push({ id: "_1_" + this.goodsShowtype, showType: this.goodsShowtype, settlType: 1 });

                            var item_2 = $("<div/>").addClass("item");
                            $("<label/>").text(this.goodsShowtypeStr + "：").appendTo(item_2);
                            var textbox_2 = $("<input/>").attr("id", "_2_" + this.goodsShowtype);
                            textbox_2.appendTo(item_2);
                            $("<label/>").text(" 元").appendTo(item_2);
                            $("<span/>").addClass("star").text("*").appendTo(item_2);
                            $("<input/>").attr("id", "_2_" + this.goodsShowtype + "_id").attr("type", "hidden").appendTo(item_2);
                            item_2.appendTo($("#_2"));
                            array_id.push({ id: "_2_" + this.goodsShowtype, showType: this.goodsShowtype, settlType: 2 });
                        });
                    }
                }
            });

            $.each(array_id, function () {
                $("#" + this.id).textbox({
                    width: "50px",
                    onChange: function (newValue, oldValue) {
                        if (isNaN(newValue) || newValue == "" || parseFloat(newValue) <= 0) {
                            $(this).textbox("setValue", "");
                        } else {
                            $(this).textbox("setValue", parseFloat(newValue).toFixed(2));
                        }
                    }
                });
            });
        }

        var changeSettlType = function (type) {
            if (type == "0") {
                $(".settlType_0").hide("fast");
                $(".settlType_1").show("middle");
            } else {
                $(".settlType_1").hide("fast");
                $(".settlType_0").show("middle");
            }
        }
    </script>

</head>
<body class="easyui-layout">
    

<style>
    fieldset {
        border-style: dotted;
        border-width: 1px;
        margin: 0 20px 10px 20px;
    }
</style>

<div data-options="region:'center',border:false">
    <form class="edit_box">
        <fieldset class="settlType_1" id="_0">
            <legend>常规价</legend>
        </fieldset>

        <fieldset class="settlType_0" id="_1">
            <legend>忙时价</legend>
        </fieldset>

        <fieldset class="settlType_0" id="_2">
            <legend>闲时价</legend>
        </fieldset>
    </form>
</div>


</body>
</html>