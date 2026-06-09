package com.example.demo.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileUploadResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long fileId;

    private String fileName;

    private String filePath;

    private String fileType;

    private Long fileSize;

    private String fileUrl;
}
