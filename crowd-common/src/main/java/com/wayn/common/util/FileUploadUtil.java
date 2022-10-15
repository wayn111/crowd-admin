package com.wayn.common.util;

import com.wayn.common.exception.BusinessException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传帮助类
 */
public class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    public static String uploadFile(MultipartFile file, String filePath) {
        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > 100) {
            throw new BusinessException("文件名称过长");
        }
        String fileName = file.getOriginalFilename();
        if (FileUtils.checkAllowDownload(fileName)) {
            throw new BusinessException("文件名称(" + fileName + ")非法，不允许下载。 ");
        }
        String extension = FilenameUtils.getExtension(fileName);
        if (StringUtils.isBlank(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        String encodingFilename = FileUtils.encodingFilename(fileName);
        fileName = DateUtils.datePath() + "/" + encodingFilename + "." + extension;
        File desc = new File(filePath, fileName);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        try {
            if (!desc.exists()) {
                desc.createNewFile();
            }
            file.transferTo(desc);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return fileName;
    }
}
