/**
 * Created by æž« on 2014/8/15.
 */

var jeasyui = (function () {
    var je = function (d, param) {
        if (typeof(d) == 'object') {
            return je.fn.init(d);
        } else if ((typeof d == 'string') && d.constructor == String) {
            return je.fn.get(d, param);
        } else {
            return this;
        }
    };
    je.fn = je.prototype = {
        clone: function () {
            return this;
        },
        init: function (d) {
            if (this.tags) {
                for (var i in d) {
                    this.tags[i] = d[i];
                }
            } else {
                this.tags = d;
            }
            return this;
        },
        get: function (id, param) {
            var tagName = this.tags[id];
            this.tagName = tagName;
            this.id = id;
            if (typeof param != 'undefined') {
                this.tag = $('#' + id)[tagName](param);
            }
            return this;
        },
        val: function (v) {
            if (v) {
                return $('#' + this.id)[this.tagName]('setValue', v);
            } else {
                return $('#' + this.id)[this.tagName]('getValue');
            }
        },
        text: function (v) {
            if (v) {
                return $('#' + this.id)[this.tagName]('setText', v);
            } else {
                return $('#' + this.id)[this.tagName]('getText');
            }
        },
        add: function (options) {
            $('#' + this.id)[this.tagName]('add', options);
        },
        getSelectedIndex: function () {
            var select = $('#' + this.id)[this.tagName]('getSelected');
            if (this.tagName == 'tabs') {
                return $('#' + this.id)[this.tagName]('getTabIndex', $(select));
            } else if (this.tagName == 'accordion') {
                return $('#' + this.id)[this.tagName]('getPanelIndex', $(select).panel());
            }
        },
        load: function (options) {
            $('#' + this.id)[this.tagName]('load', options);
        },
        reload: function (options) {
            $('#' + this.id)[this.tagName]('reload', options);
        },
        submit: function (options) {
            $('#' + this.id)[this.tagName]('submit', options);
        },
        reset: function (options) {
            $('#' + this.id)[this.tagName]('reset', options);
        },
        validate: function (options) {
            $('#' + this.id)[this.tagName]('validate', options);
        },
        getData: function (options) {
            return $('#' + this.id)[this.tagName]('getData', options);
        },
        getChecked: function (options) {
            return $('#' + this.id)[this.tagName]('getChecked', options);
        },
        clearChecked: function (options) {
            return $('#' + this.id)[this.tagName]('clearChecked', options);
        },
        getSelected: function (options) {
            return $('#' + this.id)[this.tagName]('getSelected', options);
        }
    };
    je.prototype = je.fn;

    je.rendBtn = function () {
        $('.easy-button').linkbutton({plain: true});
    };
    je.valid = function (d) {
        if (typeof d != 'object') {
            try {
                d = eval('(' + d + ')');
            } catch (ex) {
                return false;
            }
        }
        if (!d.success) {
            if (d.error.fieldErrors.length > 0) {
                for (var error in d.error.fieldErrors) {
                    Alert.warning(d.error.fieldErrors[error].message);
                }
            } else {
                Alert.warning(d.error.errorMessage);
            }

            try {
                console.error(d);
            } catch (e) {

            }
            return false;
        } else {
            return true;
        }
    };


    return je;
})();

jeasyui.util = (function () {
    return {
        isNull: function (o) {
            return (o == null || typeof (o) == 'undefined');
        },
        isDate: function (o) {
            return Object.prototype.toString.call(o) === "[object Date]";
        },
        date2String: function (date, format) {
            if (!this.isDate(date) || this.isNull(format)) {
                return "";
            }
            var o = {
                "M+": date.getMonth() + 1, // month
                "d+": date.getDate(), // day
                "H+": date.getHours(), // hour
                "m+": date.getMinutes(), // minute
                "s+": date.getSeconds(), // second
                "q+": Math.floor((date.getMonth() + 3) / 3), // quarter
                "S": date.getMilliseconds()
                // millisecond
            };

            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (date.getFullYear() + "")
                    .substr(4 - RegExp.$1.length));
            }

            for (var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                        : ("00" + o[k]).substr(("" + o[k]).length));
                }
            }
            return format;
        },
        string2Date: function (str, format) {
            if (this.isNull(str) || this.isNull(format)) {
                return null;
            }
            var compare = {
                'y+': 'y',
                'M+': 'M',
                'd+': 'd',
                'H+': 'H',
                'm+': 'm',
                's+': 's'
            };
            var result = {
                'y': '',
                'M': '',
                'd': '',
                'H': '00',
                'm': '00',
                's': '00'
            };
            var tmp = format;
            var tempD = 0;
            for (var k in compare) {
                if (new RegExp('(' + k + ')').test(format)) {
                    var tmpValue = str.substring(tmp.indexOf(RegExp.$1) + tempD, tmp.indexOf(RegExp.$1) + tempD + RegExp.$1.length);
                    tempD += (parseInt("1" + tmpValue).toString().length - 1 - tmpValue.length);
                    result[compare[k]] = parseInt(tmpValue);
                }
            }
            return new Date(result['y'], result['M'] - 1, result['d'], result['H'], result['m'], result['s']);
        }
    };
})();

jeasyui.push = (function () {
    var push = function (channel, onMessage) {
        var socket = $.atmosphere;
        var request = {
            url: channel,
            contentType: "application/json",
            trackMessageLength: true,
            transport: 'websocket'
        };
        request.onMessage = function (response) {
            var message = response.responseBody;
            if (typeof onMessage == 'function')
                onMessage.apply(null, [message]);

        };
        var subSocket = socket.subscribe(request);
        this.socket = subSocket;
        return this;
    };

    push.fn = push.prototype = {
        push: function (message) {
            this.socket.push(message);
        }
    };
    push.prototype = push.fn;
    return push;
})();

jeasyui.dialog = (function () {
    var dialog = function (options) {
        options = $.extend(options, defaultOptions);
        var dialogDiv = $('<div></div>').appendTo($('body'));

        if (options.title == null)
            options.title == "";
        if (options.width == null) {
            var _w = document.body.clientWidth;
            options.width = _w * 0.9;
        }
        if (options.height == null) {
            var _h = document.body.clientHeight;
            options.height = _h * 0.9;
        }

        options.onClose = function () {
            if (typeof options.callback == 'function') {
                var backData = $(dialogDiv).data("callbackdata");
                options.callback.apply(this, [backData]);
            }
            $(dialogDiv).dialog('destroy');
            $(dialogDiv).remove();
        };

        var url = options.href;
        options.href = null;
        if (options.queryParams) {
            var tempUrl = "";
            for (var i in options.queryParams) {
                tempUrl += "&" + i + "=" + options.queryParams[i];
            }
            url += (url.indexOf("?") > 0) ? tempUrl : "?" + tempUrl.substring(1, tempUrl.length);
        }

        if (!options.content) {
            options.content = '<iframe " frameborder="0"  src="' + url
            + '" style="width:100%;height:100%;"></iframe>';
        }
        $(dialogDiv).dialog(options);
        this.wrapper = dialogDiv;
        return this;
    };

    var defaultOptions = {
        modal: true,
        maximizable: true,
        cache: false
    };

    dialog.fn = dialog.prototype = {
        close: function (data) {
            $(this.wrapper).data("callbackdata", data);
            $(this.wrapper).dialog('close');
        }
    };
    dialog.prototype = dialog.fn;
    return dialog;
})();

var Frame = {
    dialogs: new Array()
};
Frame.dialog = (function () {
    return {
        open: function (options) {
            if (typeof options.top == 'undefined' || options.top) {
                return window.parent.Frame.dialog.openDialog(options);
            } else {
                return Frame.dialog.openDialog(options);
            }
        },
        openDialog: function (options) {
            var dialog = new jeasyui.dialog(options);
            Frame.dialogs.unshift(dialog);
            return dialog;
        },
        close: function (data) {
            window.parent.Frame.dialogs.shift().close(data);
        }
    };
})();


/**
 * Some component extension
 *
 */

/**
 * fileUpload
 */
(function () {
    $.fn.fileUpload = function (options, param) {
        if (typeof options == 'string') {
            return $.fn.fileUpload.methods[options](this, param);
        }
        var dataOptions = {};
        if (typeof $(this).data('options') != 'undefined') {
            dataOptions = eval("({" + $(this).data('options') + "})");
        }
        options = $.extend({}, $.fn.fileUpload.defaults, dataOptions, options);
        return init(this, options);
    };

    $.fn.fileUpload.methods = {};

    $.fn.fileUpload.defaults = {
        chooseBtnText: 'é€‰æ‹©æ–‡ä»¶',
        uploadBtnText: 'ä¸Šä¼ ',
        chooseBtnCls: '',
        uploadBtnCls: '',
        maxFileSize: '10mb',
        filters: null,
        basePath: '../plupload',
        multiFile: true,
        params: {}
    };

    function init(ele, options) {
        var classStyle = $(ele).attr("class");
        var style = $(ele).attr('style');
        var id = $(ele).attr("id");
        var wrapper = $('<span></span>')[0];
        $(wrapper).attr("id", id).attr('style', style).addClass("jeasyui-upload").addClass(classStyle);
        $(ele).after(wrapper);
        $(ele).remove();
        var chooseBtn = $('<a href="#" ></a>')[0];
        var uploadBtn = $('<a href="#" ></a>')[0];
        $(chooseBtn).text(options.chooseBtnText).addClass(options.chooseBtnCls);
        $(uploadBtn).text(options.uploadBtnText).addClass(options.uploadBtnCls);
        $(wrapper).append(chooseBtn);
        $(wrapper).append(uploadBtn);
        var files = $('<span>æš‚æ— æ–‡ä»¶</span>').addClass("jeasyui-upload-files");
        $(wrapper).append(files);
        var errorMsg = $('<span></span>').addClass("jeasyui-upload-msg");
        $(wrapper).append(errorMsg);

        $(uploadBtn).linkbutton({iconCls: "fa fa-upload"});
        $(chooseBtn).linkbutton({iconCls: "fa fa-folder-open"});

        var uploadOptions = {
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: chooseBtn, // you can pass in id...
            container: wrapper,
            max_file_size: options.maxFileSize,
            url: options.url,
            flash_swf_url: options.basePath + "/Moxie.swf",
            silverlight_xap_url: options.basePath + "/Moxie.xap",
            multi_selection: options.multiFile,
            multipart_params: options.params,
            init: {
                BeforeUpload: function () {
                    if (typeof options.BeforeUpload == 'function') {
                        options.BeforeUpload.apply(this, arguments);
                    }
                },
                FileUploaded: function () {
                    if (typeof options.FileUploaded == 'function') {
                        options.FileUploaded.apply(this, arguments);
                    }
                },
                PostInit: function () {
                    if (typeof options.PostInit == 'function') {
                        options.PostInit.apply(this, arguments);
                    } else {
                        $(files).html("æš‚æ— æ–‡ä»¶");
                        $(uploadBtn)[0].onclick = function () {
                            uploader.start();
                            return false;
                        };
                    }
                },

                FilesAdded: function (up, _files) {
                    if (typeof options.FilesAdded == 'function') {
                        options.FilesAdded.apply(this, arguments);
                    } else {
                        if (uploader.files.length > 0) {
                            $(files).empty()
                        } else {
                            $(files).html("æš‚æ— æ–‡ä»¶");
                        }
                        plupload.each(_files, function (_file) {
                            var fileDiv = $('<div id="' + _file.id + '"><a href="#" title="åˆ é™¤"><i class="fa fa-close"></i></a> ' + _file.name + ' (' + plupload.formatSize(_file.size) + ') <b></b></div>');
                            $(files).append(fileDiv);
                            $(fileDiv).find("a").on('click', function () {
                                var fileId = $(this).parent().attr("id");
                                var f = uploader.getFile(fileId);
                                uploader.removeFile(f);
                                $(this).parent().remove();
                                if (uploader.getFiles().length == 0) {
                                    $(files).html("æš‚æ— æ–‡ä»¶");
                                }
                            });
                        });
                    }
                },

                UploadProgress: function (up, file) {
                    if (typeof options.UploadProgress == 'function') {
                        options.UploadProgress.apply(this, arguments);
                    } else {
                        $("#" + file.id)[0].getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
                    }
                },
                Error: function (up, err) {
                    if (typeof options.Error == 'function') {
                        options.Error.apply(this, arguments);
                    } else {
                        $(errorMsg).html(err);
                    }
                }
            }
        };

        if (options.filters) {
            uploadOptions.filters = options.filters;
        }

        var uploader = new plupload.Uploader(uploadOptions);
        uploader.init();
    }
})(jQuery);

/**
 * selectBooleanCheckbox
 */

(function () {

    function init(ele, options) {
        var span = $("<span></span>").addClass($(ele).attr("class")).css("display", "inline-block").css("height", "24px");
        $(ele).wrap(span);
        $(ele).change(function () {
            $(this).val($(ele)[0].checked ? true : false);
        });
    }

    $.fn.selectBooleanCheckbox = function (options, param) {
        if (typeof options == 'string') {
            return $.fn.selectManyCheckbox.methods[options](this, param);
        }
        var dataOptions = eval("({" + $(this).data('options') + "})");
        options = $.extend({}, $.fn.selectManyCheckbox.defaults, dataOptions, options);
        return init(this, options);
    };

    $.fn.selectBooleanCheckbox.methods = {
        setValue: function (ele, param) {
            $(ele)[0].checked = param ? true : false;
        },
        getValue: function (ele) {
            return $(ele)[0].checked;
        }
    };

    $.fn.selectBooleanCheckbox.defaults = {};
})(jQuery);

/**
 * selectManyCheckbox
 */
(function () {

    var componentName = "selectManyCheckbox";

    function init(target, options) {

        var id = $(target).attr("id");
        var style = $(target).attr("style") || '';
        var classStyle = $(target).attr("class");
        $(target).removeAttr("name");

        var wrapper = $('<span class="' + componentName + '"></span>');
        if (classStyle) {
            wrapper.addClass(classStyle);
        }

        var data = getDataFromOption(target).concat(options.data);

        if (options.url) {
            getDataFromUrl(options.url, function (d) {
                handleNewHtml(data.concat(d), wrapper, target, options);
            });
        } else {
            handleNewHtml(data, wrapper, target, options);
        }
        return this;
    }

    function handleNewHtml(data, wrapper, target, options) {

        var name = $(target).attr("name") || $(target).attr("id");
        var table = $('<table></table>');
        wrapper.append(table);
        $(target).after(wrapper);
        $(target).hide();

        var tr = $("<tr></tr>");
        $(data).each(function (i) {
            var value = this[options.valueField] || this['value'];
            var text = this[options.textField] || this['text'];
            var checkbox = '<td><input type="checkbox" name="' + name + '" value="' + value + '" ' + (this['selected'] ? 'checked="checked"' : '') + '></td>';
            tr.append($(checkbox));
            tr.append('<td class="selectManyCheckbox-text">' + text + '</td>');
            $(checkbox).click(function () {
                var input = $(this).find('input')[0];
                if (input.checked) {
                    input.checked = false;
                } else {
                    input.checked = true;
                }
            });
            i = i + 1;
            if (options.columns > 0 && i % options.columns == 0) {
                table.append(tr);
                tr = $("<tr></tr>");
            }
            if (i == data.length) {
                table.append(tr);
            }
        });

        if (options.checkAllable) {
            var checkAllSpan = $('<span></span>').addClass('selectManyCheckbox-checkAll');
            var checkAllA = $('<a href="#">å…¨é€‰</a>').bind('click', function () {
                $(this).parent().parent().find('input').each(function () {
                    $(this)[0].checked = true;
                });
            });
            var checkNoA = $('<a href="#">å…¨ä¸é€‰</a>').bind('click', function () {
                $(this).parent().parent().find('input').each(function () {
                    $(this)[0].checked = false;
                });
            });
            checkAllSpan.append(checkAllA).append("/").append(checkNoA);
            wrapper.append(checkAllSpan);
        }
    }

    function getDataFromOption(elem) {
        var data = [];
        $(elem).find("option").each(function () {
            var d = {value: $(this).attr('value'), text: $(this).text(), selected: $(this).attr('selected')};
            data.push(d);
        });
        return data;
    }

    function getDataFromUrl(url, callback) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: "JSON",
            success: function (d) {
                callback.apply(this, [d]);
            }
        });
    }


    $.fn.selectManyCheckbox = function (options, param) {
        if (typeof options == 'string') {
            return $.fn.selectManyCheckbox.methods[options](this, param);
        }
        var dataOptions = eval("({" + $(this).data('options') + "})");
        options = $.extend({}, $.fn.selectManyCheckbox.defaults, dataOptions, options);
        return init(this, options);
    };

    $.fn.selectManyCheckbox.methods = {
        setValues: function (elem, params) {
            var jWrap = $(elem).next();
            for (var i = 0; i < params.length; i++) {
                jWrap.find('input').each(function () {
                    var value = $(this).val();
                    if (value == params[i]) {
                        $(this)[0].checked = true;
                    }
                });
            }
        },
        getValues: function (elem) {
            var jWrap = $(elem).next();
            var values = [];
            jWrap.find('input').each(function () {
                var value = $(this).val();
                var checked = $(this)[0].checked;
                if (checked) {
                    values.push(value);
                }
            });
            return values;
        },
        checkAll: function (elem) {
            var jWrap = $(elem).next();
            jWrap.find('input').each(function () {
                $(this)[0].checked = true;
            });
        }
    };

    $.fn.selectManyCheckbox.defaults = {
        width: 'auto',
        height: '24',
        columns: 0,
        data: [],
        url: null,
        valueField: "value",
        textField: "text",
        checkAllable: true
    };
})(jQuery);


/**
 *
 */
(function () {

    var componentName = "selectOneRadio";

    function init(target, options) {

        var id = $(target).attr("id");
        var style = $(target).attr("style") || '';
        var classStyle = $(target).attr("class");
        $(target).removeAttr("name");

        var wrapper = $('<span class="' + componentName + '"></span>');
        if (classStyle) {
            wrapper.addClass(classStyle);
        }

        var data = getDataFromOption(target).concat(options.data);

        if (options.url) {
            getDataFromUrl(options.url, function (d) {
                handleNewHtml(data.concat(d), wrapper, target, options);
                $(target).selectOneRadio('setValue', $(target).attr("value"));
            });
        } else {
            handleNewHtml(data, wrapper, target, options);
            $(target).selectOneRadio('setValue', $(target).attr("value"));
        }

        return $(target);
    }

    function handleNewHtml(data, wrapper, target, options) {

        var name = $(target).attr("name") || $(target).attr("id");
        var table = $('<table></table>');
        wrapper.append(table);
        $(target).after(wrapper);
        $(target).hide();

        var tr = $("<tr></tr>");
        $(data).each(function (i) {
            var value = this[options.valueField] || this['value'];
            var text = this[options.textField] || this['text'];
            var checkbox = '<td><input type="radio" name="' + name + '" value="' + value + '" ' + (this['selected'] ? 'checked="checked"' : '') + '></td>';
            tr.append($(checkbox));
            tr.append('<td class="selectOneRadio-text">' + text + '</td>');
            $(checkbox).click(function () {
                var input = $(this).find('input')[0];
                if (input.checked) {
                    input.checked = false;
                } else {
                    input.checked = true;
                }
            });
            i = i + 1;
            if (options.columns > 0 && i % options.columns == 0) {
                table.append(tr);
                tr = $("<tr></tr>");
            }
            if (i == data.length) {
                table.append(tr);
            }
        });
    }

    function getDataFromOption(elem) {
        var data = [];
        $(elem).find("option").each(function () {
            var d = {value: $(this).attr('value'), text: $(this).text(), selected: $(this).attr('selected')};
            data.push(d);
        });
        return data;
    }

    function getDataFromUrl(url, callback) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: "JSON",
            success: function (d) {
                callback.apply(this, [d]);
            }
        });
    }


    $.fn.selectOneRadio = function (options, param) {
        if (typeof options == 'string') {
            return $.fn.selectOneRadio.methods[options](this, param);
        }

        var dataOptions = $(this).data('options') ? eval("({" + $(this).data('options') + "})") : {};
        options = $.extend({}, $.fn.selectOneRadio.defaults, dataOptions, options);
        return init(this, options);
    };

    $.fn.selectOneRadio.methods = {
        setValue: function (elem, param) {
            var jWrap = $(elem).next();
            jWrap.find('input').each(function () {
                var value = $(this).val();
                if (value == param) {
                    $(this)[0].checked = true;
                }
            });
        },
        getValue: function (elem) {
            var jWrap = $(elem).next();
            var values = [];
            jWrap.find('input').each(function () {
                var value = $(this).val();
                var checked = $(this)[0].checked;
                if (checked) {
                    return value;
                }
            });
        }
    };

    $.fn.selectOneRadio.defaults = {
        width: 'auto',
        height: '24',
        columns: 0,
        data: [],
        url: null,
        valueField: "value",
        textField: "text"
    };
})(jQuery);

(function () {


    function init(ele, options) {
        var legend = $(ele).find('legend')[0];
        var eleW = $(ele).width();
        var style = $(ele).attr('style');
        $(legend).addClass(options.legendCls);
        var content = $(ele).find('.jeasyui-fieldset-content')[0];
        $(content).attr('style', style);
        $(content).width(eleW - 22);
        if (options.closed) {
            $(content).hide();
            $(content).addClass('jeasyui-closed');
        }
        if (options.toggleable) {
            $(legend).click(function () {
                $(content).toggle(options.toggleSpeed);
                $(content).toggleClass("jeasyui-closed");
                if ($(content).hasClass("jeasyui-closed")) {
                    $(ele).fieldset('close');
                } else {
                    $(ele).fieldset('open');
                }
            });
        }
        return this;
    }

    $.fn.fieldset = function (options, param) {
        if (typeof options == 'string') {
            return $.fn.fieldset.methods[options](this, param, options);
        }
        var dataOptions = $(this).data('options') ? eval("({" + $(this).data('options') + "})") : {};
        options = $.extend({}, $.fn.fieldset.defaults, dataOptions, options);
        $(this).data('options', options);
        return init(this, options);
    };


    $.fn.fieldset.defaults = {
        onOpen: null,
        onClose: null,
        closed: false,
        toggleable: false,
        toggleSpeed: 500,
        legendCls: 'c-info'
    };

    $.fn.fieldset.methods = {
        open: function (ele, force) {
            var options = $.data(ele).options;
            $(ele).find("legend").find("i").removeClass().addClass("fa").addClass("fa-minus");
            if (typeof force != 'undefined' && !force && typeof options.onClose == 'function') {
                options.onOpen.apply(this);
            }
        },
        close: function (ele, force) {
            var options = $.data(ele).options;
            $(ele).find("legend").find("i").removeClass().addClass("fa").addClass("fa-plus");
            if (typeof force != 'undefined' && !force && typeof options.onClose == 'function') {
                options.onClose.apply(this);
            }
        }
    };

})(jQuery);


var Alert = {};

Alert.info = function (msg, title, event) {
    var t = title ? title : 'ç³»ç»Ÿæ¶ˆæ¯';
    if (event == 'refresh') {
        $.messager.alert(t, msg, 'info', function () {
            window.location = window.location.href.replace("#", "");
        });
    } else if (event == 'close') {
        $.messager.alert(t, msg, 'info', function () {
            window.close();
        });
    } else if (typeof (event) == 'function') {
        $.messager.alert(t, msg, 'info', event);
    } else {
        $.messager.alert(t, msg, 'info');
    }
};

Alert.error = function (msg, title, event) {
    var t = title ? title : 'ç³»ç»Ÿæ¶ˆæ¯';
    if (event == 'refresh') {
        $.messager.alert(t, msg, 'error', function () {
            window.location = window.location.href.replace("#", "");
        });
    } else if (event == 'close') {
        $.messager.alert(t, msg, 'error', function () {
            window.close();
        });
    } else if (typeof (event) == 'function') {
        $.messager.alert(t, msg, 'error', event);
    } else {
        $.messager.alert(t, msg, 'error');
    }
};

Alert.question = function (msg, title, event) {
    var t = title ? title : 'ç³»ç»Ÿæ¶ˆæ¯';
    if (event == 'refresh') {
        $.messager.alert(t, msg, 'question', function () {
            window.location = window.location.href.replace("#", "");
        });
    } else if (event == 'close') {
        $.messager.alert(t, msg, 'question', function () {
            window.close();
        });
    } else if (typeof (event) == 'function') {
        $.messager.alert(t, msg, 'question', event);
    } else {
        $.messager.alert(t, msg, 'question');
    }
};

Alert.warning = function (msg, title, event) {
    var t = title ? title : 'ç³»ç»Ÿæ¶ˆæ¯';
    if (event == 'refresh') {
        $.messager.alert(t, msg, 'warning', function () {
            window.location = window.location.href.replace("#", "");
        });
    } else if (event == 'close') {
        $.messager.alert(t, msg, 'warning', function () {
            window.close();
        });
    } else if (typeof (event) == 'warning') {
        $.messager.alert(t, msg, 'question', event);
    } else {
        $.messager.alert(t, msg, 'warning');
    }
};
Alert.confirm = function (msg, event) {
    $.messager.confirm('ç³»ç»Ÿæ¶ˆæ¯', msg, event);
};
