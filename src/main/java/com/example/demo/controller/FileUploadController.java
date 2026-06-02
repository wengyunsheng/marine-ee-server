package com.example.demo.controller;

import com.example.demo.entity.FileUploadResult;
import com.example.demo.entity.Result;
import com.example.demo.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Resource
    private FileUploadService fileUploadService;

    /**
     * 上传3D模型文件
     * 支持格式: OBJ, FBX, GLTF, GLB, STL
     * 文件大小限制: 100MB
     *
     * @param file     上传的文件
     * @param deviceId 设备ID（父设备）
     * @return 上传结果
     */
    @PostMapping("/upload/3d-model")
    public Result<FileUploadResult> upload3DModel(
            @RequestParam("file") MultipartFile file,
            @RequestParam("deviceId") Long deviceId) {
        try {
            if (file.isEmpty()) {
                return Result.error("文件不能为空");
            }
            FileUploadResult result = fileUploadService.upload3DModel(file, deviceId);
            return Result.success("上传成功", result);
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            log.error("上传3D模型失败", e);
            return Result.error("上传失败: " + e.getMessage());
        }
    }

}
