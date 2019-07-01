$(function () {
    $.fn.select2.defaults.set("language", "zh-CN");
    $.fn.select2.defaults.set("theme", "bootstrap");

    // laydate 时间控件绑定
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
});

/**
 * 初始化select2控件
 */
function select2Init(selector, config) {
    let deFaultConfig = {
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
}


/**
 * 通用 查询框 重置函数
 * @returns
 */
function selectReset() {
    $('form').first()[0].reset();
    $('.js-example-basic-single').val(null).trigger('change');
}

/**
 * 初始化选择控件
 * @param module
 * @param state
 * @returns
 */
function switchInit(module, state = true) {
    /*if ('boolean' != typeof state) {
        throw new Error('state must be boolean type');
    }*/
    $("[name='" + module + "StateSwicth']").bootstrapSwitch({
        state: state,
        onText: "启用",
        offText: "禁用",
        onColor: "success",
        onSwitchChange: function (event, state) {
            if (state) {
                $('#' + module + 'State').val('1');
            } else {
                $('#' + module + 'State').val('-1');
            }
        }
    });
}

/**
 * 根据菜单url和菜单name加载右侧tab页
 */
function menuItemCreate(url, name) {
    //m 确保data-index不重复
    let o = url, m = Math.ceil(Math.random() * 1000) + 1000, l = name, k = true;
    let topWindow = $(window.top.document);
    if (o == undefined || $.trim(o).length == 0) {
        return false
    }
    $(".J_menuTab", topWindow).each(function () {
        if ($(this).data("id") == o) {
            if (!$(this).hasClass("active")) {
                $(this).addClass("active").siblings(".J_menuTab").removeClass("active");
                $('.page-tabs-content', topWindow).addClass('layui-anim layui-anim-up')
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
        let p = '<a href="javascript:;" class="active J_menuTab" data-id="' + o + '">' + l + ' <i class="fa fa-times-circle"></i></a>';
        $(".J_menuTab", topWindow).removeClass("active");
        let n = '<iframe class="J_iframe" name="iframe' + m + '" width="100%" height="100%" src="' + o + '" frameborder="0" data-id="' + o + '" seamless></iframe>';
        $(".J_mainContent", topWindow).find("iframe.J_iframe").hide().parents(".J_mainContent").append(n);
        $(".J_menuTabs .page-tabs-content", topWindow).append(p);
        $('.page-tabs-content', topWindow).addClass('layui-anim layui-anim-up')
    }
    return false
}