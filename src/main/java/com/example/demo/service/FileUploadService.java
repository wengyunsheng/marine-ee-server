package com.example.demo.service;

import com.example.demo.entity.vo.FileUploadResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    FileUploadResultVO upload3DModel(MultipartFile file, Long deviceId);

}
