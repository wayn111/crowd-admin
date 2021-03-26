package com.wayn.commom.util;

import java.io.*;
import java.util.Base64;

public class ImageUtil {

    /**
     * 图片转换base64编码
     *
     * @param in   输入流
     * @param type 编码头
     * @return base64编码字符串
     */
    public static String imgToBase64(InputStream in, String type) {
        int ch;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            while ((ch = in.read()) != -1) {
                outputStream.write(ch);
            }
            return type + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("图片转base64编码失败");
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("E:/123.jpg");
        System.out.println(imgToBase64(new FileInputStream(file), "data:image/png;base64,"));
    }

}
