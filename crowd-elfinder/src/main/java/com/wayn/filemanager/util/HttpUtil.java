package com.wayn.filemanager.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HttpUtil {

	public static String getAttachementFileName(String fileName, String userAgent) throws UnsupportedEncodingException {
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();

			if (userAgent.contains("msie")) {
				return "filename=\"" + URLEncoder.encode(fileName, "UTF8") + "\"";
			}

			if (userAgent.contains("opera")) {
				return "filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF8");
			}
			if (userAgent.contains("safari")) {
				return "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
			}
			if (userAgent.contains("mozilla")) {
				return "filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF8");
			}
		}

		return "filename=\"" + URLEncoder.encode(fileName, "UTF8") + "\"";
	}
}
