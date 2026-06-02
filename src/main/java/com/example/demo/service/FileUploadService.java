package com.example.demo.service;

import com.example.demo.entity.dto.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    FileUploadResult upload3DModel(MultipartFile file, Long deviceId);

}
