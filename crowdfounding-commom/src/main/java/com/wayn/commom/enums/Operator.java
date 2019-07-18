package com.wayn.commom.enums;

/**
 * 日志记录操作枚举类
 * @author wayn
 *
 */
public enum Operator {
	ADD("新建"), UPDATE("更新"), DELETE("删除"), BATCH_DELETE("删除"), SELECT("查询"), UPLOAD("上传"), DOWNLOAD("下载"), OTHER("其他"),
	LOGIN("登陆"), LOGOUT("登出"),GENCODE("代码生成");

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Operator(String name) {
		this.name = name;
	}

}
