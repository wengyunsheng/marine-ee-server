package com.marine.service.impl;

import com.marine.entity.Device;
import com.marine.entity.FileInfo;
import com.marine.entity.vo.FileUploadResultVO;
import com.marine.mapper.DeviceMapper;
import com.marine.service.FileInfoService;
import com.marine.service.FileUploadService;
import com.marine.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    @Transactional
    public FileUploadResultVO upload3DModel(MultipartFile file, Long deviceId) {
        try {
            Device device = deviceMapper.selectById(deviceId);
            if (device == null) {
                throw new IllegalArgumentException("设备不存在");
            }

            if (device.getParentCode() != null) {
                throw new IllegalArgumentException("只有父设备才能上传3D模型");
            }

            // 删除旧文件
            fileInfoService.removeById(device.getModelFileId());

            String filePath = fileUploadUtil.upload(file);
            String fileName = file.getOriginalFilename();
            String fileType = getFileExtension(fileName);

            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setFilePath(filePath);
            fileInfo.setFileType(fileType);
            fileInfo.setFileSize(file.getSize());
            fileInfo.setCreateTime(LocalDateTime.now());
            fileInfoService.save(fileInfo);

            device.setModelFileId(fileInfo.getId());
            deviceMapper.updateById(device);

            FileUploadResultVO result = new FileUploadResultVO();
            result.setFileId(fileInfo.getId());
            result.setFileName(fileName);
            result.setFilePath(filePath);
            result.setFileType(fileType);
            result.setFileSize(file.getSize());

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
