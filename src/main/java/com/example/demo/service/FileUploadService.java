package com.example.demo.service;

import com.example.demo.entity.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    FileUploadResult upload3DModel(MultipartFile file, Long deviceId);

}
