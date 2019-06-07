$(function() {
	$.fn.select2.defaults.set("language", "zh-CN");
	$.fn.select2.defaults.set("theme", "bootstrap");
})

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
function switchInit(module,state = true) {
	if ('boolean' != typeof state) {
		throw new Error('state must be boolean type');
	}
	$("[name='" + module + "StateSwicth']").bootstrapSwitch({
		state : state,
		onText : "启用",
		offText : "禁用",
		onColor : "success",
		onSwitchChange : function(event, state) {
			if (state) {
				$('#' + module + 'State').val('1');
			} else {
				$('#' + module + 'State').val('-1');
			}
		}
	});
}