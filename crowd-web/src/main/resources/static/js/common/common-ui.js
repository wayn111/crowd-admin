/**
 * 绑定向上滚动元素
 */
(function ($) {
    'use strict';
    $.fn.toTop = function (opt) {
        var elem = this;
        var win = $(window);
        var doc = $('html, body');
        var options = $.extend({
            autohide: true,
            offset: 50,
            speed: 500,
            position: true,
            right: 15,
            bottom: 5
        }, opt);
        elem.css({
            'cursor': 'pointer'
        });
        if (options.autohide) {
            elem.css('display', 'none');
        }
        if (options.position) {
            elem.css({
                'position': 'fixed',
                'right': options.right,
                'bottom': options.bottom,
            });
        }
        elem.click(function () {
            doc.animate({
                scrollTop: 0
            }, options.speed);
        });
        win.scroll(function () {
            var scrolling = win.scrollTop();
            if (options.autohide) {
                if (scrolling > options.offset) {
                    elem.fadeIn(options.speed);
                } else elem.fadeOut(options.speed);
            }
        });
    };
})(jQuery);

/**
 * 绑定select2控件
 */
function select2Init(selector, config) {
    var deFaultConfig = {
        width: '100px',
        data: [{
            id: '',
            text: '全部'
        }, {
            id: 1,
            text: '启用'
        }, {
            id: -1,
            text: '禁用'
        }]
    };
    deFaultConfig = $.extend({}, deFaultConfig, config);
    $(selector).select2(deFaultConfig);

    treeSearchInit();
}


/**
 * 通用 查询框 重置函数
 * @returns
 */
function selectReset() {
    $('form').first()[0].reset();
    $('.js-example-basic-single').val(null).trigger('change');
    $('.js-example-basic-multi').val(null).trigger('change');
}

/**
 * 初始化bootstrapSwitch选择控件
 * @param module
 * @param state
 * @returns
 */
function switchInit(module, config) {
    /*if ('boolean' != typeof state) {
        throw new Error('state must be boolean type');
    }*/
    var defaultConfig = {
        onText: '启用',
        offText: '禁用',
        state: true,
        onColor: "success",
        size: 'small',
        onSwitchChange: function (event, state) {
            if (state) {
                $('#' + module).val('1');
            } else {
                $('#' + module
                ).val('-1');
            }
        }
    };
    if (config) {
        defaultConfig = $.extend({}, defaultConfig, config);
    }
    $("[name='" + module + "Swicth']").bootstrapSwitch(defaultConfig);
}

/**
 * 根据菜单url和菜单name加载右侧tab页
 */
function menuItemCreate(url, name) {
    //m 确保data-index不重复
    var o = url, m = Math.ceil(Math.random() * 1000) + 1000, l = name, k = true;
    if (o == undefined || $.trim(o).length == 0) {
        return false
    }
    var topWindow = $(window.top.document);
    $(".J_menuTab", topWindow).each(function () {
        if ($(this).data("id") == o) {
            if (!$(this).hasClass("active")) {
                $(this).addClass("active").siblings(".J_menuTab").removeClass("active");
                $('.page-tabs-content', topWindow).addClass('layui-anim layui-anim-up');
                $(".J_mainContent .J_iframe", topWindow).each(function () {
                    if ($(this).data("id") == o) {
                        $(this).show().siblings(".J_iframe").hide();
                        return false
                    }
                })
            }
            k = false;
            return false
        }
    });
    if (k) {
        var index = window.top.layer.msg('正在加载中，请稍后...', {
            icon: 16,
            shade: 0.1
        });
        var p = '<a href="javascript:;" class="active J_menuTab" data-id="' + o + '">' + l + ' <i class="fa fa-times-circle"></i></a>';
        $(".J_menuTab", topWindow).removeClass("active");
        var n = '<iframe class="J_iframe" name="iframe' + m + '" width="100%" height="100%" src="' + o + '" frameborder="0" data-id="' + o + '" seamless></iframe>';
        $(".J_mainContent", topWindow).find("iframe.J_iframe").hide().parents(".J_mainContent").append(n);
        $('.J_mainContent iframe.J_iframe:visible', topWindow).load(function () {
            window.top.layer.close(index);
        });
        $(".J_menuTabs .page-tabs-content", topWindow).append(p);
        $('.page-tabs-content', topWindow).addClass('layui-anim layui-anim-up')
    }
    return false
}

/**
 * 关闭当前激活tab
 */
function activeTabClose() {
    var topWindow = $(window.top.document);
    $('.J_menuTabs .J_menuTab.active i', topWindow).trigger('click');
}

/**
 * 绑定laydate控件
 */
function layDateQuery() {
    $('.select-time').each(function (index, item) {
        var startDate = laydate.render({
            elem: $('input[name="startTime"]', item)[0],
            max: $('#endTime').val(),
            theme: 'molv',
            trigger: 'click',
            done: function (value, date) {
                // 结束时间大于开始时间
                if (value !== '') {
                    endDate.config.min.year = date.year;
                    endDate.config.min.month = date.month - 1;
                    endDate.config.min.date = date.date;
                } else {
                    endDate.config.min.year = '';
                    endDate.config.min.month = '';
                    endDate.config.min.date = '';
                }
            }
        });
        var endDate = laydate.render({
            elem: $('input[name="endTime"]', item)[0],
            min: $('#startTime').val(),
            theme: 'molv',
            trigger: 'click',
            done: function (value, date) {
                // 开始时间小于结束时间
                if (value !== '') {
                    startDate.config.max.year = date.year;
                    startDate.config.max.month = date.month - 1;
                    startDate.config.max.date = date.date;
                } else {
                    startDate.config.max.year = '';
                    startDate.config.max.month = '';
                    startDate.config.max.date = '';
                }
            }
        });
    });
    if ($(".select-time").length > 0) {
        var startDate = laydate.render({
            elem: '#startTime',
            max: $('#endTime').val(),
            theme: 'molv',
            trigger: 'click',
            done: function (value, date) {
                // 结束时间大于开始时间
                if (value !== '') {
                    endDate.config.min.year = date.year;
                    endDate.config.min.month = date.month - 1;
                    endDate.config.min.date = date.date;
                } else {
                    endDate.config.min.year = '';
                    endDate.config.min.month = '';
                    endDate.config.min.date = '';
                }
            }
        });
        var endDate = laydate.render({
            elem: '#endTime',
            min: $('#startTime').val(),
            theme: 'molv',
            trigger: 'click',
            done: function (value, date) {
                // 开始时间小于结束时间
                if (value !== '') {
                    startDate.config.max.year = date.year;
                    startDate.config.max.month = date.month - 1;
                    startDate.config.max.date = date.date;
                } else {
                    startDate.config.max.year = '';
                    startDate.config.max.month = '';
                    startDate.config.max.date = '';
                }
            }
        });
    }
}

/**
 * 初始化树搜索控件
 * @returns
 */
function treeSearchInit() {
    $('#keyword').on('keyup', function () {
        search();
    });
}

/**
 * 绑定文本编辑器
 * @param selector
 * @param config
 */
function textareaEditorInit(selector, config) {
    var defaultConfig = {
        lang: 'zh-CN',
        dialogsInBody: true,
        placeholder: '请输入内容',
        tabsize: 2,
        height: 200
    };
    defaultConfig = $.extend({}, defaultConfig, config);
    $(selector).summernote(defaultConfig);
}

// 上传文件
function summernoteSendFile(file, obj) {
    var data = new FormData();
    data.append('file', file);
    $.ajax({
        type: 'POST',
        url: _ctx + '/common/upload',
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json',
        success: function (result) {
            if (result.code == 100) {
                $(obj).summernote('editor.insertImage', result.map.url, result.map.fileName);
            } else {
                layer.alert(result.msg);
            }
        },
        error: function (error) {
            layer.alert('图片上传失败。');
        }
    });
}

/**
 * 生成bootstrap-table表格序号
 * @param selector
 * @param index
 * @returns {*}
 */
function generatorTableSequence(selector, index) {
    // 通过表的#id 可以得到每页多少条
    var pageSize = $(selector).bootstrapTable('getOptions').pageSize;
    // 通过表的#id 可以得到当前第几页
    var pageNumber = $(selector).bootstrapTable('getOptions').pageNumber;
    // 返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
    return pageSize * (pageNumber - 1) + index + 1;
}

/**
 * 表单查看,input，select，textarea不能更改
 * @param win
 */
function formView(win) {
    $('input[onclick]', win).off().removeAttr('onclick');
    $('input[name],textarea[name]', win).attr('readOnly', true).attr('disabled', true);
    $('input[name]', win).each(function (index, item) {
        if ($(item).attr('name').indexOf('Time') > 1) {
            $(item).attr('disabled', true);
        }
    });
    $('select[name]', win).attr('disabled', true);
    $('input[type=radio][disabled]', win).css('cursor', 'default');
}

/**
 * 通用导出方法
 */
function exportData(exportUrl, formId, filename) {
    var req = new XMLHttpRequest();
    req.open("post", exportUrl);
    req.responseType = "blob";
    //监听进度事件
    req.addEventListener("progress", function (evt, a, b) {
        if (evt.lengthComputable) {
            var percentComplete = evt.loaded / evt.total;
            $("#process").css({'width': percentComplete * 100 + "%"})
            $("#processText").text(percentComplete * 100 + "%")
            console.log(percentComplete);
            if (percentComplete >= 1) {
                setTimeout(() => {
                    layer.closeAll();
                }, 2000);
            }
        }
    }, false);
    layer.closeAll();
    layer.open({
        type: 1,
        title: '正在下载，请稍后...',
        icon: 16,
        shade: 0.01,
        time: false,
        area: ['240px', '75px'],
        content: `<div class="progress progress-striped active" style="position: relative;top: 15%;width: 95%;display: inline-flex;margin: 0 0 0 5px;">
                            <div style="width: 0%" id="process" class="progress-bar progress-bar-success">
                                <span id="processText" style="color: #262c2a">0%</span>
                            </div>
                        </div>` //这里content是一个普通的String
    });
    req.onreadystatechange = function () {
        if (req.readyState === 4) {
            if (req.status === 200) {
                if (typeof window.chrome !== 'undefined') {
                    // Chrome version
                    var link = document.createElement('a');
                    link.href = window.URL.createObjectURL(req.response);
                    link.download = filename;
                    link.click();
                } else if (typeof window.navigator.msSaveBlob !== 'undefined') {
                    // IE version
                    var blob = new Blob([req.response], {type: 'application/force-download'});
                    window.navigator.msSaveBlob(blob, filename);
                } else {
                    // Firefox version
                    var file = new File([req.response], filename, {type: 'application/force-download'});
                    window.open(URL.createObjectURL(file));
                }
            } else {
                layer.close(index);
                layer.alert('下载失败！');
            }

        }
    };
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    req.send($('#' + formId + '').serialize());
}

/**
 * 使用tooltip优化长文本显示，配合<br>
 * $("[data-toggle='tooltip']").tooltip();<br>
 * 使用
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function toolTip(value, row, index) {
    var nameString;
    if (value.length > 9) {
        nameString = value.substring(0, 9) + '...';
    } else {
        nameString = value;
    }
    return '<a href="#" data-toggle="tooltip" data-placement="top" title=' + value + '>' + nameString + '</a>';
}
