package com.marine.service;

import com.marine.entity.vo.FileUploadResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    /**
     * 上传3D模型文件
     *
     * @param file     上传的文件
     * @param deviceId 设备ID
     * @return 文件上传结果（包含文件ID、路径等信息）
     */
    FileUploadResultVO upload3DModel(MultipartFile file, Long deviceId);

}
