// 日期类型格式化
function ChangeDateFormat(d) {

    if (d) {
        var date = new Date(parseInt(d.replace("/Date(", "").replace(")/", ""), 10));
        var month = padLeft(date.getMonth() + 1, 10);
        var currentDate = padLeft(date.getDate(), 10);
        return date.getFullYear() + "-" + month + "-" + currentDate;
    }
    return '---';
}
// 日期类型格式化到时分秒
function ChangeDateMinFormat(d) {
    if (d) {
        var date = new Date(parseInt(d.replace("/Date(", "").replace(")/", ""), 10));
        var month = padLeft(date.getMonth() + 1, 10);
        var currentDate = padLeft(date.getDate(), 10);
        var shi = padLeft(date.getHours(), 10);
        var fen = padLeft(date.getMinutes(), 10);
        var miu = padLeft(date.getSeconds(), 10);
        return date.getFullYear() + "-" + month + "-" + currentDate + " " + shi + ":" + fen + ":" + miu;
    }
    return '---';
}
// 时间类型格式化
function ChangeTimeFormat(d) {
    if (d) {
        var date = new Date(parseInt(d.replace("/Date(", "").replace(")/", ""), 10));
        var month = padLeft(date.getMonth() + 1, 10);
        var currentDate = padLeft(date.getDate(), 10);
        var hour = padLeft(date.getHours(), 10);
        var minute = padLeft(date.getMinutes(), 10);
        var second = padLeft(date.getSeconds(), 10);
        return date.getFullYear() + "-" + month + "-" + currentDate + " " + hour + ":" + minute + ":" + second;
    }
    return '---';
}
//用于new Date()
function getNowFormatDate() {
    var day = new Date();
    var Year = 0;
    var Month = 0;
    var Day = 0;
    var CurrentDate = "";
    //初始化时间
    //Year= day.getYear();//有火狐下2008年显示108的bug
    Year = day.getFullYear();//ie火狐下都可以
    Month = day.getMonth() + 1;
    Day = day.getDate();
    //Hour = day.getHours();
    // Minute = day.getMinutes();
    // Second = day.getSeconds();
    CurrentDate += Year + "-";
    if (Month >= 10) {
        CurrentDate += Month + "-";
    }
    else {
        CurrentDate += "0" + Month + "-";
    }
    if (Day >= 10) {
        CurrentDate += Day;
    }
    else {
        CurrentDate += "0" + Day;
    }
    return CurrentDate;
}
function IsEmpty(d)
{
    if (d) {
        return d;
    }
    return '---';
}
//财务数字格式化
function MoneyFormat(s) {
    if (s) {
        var n = 2;
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r;
    }
    return '---';
}

//财务数字格式化
function MoneyFormatYuan(s) {
    if (s) {
    	s = s/100;
        var n = 2;
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
        t = "";
        for (i = 0; i < l.length; i++) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r;
    }
    return '---';
}

function padLeft(str, min) {
    if (str >= min)
        return str;
    else
        return '0' + str;
}
//中英文字符长度计算
function getStrLength(str) {
    var cArr = str.match(/[^\x00-\xff]/ig);
    return str.length + (cArr == null ? 0 : cArr.length);
}
// 处理json错误消息
function ErrorMessage(msg) {
    var error = '';
    if (msg) {
        if (typeof (msg) == 'string' && msg.constructor == String) {
            error = msg;
        }
        else {
            error += '<div style="float: left;">'
            $.each(msg, function (i, item) {
                error += item + '<br>';
            });
            error += '</div>';
        }
    }
    return error;
}

/*区域级联控件*/
var AreaControl = {
    _temp: {
        province: null,
        city: null,
        area: null,
        data: null
    },
    Init: function (provinceid, cityid, areaid, fun) {
        var _this = AreaControl;

        _this._temp.province = "#" + provinceid;
        $(_this._temp.province).combobox({
            data: [{ "selected": true, "No": "", "Name": "全部" }],
            valueField: "No",
            textField: "Name",
            onSelect: _this.ProvinceSelect
        });
        _this._temp.city = "#" + cityid;
        $(_this._temp.city).combobox({
            data: [{ "selected": true, "No": "", "Name": "全部" }],
            valueField: "No",
            textField: "Name",
            onSelect: _this.CitySelect
        });
        _this._temp.area = "#" + areaid;
        $(_this._temp.area).combobox({
            data: [{ "selected": true, "No": "", "Name": "全部" }],
            valueField: "No",
            textField: "Name",
            onSelect: _this.AreaSelect
        });

        $.ajax({
            type: 'post',
            cache: false,
            dataType: 'json',
            url: "/common/getallarea",
            async: true,
            success: function (result) {
                _this._temp.data = result;
                var provinceList = new Array();
                provinceList.push({ selected: true, No: "", Name: "全部" });
                $.each(result, function (i, n) {
                    if (n.Level == 1) {
                        provinceList.push(n);
                    }
                });

                $(_this._temp.province).combobox('loadData', provinceList);
                if (fun) {
                    fun();
                }
            },
            error: function () {
            },
            complete: function () {
            }
        });
    },
    ProvinceSelect: function (item) {
        var _this = AreaControl,
            cityList = new Array();

        cityList.push({ selected: true, No: "", Name: "全部" });
        $.each(_this._temp.data, function (i, n) {
            if (n.ParentNo == item.No) {
                cityList.push(n);
            }
        });

        $(_this._temp.city).combobox("loadData", cityList);
        $(_this._temp.area).combobox("loadData", [{ selected: true, No: "", Name: "全部" }]);
    },
    CitySelect: function (item) {
        var _this = AreaControl,
            areaList = new Array();

        areaList.push({ selected: true, No: "", Name: "全部" });
        $.each(_this._temp.data, function (i, n) {
            if (n.ParentNo == item.No) {
                areaList.push(n);
            }
        });

        $(_this._temp.area).combobox("loadData", areaList);
    }
};

//上传完成后用于回调
var upload_obj = {
    //上传文件窗口对象
    _upload_box: null,
    //传递一个对象，如触发对象
    _upload_send: null,
    //完成处理
    CompleteHandler: function (data) {

    },
    Clear: function () {
        var _this = upload_obj;
        if (_this._upload_box) {
            _this._upload_box.window("close");
        }
        _this._upload_box = null;
        _this.CompleteHandler = null;
    }
};

function NoImage(t) {
    $(t).attr("src", "/content/images/no-image.jpg");
}

function getUrlParam(name) {
    
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");  
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function Datagrid_PagerHelper(gridId) {
    var footers = $('#' + gridId).datagrid('getFooterRows');
    var parmPm = $('#' + gridId).datagrid('options').queryParams;
    if (!footers) { return false; }
    if (footers.length > 0) {
        var arr = new Array();
        $.each(footers, function (i, n) {
            if ((n["minKey"] && n["maxKey"]) || (n["minKey"] === null && n["maxKey"] === null)) {
                parmPm.minKey = (footers && n.minKey) ? n.minKey : 0;
                parmPm.maxKey = (footers && n.maxKey) ? n.maxKey : 0;
                parmPm.pageOld = (footers && n.pageOld) ? n.pageOld : 0;
            }
            else {
                arr.push(n);
            }
        });

        $('#' + gridId).datagrid('reloadFooter', arr);
    }
    else {
        parmPm.minKey = (footers && footers.minKey) ? footers.minKey : 0;
        parmPm.maxKey = (footers && footers.maxKey) ? footers.maxKey : 0;
        parmPm.pageOld = (footers && footers.pageOld) ? footers.pageOld : 0;
    }
}

function ChangePageSize(gridId) {
    var p = $('#' + gridId).datagrid('getPager');
    $(p).pagination({
        onChangePageSize: function () {
            $(p).pagination("options").pageNumber = 1;
        }
    });
}

/*表格行样式*/
function Datagrid_RowStyle(index,row){
    if (index % 2 == 1) {
        return 'background-color:#f6f6f6;';
    }
}

/*表格右键菜单*/
function Datagrid_RowContextMenu(e, index, rowData) {
    e.preventDefault();
    var datagrid_menu = $("#datagrid_menu");
    if (datagrid_menu.length == 0) {
        $("body").append("<div id=\"datagrid_menu\" class=\"easyui-menu datagrid-menu\"></div>");
        $("#datagrid_menu").menu();

        $("#datagrid_menu a").live("click", function (item) {
            $("#datagrid_menu").menu("hide");
        });
    }
    var html = $(this).datagrid("getColumnOption", "_opeate_").formatter("", rowData, index);
    if (html) {
        $("#datagrid_menu").html(html).menu("show", {
            left: e.pageX,
            top: e.pageY
        });
    }
}

/*提示滑块*/
var Tips = {
    _temp: {
        obj: null
    },
    Init: function () {
        $("body").append("<div id=\"sys_tips\">成功</div>");
        Tips._temp.obj = $("#sys_tips");
    },
    Success: function (message) {
        if (!message) {
            message = "操作成功";
        }

        var _this = Tips;

        if (!_this._temp.obj) {
            _this.Init();
        }

        _this._temp.obj.html(message).removeClass();
        var left = ($("body").width() - _this._temp.obj.width()) / 2;
        var top = ($("body").height() - _this._temp.obj.height()) / 2;

        _this._temp.obj.css({ top: top, left: left }).addClass("success");
    },
    Error: function (message) {
        if (!message) {
            message = "操作失败";
        }

        var _this = Tips;

        if (!_this._temp.obj) {
            _this.Init();
        }

        _this._temp.obj.html(message).removeClass();
        var left = ($("body").width() - _this._temp.obj.width()) / 2;
        var top = ($("body").height() - _this._temp.obj.height()) / 2;

        _this._temp.obj.css({ top: top, left: left }).addClass("error");
    },
    Warn: function (message) {
        if (!message) {
            message = "警告";
        }

        var _this = Tips;

        if (!_this._temp.obj) {
            _this.Init();
        }

        _this._temp.obj.html(message).removeClass();
        var left = ($("body").width() - _this._temp.obj.width()) / 2;
        var top = ($("body").height() - _this._temp.obj.height()) / 2;

        _this._temp.obj.css({ top: top, left: left }).addClass("warn");
    }
};

window.alert = function (msg, fn) {
    $.messager.alert("系统提示", msg, "", function () {
        if (fn) { fn(); }
    });
};

$.extend($.fn.textbox.methods, {
    showError: function (jq, message) {
        var tmp = jq.data("textbox").textbox;

        tmp.addClass("text-error").find(".textbox-text").addClass("text-error");

        tmp.tooltip({
            position: "bottom",
            content: message,
            showEvent: "mouseenter",
            showDelay: 50,
            hideDelay: 50
        });
    },
    closeError: function (jq) {
        var tmp = jq.data("textbox").textbox;

        tmp.removeClass("text-error").find(".textbox-text").removeClass("text-error");

        tmp.tooltip({
            showEvent: ""
        });
    }
});

$.extend($.fn.combobox.methods, {
    showError: function (jq, message) {
        var tmp = jq.data("textbox").textbox;

        tmp.addClass("text-error").find(".textbox-text").addClass("text-error");

        tmp.tooltip({
            position: "bottom",
            content: message,
            showEvent: "mouseenter",
            showDelay: 50,
            hideDelay: 50
        });
    },
    closeError: function (jq) {
        var tmp = jq.data("textbox").textbox;

        tmp.removeClass("text-error").find(".textbox-text").removeClass("text-error");

        tmp.tooltip({
            showEvent: ""
        });
    }
});
