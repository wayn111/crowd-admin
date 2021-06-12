package com.wayn.filemanager.util;

import java.util.UUID;

public class UUIDUtil {

	public static String genUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
