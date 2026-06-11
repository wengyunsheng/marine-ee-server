package com.marine.util;

import com.marine.config.FileUploadConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 文件上传工具类
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class FileUploadUtil {

    private final FileUploadConfig fileUploadConfig;

    /**
     * 上传文件到服务器
     *
     * @param file 要上传的文件
     * @return 文件保存的完整路径
     * @throws IOException 文件上传失败时抛出异常
     */
    public String upload(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }

        String originalFileName = file.getOriginalFilename();
        String fileType = getFileExtension(originalFileName);

        if (!isAllowedType(fileType)) {
            throw new IllegalArgumentException("不支持的文件类型，支持的类型: " + fileUploadConfig.getAllowedTypes());
        }

        if (file.getSize() > fileUploadConfig.getMaxSize()) {
            throw new IllegalArgumentException("文件大小超过限制，最大: " + (fileUploadConfig.getMaxSize() / 1024 / 1024) + "MB");
        }

        String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String uploadPath = fileUploadConfig.getPath() + datePath + "/";

        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path path = Paths.get(uploadPath + originalFileName);
        Files.write(path, file.getBytes());

        log.info("文件上传成功: {}", path);
        return path.toString();
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return 文件扩展名
     */
    public String getFileExtension(String fileName) {
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            return StringUtils.EMPTY;
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     * 检查文件类型是否在允许的列表中
     *
     * @param fileType 文件扩展名
     * @return true表示允许，false表示不允许
     */
    private boolean isAllowedType(String fileType) {
        if (StringUtils.isBlank(fileType)) {
            return false;
        }
        String[] allowedTypes = fileUploadConfig.getAllowedTypes().split(",");
        return Arrays.asList(allowedTypes).contains(fileType);
    }
}
