package com.wayn.web.controller.common;

import com.wayn.common.exception.BusinessException;
import com.wayn.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Resource
    private ProjectConfig projectConfig;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response) {
        try {
            String uploadDir = projectConfig.getUploadDir();
            if (FileUtils.checkAllowDownload(fileName)) {
                throw new BusinessException("文件名称(" + fileName + ")非法，不允许下载。 ");
            }

            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = uploadDir + File.separatorChar + fileName;
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 下载模板文件
     *
     * @param fileName 文件名称
     */
    @GetMapping("/downTemplate")
    public void downTemplate(String fileName, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.checkAllowDownload(fileName)) {
                throw new BusinessException("文件名称(" + fileName + ")非法，不允许下载。 ");
            }

            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("tmp/" + fileName)) {
                FileUtils.writeBytes(is, response.getOutputStream());
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/upload")
    @ResponseBody
    public Response uploadFile(MultipartFile file, HttpServletRequest request) {
        try {
            // 上传文件路径
            String filePath = projectConfig.getUploadDir();
            String fileName = FileUploadUtil.uploadFile(file, filePath);
            String requestUrl = HttpUtil.getRequestContext(request);
            String url = requestUrl + "/upload/" + fileName;
            return Response.success().add("url", url).add("fileName", fileName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.error(e.getMessage());
        }
    }

}
