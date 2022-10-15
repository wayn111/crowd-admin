package com.wayn.common.util;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

/**
 * 图片帮助类
 */
public class ImageUtil {

    /**
     * 图片转换base64编码
     *
     * @param in   输入流
     * @param type 文件类型
     * @return base64编码字符串
     */
    public static String imgToBase64(InputStream in, String type) {
        String prefix = "data:" + type + ";base64,";
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            int ch;
            while ((ch = in.read()) != -1) {
                outputStream.write(ch);
            }
            return prefix + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("图片转base64编码失败");
    }

    /**
     * 图片转换base64编码
     *
     * @param file 文件对象
     * @param type 文件类型
     * @return base64编码字符串
     */
    public static String imgToBase64(File file, String type) {
        String prefix = "data:" + type + ";base64,";
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                outputStream.write(ch);
            }
            return prefix + Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("图片转base64编码失败");
    }

    /**
     * base64编码字符串转图片
     *
     * @param base64 base64编码字符串
     * @return 字节数组
     */
    public static byte[] base64ToImg(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("E:/123.jpg");
        System.out.println(imgToBase64(Files.newInputStream(file.toPath()), "data:image/jpg;base64,"));
    }

}
