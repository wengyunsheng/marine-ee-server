package com.marine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传配置类
 *
 * @author admin
 * @since 2026-06-02
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {

    /**
     * 文件上传路径
     */
    private String path;

    /**
     * 允许上传的文件类型，多个类型用逗号分隔
     */
    private String allowedTypes;

    /**
     * 文件最大大小（字节）
     */
    private long maxSize;
}
