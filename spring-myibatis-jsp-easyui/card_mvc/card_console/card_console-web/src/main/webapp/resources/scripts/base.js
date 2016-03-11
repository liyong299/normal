/*ajax方法*/
/*
 * URL：para.url
 * 参数：para.para
 * 是否异步：para.async
 * 成功运行：para.success(list, total)
 * 错误运行：para.error()
 * 完成运行：para.complete()
 * */
$.ajaxRequest = function (para) {
    //messagebox.ShowWait();
	if(!para.url){
		console.error("ajaxRequest方法需要url参数!");
		return false;
	}
    $.ajax({
        type: 'post',
        cache: false,
        dataType: 'json',
        url: para.url,
        //data: JSON.stringify(para.para),
        data: para.para,
        async: para.async != undefined ? para.async : true,
        success: function (result) {
            //if (result.isSuccess) {
                para.success(result);
            //}
            //else {
               // Tips.Error(result.message);
            //}
        },
        error: function () {
            if (para.error) {
                para.error();
            }
        },
        complete: function () {
            if (para.complete) {
                para.complete();
            }
            //messagebox.CloseWait();
        }
    });
};

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

/*
 * 扩展validatebox验证
 * 
 * 
 */
$.extend($.fn.validatebox.defaults.rules, {
    //验证汉子
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只能输入汉字'
    },
    //移动手机号码验证
    mobile: {//value值为文本框中的值
        validator: function (value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '输入手机号码格式不准确.'
    },
    //国内邮编验证
    zipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字.'
    },
    //用户账号验证(只能包括 _ 数字 字母) 
    account: {//param的值为[]中值
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: ''
    },
    //大于0的整数
   pint:{
	   validator: function (value) {
           var reg = /^[1-9]\d*$/;
           return reg.test(value);
       },
       message: '必须输入大于0的整数.'
   },
 //大于0的数字
   pnumber:{
	   validator: function (value) {
           var reg = /^[1-9]\d*$|^[0-9]\d*\.\d*$/;
           return reg.test(value);
       },
       message: '必须输入大于0的数字.'
   },
   validate: {
       validator: function (value, param) {
           var result = true;
           if (isNaN(value)) {
               $.fn.validatebox.defaults.rules.validate.message = "必须是数字";
               result = false;
           }
           else if (parseFloat(value) <= 0 ) {
               $.fn.validatebox.defaults.rules.validate.message = "必须大于0";
               result = false;
           }

           return result;
       }
   }
});

Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 