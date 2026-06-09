package com.example.demo.service.impl;

import com.example.demo.entity.Device;
import com.example.demo.entity.FileInfo;
import com.example.demo.entity.dto.FileUploadResult;
import com.example.demo.mapper.DeviceMapper;
import com.example.demo.service.FileInfoService;
import com.example.demo.service.FileUploadService;
import com.example.demo.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadUtil fileUploadUtil;

    private final DeviceMapper deviceMapper;

    private final FileInfoService fileInfoService;

    @Value("${file.upload.path}")
    private String basePath;

    @Override
    @Transactional
    public FileUploadResult upload3DModel(MultipartFile file, Long deviceId) {
        try {
            Device device = deviceMapper.selectById(deviceId);
            if (device == null) {
                throw new IllegalArgumentException("设备不存在");
            }

            if (device.getParentCode() != null) {
                throw new IllegalArgumentException("只有父设备才能上传3D模型");
            }

            FileInfo oldFile = fileInfoService.getByBusinessId("3d_model", deviceId);
            if (oldFile != null) {
                fileUploadUtil.deleteFile(oldFile.getFilePath());
                fileInfoService.logicDeleteById(oldFile.getId());
            }

            String filePath = fileUploadUtil.upload(file);
            String fileName = file.getOriginalFilename();
            String fileType = getFileExtension(fileName);
            String fileUrl = "/files/" + filePath.replace(basePath, "").replace("\\", "/");

            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setFilePath(filePath);
            fileInfo.setFileType(fileType);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setFileUrl(fileUrl);
            fileInfo.setBusinessType("3d_model");
            fileInfo.setBusinessId(deviceId);
            fileInfo.setIsDeleted(0);
            fileInfo.setCreateTime(LocalDateTime.now());
            fileInfo.setUpdateTime(LocalDateTime.now());
            fileInfoService.save(fileInfo);

            device.setModelFileId(fileInfo.getId());
            deviceMapper.updateById(device);

            FileUploadResult result = new FileUploadResult();
            result.setFileId(fileInfo.getId());
            result.setFileName(fileName);
            result.setFilePath(filePath);
            result.setFileType(fileType);
            result.setFileSize(file.getSize());
            result.setFileUrl(fileUrl);

            log.info("设备 {} 的3D模型上传成功，文件ID: {}", deviceId, fileInfo.getId());
            return result;
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        }
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
}
