package com.wayn.commom.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

    /**
     * 图片转化成base64字符串,返回的string可以直接在src上显示
     *
     * @param file     图片文件
     * @param fileType 图片格式
     * @return
     * @throws IOException
     */
    public static String getImageStr(File file, String fileType) throws IOException {
        String fileContentBase64 = null;
        String base64Str = "data:" + fileType + ";base64,";
        String content = null;
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data;
        //读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            //对字节数组Base64编码
            if (data == null || data.length == 0) {
                return null;
            }
            BASE64Encoder encoder = new BASE64Encoder();
            content = encoder.encode(data);
            content = content.replaceAll("\n", "").replaceAll("\r", "");
            if (content == null || "".equals(content)) {
                return null;
            }
            fileContentBase64 = base64Str + content;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return fileContentBase64;
    }

    public static String getImageStr(InputStream in, String fileType) throws IOException {
        String fileContentBase64 = null;
        String base64Str = "data:" + fileType + ";base64,";
        String content = null;
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data;
        //读取图片字节数组
        try {
            data = new byte[in.available()];
            in.read(data);
            in.close();
            //对字节数组Base64编码
            if (data == null || data.length == 0) {
                return null;
            }
            BASE64Encoder encoder = new BASE64Encoder();
            content = encoder.encode(data);
            content = content.replaceAll("\n", "").replaceAll("\r", "");
            if (content == null || "".equals(content)) {
                return null;
            }
            fileContentBase64 = base64Str + content;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return fileContentBase64;
    }

    public static byte[] decodeImageStr(String content) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(content);
        return bytes;
    }
}
