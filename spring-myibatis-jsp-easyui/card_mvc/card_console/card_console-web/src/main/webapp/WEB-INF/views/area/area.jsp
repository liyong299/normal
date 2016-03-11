<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="utf-8" />
    <title>兜有院线通后台管理系统 - 区域维护</title>

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
    var Area = {
        _temp: {
            items: null,
            hotCity: null,
            clientID: "0021",
            editor: null,
            parentID: 0
        },
        Init: function () {
            var _this = Area;

            _this._temp.items = $("#area div");

            $("#btn_all").click(function () {
                _this.Filter("");
                $("#filter a").removeClass("underline");
                $(this).addClass("underline");
            });
            $("#btn_abcde").click(function () {
                _this.Filter("abcde");
                $("#filter a").removeClass("underline");
                $(this).addClass("underline");
            });
            $("#btn_fghij").click(function () {
                _this.Filter("fghij");
                $("#filter a").removeClass("underline");
                $(this).addClass("underline");
            });
            $("#btn_klmno").click(function () {
                _this.Filter("klmno");
                $("#filter a").removeClass("underline");
                $(this).addClass("underline");
            });
            $("#btn_pqrst").click(function () {
                _this.Filter("pqrst");
                $("#filter a").removeClass("underline");
                $(this).addClass("underline");
            });
            $("#btn_wxyz").click(function () {
                _this.Filter("wxyz");
                $("#filter a").removeClass("underline");
                $(this).addClass("underline");
            });
            $("#btn_hot").click(function () {
                $("#layout_hot").dialog("open");
                _this.GetHotCity();
            });
            $("#area a").live("click", function () {
                var _this = Area,
                    btn = $(this);
                if (btn.html() == "区域") {
                    $("#layout_area").dialog("open");
                    _this.GetArea(btn.attr("data"));
                    _this._temp.parentID = btn.attr("parentID");
                }
                else if(btn.html() == "开启" || btn.html() == "关闭"){
                	_this.OpenArea(btn.attr("data"),btn.html());
                }
                else {
                    if (btn.parent("span").attr("class") == "no") {
                        Tips.Error("当前城市未启用");
                        return;
                    }
                    _this.SetHot(btn.attr("data"), btn.attr("ishot"));
                }
            });
            $("#layout_area .save").live("click", function () {
                var no = $("#layout_area input.textbox-text").eq(0).val(),
                    py = $("#layout_area input.textbox-text").eq(1).val(),
                    name = $("#layout_area input.textbox-text").eq(2).val();
                
                Area.AddArea(no, py, name);
            });
            $("#layout_area .cencel").live("click", function () {
                $('#layout_areabox').datagrid('rejectChanges');
                _this._temp.editor = null;
            });

            _this.Load();
        },
        AddArea: function (no, py, name) {
            var _this = Area;
            if (!no) {
                $.messager.alert("错误", "区域编号不能为空！");
                return;
            }
            else if (no.length > 10) {
                $.messager.alert("错误", "区域编号不能超过10个字符！");
                return;
            }
            else if (!/^[0-9]*$/.test(no)) {
                $.messager.alert("错误", "区域编号只能为数字！");
                return;
            }
            else if (!py) {
                $.messager.alert("错误", "拼音不能为空！");
                return;
            }
            else if (py.length > 10) {
                $.messager.alert("错误", "拼音不能超过20个字符！");
                return;
            }
            else if (!/^[a-zA-Z]*$/.test(py)) {
                $.messager.alert("错误", "拼音只能为字母！");
                return;
            }
            else if (!name) {
                $.messager.alert("错误", "区域名称不能为空！");
                return;
            }
            else if (name.length > 20) {
                $.messager.alert("错误", "区域名称不能超过20个字符！");
                return;
            }

            var isok = true;
            var items = $("#layout_areabox").datagrid("getRows");
            $.each(items, function (i, n) {
                if (items[i].areaNo == no) {
                    $.messager.alert("错误", "区域编号不能重复！");
                    isok = false;
                    return;
                }
                if (items[i].areaName == name) {
                    $.messager.alert("错误", "区域名称不能重复！");
                    isok = false;
                    return;
                }
            });
            if (!isok) {
                return;
            }

            $.ajax({
                type: 'post',
                cache: false,
                dataType: 'json',
                url: "../area/addArea.do",
                data: { no: no, py: py, name: name, parentId: _this._temp.parentID },
                async: true,
                success: function (result) {
                    if (result.isSucceed) {
                        $("#layout_areabox").datagrid("reload");
                        _this._temp.editor = null;
                    }
                    else {
                        alert(result.Message);
                    }
                },
                error: function () {
                },
                complete: function () {
                }
            });
        },
        OpenArea: function(id,status){
        	var msg = "确定"+status+"吗？";
        	var isused = status=="关闭"? "0":"1";
        	$.messager.confirm("系统提示",msg , function (b) {
        		var _this = Area;
                if (b) {
                    $.ajax({
                        type: 'post',
                        cache: false,
                        dataType: 'json',
                        url: "../area/changeStatusArea.do",
                        data: { id: id ,isused:isused},
                        async: true,
                        success: function (result) {
                        	if (result.isSucceed) {
                                _this.Load();
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
            });
        },
        DeleteArea: function (id) {
            $.messager.confirm("系统提示", "确定删除？", function (b) {
                if (b) {
                    $.ajax({
                        type: 'post',
                        cache: false,
                        dataType: 'json',
                        url: "../area/deleteArea.do",
                        data: { id: id },
                        async: true,
                        success: function (result) {
                            if (result.isSucceed) {
                                $("#layout_areabox").datagrid("reload");
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
            });
        },
        Filter: function (key) {
            var _this = Area,
                key = key.toLocaleLowerCase();

            $.each(_this._temp.items, function (i, n) {
                var item = _this._temp.items.eq(i),
                    py = item.attr("py")[0].toLocaleLowerCase();
                if (key === "" || key.search(py) >= 0) {
                    item.show();
                }
                else {
                    item.hide();
                }
            });
        },
        GetHotCity: function () {
            $.ajax({
                type: 'post',
                cache: false,
                dataType: 'json',
                url: "../area/gethotcity.do",
                data: { clientId: Area._temp.clientID },
                async: true,
                success: function (result) {
                    var html = "";
                    $.each(result, function (i, n) {
                        html += "<tr data=\"" + n.areaNo + "\"><td>" + n.areaName + "</td></tr>";
                    });
                    $("#layout_hot tbody").html(html);
                },
                error: function () {
                },
                complete: function () {
                }
            });
        },
        GetArea: function (id) {
            $("#layout_areabox").datagrid("load", { id: id });
            Area._temp.editor = null;
        },
        SetHot: function (id, isHot) {
            var _this = Area,
                text = "",
                url = "../area/updatecityhot.do";
            if (isHot == 1) {
                text = "取消热门城市？";
                url = "../area/cencelcityhot.do";
            }
            else {
                text = "设置热门城市？";
            }
            $.messager.confirm("系统提示", text, function (b) {
                if (b) {
                    $.ajax({
                        type: 'post',
                        cache: false,
                        dataType: 'json',
                        url: url,
                        data: { id: id, clientId: Area._temp.clientID },
                        async: true,
                        success: function (result) {
                            if (result.isSucceed) {
                                _this.Load();
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
            });
        },
        Load: function (clientID) {
            var _this = Area;
            if (clientID) {
                _this._temp.clientID = clientID;
            }
            else {
                clientID = _this._temp.clientID
            }

            $.ajax({
                type: 'post',
                cache: false,
                dataType: 'json',
                url: "../area/getAllAreaByClient.do",
                data: { clientId: _this._temp.clientID },
                async: true,
                success: function (result) {
                    result;
                    var html = "";
                    $.each(result, function (i, n) {
                        html += "<div py=\"" + n.chn + "\"><span>" + n.areaName + "</span><div>";
                        $.each(n.childAreas, function (j, m) {
                            html += "<span class=\"" + (m.isHot ==1? "hot" : (m.isUsed == 1 ? "" : "no")) + "\"><a data=\"" + m.areaNo + "\" ishot=\"" + m.isHot + "\">" + m.areaName + "</a><a data=\"" + m.areaNo + "\" parentID=\"" + m.areaNo + "\">区域</a><a data=\"" + m.areaId + "\" >"+(m.isUsed == 1 ? "关闭" : "开启")+"</a></span>";
                            
                        });
                        html += "</div></div>";
                    });
                    $("#area").html(html);

                    _this._temp.items = $("#area > div");
                },
                error: function () {
                },
                complete: function () {
                }
            });

            _this.ShowHotCityList();
        },
        ShowHotCityList: function () {
            $.ajax({
                type: 'post',
                cache: false,
                dataType: 'json',
                url: "../area/gethotcity.do",
                data: { clientId: Area._temp.clientID },
                async: true,
                success: function (result) {
                    if (result.length > 0) {
                        var list = "";
                        $.each(result, function (i, n) {
                            list += "<span>" + n.areaName + "</span>";
                        });
                        $("#hotcity_box").show();
                        $("#cityList").html(list);
                    }
                    else {
                        $("#hotcity_box").hide();
                    }
                },
                error: function () {
                },
                complete: function () {
                }
            });
        }
    };

    $(function () {
        var _this = Area;

        $('.left_tree').tree({
            data: [
                   {
                	   /**
                	   text: '渠道', children: [
                	                             { text: 'web网站', data: "C100000000" }, 
                	                             { text: '兜有android', data: "C100000001" }, 
                	                             { text: '中影票务通微信h5', data: "0021" }
                	                             ] */
                   
                   
                   text: '渠道', 
                   children: ${channelEntities}
                   }
                   ],
            animate: true,
            lines: true,
            onClick: function (node) {
                Area.Load(node.data);
            }
        });

        $('#layout_hot').dialog({
            title: '热门城市',
            width: 300,
            height: 400,
            closed: true,
            cache: false,
            modal: true,
            buttons: [{
                text: '关闭',
                handler: function () {
                    $("#layout_hot").dialog("close");
                }
            }]
        });

        $('#layout_area').dialog({
            title: '区域编辑',
            width: 500,
            height: 400,
            closed: true,
            cache: false,
            modal: true,
            buttons: [{
                text: '关闭',
                handler: function () {
                    $("#layout_area").dialog("close");
                }
            }]
        });

        $("#layout_areabox").datagrid({
            url: "../area/getArea.do",
            singleSelect: true,
            toolbar: [{
                iconCls: 'icon-add',
                text: "添加",
                handler: function () {
                    if (Area._temp.editor) {
                        $("#layout_area").scrollTop(2000);
                        return;
                    }
                    $("#layout_areabox").datagrid("appendRow", {});
                    _this._temp.editor = $('#layout_areabox').datagrid('getRows').length - 1;
                    $('#layout_areabox').datagrid('selectRow', _this._temp.editor).datagrid('beginEdit', _this._temp.editor);
                    $("#layout_area").scrollTop(2000);
                }
            }],
            columns: [[
                { field: 'areaId', title: 'areaId', width: 120, align: 'center', hidden: true },
                { field: 'areaNo', title: '区域编号', width: 120, align: 'center', editor: "textbox" },
                { field: 'chn', title: '拼音', width: 120, align: 'center', editor: "textbox" },
                { field: 'areaName', title: '区域名称', width: 120, align: 'center', editor: "textbox" },
                {
                    field: 'Handler', title: '操作', width: 124, align: 'center', formatter: function (value, rowData, rowIndex) {
                        return rowData.areaId ? "<a class=\"delete\" onclick=\"Area.DeleteArea(" + rowData.areaId + ")\">删除</a>" : "<a class=\"save\" style=\"margin-right:10px\">保存</a><a class=\"cencel\">取消</a>";
                    }
                }
            ]],
        });

        
        $("#layout_hot tbody").sortable({
            helper: fixHelper,
            axis: "y",
            cursor: "move",
            placeholder: ".moveHandle",
            update: function () {
                var items = $("#layout_hot tr"),
                    ids = "";
                $.each(items, function (i, n) {
                    ids += items.eq(i).attr("data") + ",";
                });

                $.ajax({
                    type: 'post',
                    cache: false,
                    dataType: 'json',
                    url: "../area/updateHotSort.do",
                    data: {"ids": ids,clientId: Area._temp.clientID},
                    async: true,
                    success: function (result) {
                        Area.ShowHotCityList();
                    },
                    error: function () {
                    },
                    complete: function () {
                    }
                });
            }
        }).disableSelection();
        

        Area.Init();
    });

    var fixHelper = function (e, ui) {
        ui.children().each(function () {
            $(this).width($(this).width());
        });
        return ui;
    };
</script>

</head>
<body class="easyui-layout">
    

<style>
    body { height:100%}
    .underline { text-decoration: underline; }
    .left_tree { height:100%;}

    #cityList span { margin: 0 2px; display: inline-block; }

    #area { padding: 10px 20px }
    #area > div { overflow: hidden; line-height:35px; border-width: 0 0 1px 0; border-style:solid; border-color:#ccc }
    #area > div span { width: 200px; height:35px; display:inline-block; text-align: center }
    #area > div > div { float: left; width: 800px; }
    #area div span:hover { background-color:#f8f8f8; }
    #area > div > span:first-child { float:left; background-color:#eee; }
    #area div a { margin: 0 2px; cursor: pointer }
    #area div .hot a { color: #c00 !important; }
    #area div .no a { color: #ccc !important; }

    #layout_hot { text-align: center }
    #layout_hot table { width: 100%; }
    #layout_hot tr {  }
    #layout_hot td { height:24px; border-width: 0 0 1px 0; border-style: solid; border-color:#eee; cursor: move }
</style>

<div data-options="region:'west',split:true,border:false" title="客户端" style="width:220px;">
    <div class="left_tree"></div>
</div>
<div data-options="region:'center',border:false">
    <div style="border-left:0px; border-right:0px; border-top:0px; border-bottom:1px; border-color:#ccc;border-style:solid">
        <div id="filter" style="padding:10px 20px">
            <label>筛选：</label>
            <a id="btn_all">全 部</a>
            <a id="btn_abcde">ABCDE</a>
            <a id="btn_fghij">FGHIJ</a>
            <a id="btn_klmno">KLMNO</a>
            <a id="btn_pqrst">PQRST</a>
            <a id="btn_wxyz">WXYZ</a>
        </div>
        <div id="hotcity_box" style="padding:0 20px 10px">
            <span>热门城市：</span><span id="cityList" style="color: #c00;"></span><span class="g-mlr-5">|</span><a id="btn_hot" class="easyui-linkbutton">排序</a>
        </div>
    </div>
    <div id="area"></div>
</div>

<div id="layout_hot">
    <table cellpadding="0" cellspacing="0">
        <tbody>
            <tr><td>正在获取数据...</td></tr>
        </tbody>
    </table>
</div>
<div id="layout_area">
    <div id="layout_areabox"></div>
</div>


</body>
</html>

