package com.wayn.filemanager.util;

import org.apache.commons.codec.binary.Base64;

/**
 * 字符串hash操作帮助类
 * @author wayn
 *
 */
public class HashesUtil {
	private static final String[][] ESCAPES = {{"+", "_P"}, {"-", "_M"}, {"/", "_S"}, {".", "_D"}, {"=", "_E"}};

	public static String encode(String source) {
		String hash = new String(Base64.encodeBase64(source.getBytes()));
        for (String[] pair : ESCAPES) {
        	hash = hash.replace(pair[0], pair[1]);
        }
		return hash;
	}
	
	public static String decode(String hash) {
		for (String[] pair : ESCAPES) {
			hash = hash.replace(pair[1], pair[0]);
        }
		return new String(Base64.decodeBase64(hash));
	}
	
	public static void main(String[] args) {
		System.out.println(encode("opt"));
//		/测试
		System.out.println(encode("/测试"));
//		b3B0L7LiytQ_E
		System.out.println(decode("b3B0L_Pa1i_PivlQ_E_E"));
		System.out.println("opt\\constants\\新建文本文档 (2).txt".substring("opt\\constants".length() + 1));
	}
}
