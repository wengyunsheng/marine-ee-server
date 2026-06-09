package com.marine.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileUploadResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    private Long fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

    /**
     * 文件访问URL
     */
    private String fileUrl;
}
