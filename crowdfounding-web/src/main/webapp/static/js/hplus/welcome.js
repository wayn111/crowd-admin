layer.config({
	extend : [ "extend/layer.ext.js", "skin/moon/style.css" ],
	skin : "layer-ext-moon"
}), layer.ready(function() {
	function e() {
		parent.layer.open({
			title : "初见倾心，再见动情",
			type : 1,
			area : [ "700px", "auto" ],
			content : t,
			btn : [ "确定", "取消" ]
		})
	}

	var t = $("#welcome-template").html();
	$("a.viewlog").click(function() {
		return e(), !1
	});
	$("#pay-qrcode").click(function() {
		var e = $(this).html();
		parent.layer.open({
			title : !1,
			type : 1,
			closeBtn : !1,
			shadeClose : !0,
			area : [ "600px", "auto" ],
			content : e
		})
	});
});
