package com.example.demo.util;

import com.example.demo.config.FileUploadConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class FileUploadUtil {

    @Resource
    private FileUploadConfig fileUploadConfig;

    public String upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
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
        String fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileType;
        String uploadPath = fileUploadConfig.getPath() + datePath + "/";

        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path path = Paths.get(uploadPath + fileName);
        Files.write(path, file.getBytes());

        log.info("文件上传成功: {}", path);
        return path.toString();
    }

    public void deleteFile(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return;
        }
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            log.info("文件删除成功: {}", filePath);
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    private boolean isAllowedType(String fileType) {
        if (fileType == null || fileType.isEmpty()) {
            return false;
        }
        String[] allowedTypes = fileUploadConfig.getAllowedTypes().split(",");
        return Arrays.asList(allowedTypes).contains(fileType);
    }
}
