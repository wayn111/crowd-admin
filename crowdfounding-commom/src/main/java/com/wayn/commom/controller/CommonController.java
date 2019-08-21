package com.wayn.commom.controller;

import com.wayn.commom.exception.BusinessException;
import com.wayn.commom.util.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/commom")
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("/download/")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            String uploadDir = ProperUtil.get("wayn.uploadDir");
            if (!FileUtils.isValidFilename(fileName)) {
                throw new BusinessException("文件名称(" + fileName + ")非法，不允许下载。 ");
            }

            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = uploadDir + File.separatorChar + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
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
    public Response uploadFile(MultipartFile file, HttpServletRequest request) throws Exception {
        try {
            // 上传文件路径
            String filePath = ProperUtil.get("wayn.uploadDir");
            int fileNameLength = file.getOriginalFilename().length();
            if (fileNameLength > 100) {
                throw new BusinessException("文件名称过长");
            }
            String fileName = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(fileName);
            String encodingFilename = FileUtils.encodingFilename(fileName);
            fileName = DateUtils.datePath() + "/" + encodingFilename + "." + extension;
            File desc = new File(filePath, fileName);
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
            if (!desc.exists()) {
                desc.createNewFile();
            }
            file.transferTo(desc);
            String requestUrl = HttpUtil.getRequestContext(request);
            String url = requestUrl + "/upload/" + fileName;
            return Response.success().add("url", url).add("fileName", fileName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.error(e.getMessage());
        }
    }
}
