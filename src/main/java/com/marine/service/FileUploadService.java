package com.marine.service;

import com.marine.entity.vo.FileUploadResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    FileUploadResultVO upload3DModel(MultipartFile file, Long deviceId);

}
