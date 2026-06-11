package com.marine.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传结果视图对象
 *
 * @author admin
 * @since 2026-06-02
 */
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
     * 文件绝对路径
     */
    private String filePath;

    /**
     * 文件静态路径
     */
    private String fileUrl;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小（字节）
     */
    private Long fileSize;

}
