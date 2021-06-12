/**
 * 首页方法封装处理
 */
layer.config({
    extend: 'moon/style.css',
    skin: 'layer-ext-moon'
});

/**
 * 是否是手机环境
 */
function isMobileEnv() {
    return navigator.userAgent.match(/(Android|iPhone|SymbianOS|Windows Phone|iPad|iPod)/i);
}

/**
 * 导航栏切换
 */
function NavToggle() {
    $(".navbar-minimalize").trigger("click")
}

/**
 * 左侧菜单系统名、头像动画
 */
function SmoothlyMenu() {
    if ($("body").hasClass("mini-navbar")) {
        $("body").hasClass("fixed-sidebar") ? ($("#side-menu").hide(), setTimeout(function () {
            $('.wayn-h3,#user-state').toggle();
            $('.wayn-profile').removeClass('bounceInDown');
            $("#side-menu").fadeIn(500)
        }, 300)) : $("#side-menu").removeAttr("style");
    } else {
        $("#side-menu").hide(), setTimeout(function () {
            $('.wayn-h3,#user-state').toggle();
            $('.wayn-profile').removeClass('fadeInDown').addClass('bounceInDown');
            $("#side-menu").fadeIn(500)
        }, 100);
    }
}

function localStorageSupport() {
    return "localStorage" in window && null !== window.localStorage
}

$(document).ready(function () {

    $("#side-menu").metisMenu();

    $(function () {
        $(".sidebar-collapse").slimScroll({height: "100%", railOpacity: .9, alwaysVisible: !1})
    });

    $(".navbar-minimalize").click(function () {
        $("body").toggleClass("mini-navbar"), SmoothlyMenu()
    });


    $("#side-menu>li").click(function () {
        $("body").hasClass("mini-navbar") && NavToggle()
    });

    $("#side-menu>li li a").click(function () {
        $(window).width() < 769 && NavToggle()
    });
    $(".nav-close").click(NavToggle), /(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent) && $("#content-main").css("overflow-y", "auto")
});

$(function () {
    if ($("#fixednavbar").click(function () {
        $("#fixednavbar").is(":checked") ? ($(".navbar-static-top").removeClass("navbar-static-top").addClass("navbar-fixed-top"), $("body").removeClass("boxed-layout"), $("body").addClass("fixed-nav"), $("#boxedlayout").prop("checked", !1), localStorageSupport && localStorage.setItem("boxedlayout", "off"), localStorageSupport && localStorage.setItem("fixednavbar", "on")) : ($(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top"), $("body").removeClass("fixed-nav"), localStorageSupport && localStorage.setItem("fixednavbar", "off"))
    }), $("#collapsemenu").click(function () {
        $("#collapsemenu").is(":checked") ? ($("body").addClass("mini-navbar"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("collapse_menu", "on")) : ($("body").removeClass("mini-navbar"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("collapse_menu", "off"))
    }), $("#boxedlayout").click(function () {
        $("#boxedlayout").is(":checked") ? ($("body").addClass("boxed-layout"), $("#fixednavbar").prop("checked", !1), $(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top"), $("body").removeClass("fixed-nav"), localStorageSupport && localStorage.setItem("fixednavbar", "off"), localStorageSupport && localStorage.setItem("boxedlayout", "on")) : ($("body").removeClass("boxed-layout"), localStorageSupport && localStorage.setItem("boxedlayout", "off"))
    }), $(".s-skin-0").click(function () {
        return $("body").removeClass("skin-1"), $("body").removeClass("skin-2"), $("body").removeClass("skin-3"), !1
    }), $(".s-skin-1").click(function () {
        return $("body").removeClass("skin-2"), $("body").removeClass("skin-3"), $("body").addClass("skin-1"), !1
    }), $(".s-skin-3").click(function () {
        return $("body").removeClass("skin-1"), $("body").removeClass("skin-2"), $("body").addClass("skin-3"), !1
    }), localStorageSupport) {
        var e = localStorage.getItem("collapse_menu"), a = localStorage.getItem("fixednavbar"),
            o = localStorage.getItem("boxedlayout");
        "on" == e && $("#collapsemenu").prop("checked", "checked"), "on" == a && $("#fixednavbar").prop("checked", "checked"), "on" == o && $("#boxedlayout").prop("checked", "checked")
    }
    if (localStorageSupport) {
        var e = localStorage.getItem("collapse_menu"), a = localStorage.getItem("fixednavbar"),
            o = localStorage.getItem("boxedlayout"), l = $("body");
        "on" == e && (l.hasClass("body-small") || l.addClass("mini-navbar")), "on" == a && ($(".navbar-static-top").removeClass("navbar-static-top").addClass("navbar-fixed-top"), l.addClass("fixed-nav")), "on" == o && l.addClass("boxed-layout")
    }
});

/**
 * 移动端适配
 */
var isMobile = false;
$(window).bind("load resize",
    function () {
        isMobile = isMobileEnv() || $(window).width() < 769;
        if (isMobile) {
            $("body").addClass("mini-navbar");
            $('.navbar-static-side').fadeIn();
            $(".wayn-h3,#user-state,.navbar-top-links").fadeOut();
        } else {
        }
    }
);


