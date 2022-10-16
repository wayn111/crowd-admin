<a id="scroll-up" href="#" class="btn btn-sm display"><i class="fa fa-angle-double-up"></i></a>
<script src="${_ctx }/static/plugin/jquery/jquery.min.js"></script>
<script src="${_ctx }/static/plugin/validate/jquery.validate.min.js?v=1.13"></script>
<script src="${_ctx }/static/plugin/validate/messages_zh.min.js"></script>
<script src="${_ctx }/static/plugin/jquery-layout/jquery.layout-latest.js?v=1.4"></script>
<script src="${_ctx }/static/plugin/bootstrap-v3.3.7/js/bootstrap.min.js?v=3.3.7"></script>
<script src="${_ctx }/static/plugin/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"></script>
<script src="${_ctx }/static/plugin/bootstrap-table-v1.18.0/bootstrap-table.min.js"></script>
<script src="${_ctx }/static/plugin/bootstrap-table-v1.18.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${_ctx }/static/plugin/bootstrap-table-v1.18.0/extensions/mobile/bootstrap-table-mobile.min.js?v=20210202"></script>
<script src="${_ctx }/static/plugin/jsTree/jstree.min.js"></script>
<script src="${_ctx }/static/plugin/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="${_ctx }/static/plugin/layer/layer.min.js"></script>
<script src="${_ctx }/static/plugin/layDate-v5.0.9/laydate/laydate.js"></script>
<script src="${_ctx }/static/plugin/select2-4.0.7/js/select2.min.js"></script>
<script src="${_ctx }/static/plugin/iCheck/icheck.min.js"></script>
<script src="${_ctx }/static/plugin/select2-4.0.7/js/i18n/zh-CN.js"></script>
<script src="${_ctx }/static/js/common/common-ui.js"></script>
<script>
    $(function () {
        // select2
        $.fn.select2.defaults.set("language", "zh-CN"); // 设置默认语言
        $.fn.select2.defaults.set("theme", "bootstrap"); // 设置默认主题

        // 回到顶部绑定
        if ($.fn.toTop !== undefined) {
            $('#scroll-up').toTop();
        }

        // layDate 时间控件绑定
        layDateQuery();

        // 初始化绑定树搜索控件
        treeSearchInit();

        // 初始化多选框/单选框控件
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",});
        $.validator.addMethod("checkEmail", function (value, element, params) {
            var checkEmail = /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,4}$/i;
            return this.optional(element) || (checkEmail.test(value));
        }, "*请输入正确的邮箱！");
    });
</script>
