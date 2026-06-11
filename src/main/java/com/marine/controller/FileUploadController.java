package com.marine.controller;

import com.marine.entity.vo.FileUploadResultVO;
import com.marine.entity.vo.ResultVO;
import com.marine.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 *
 * @author admin
 * @since 2026-06-09
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    /**
     * 上传3D模型文件
     *
     * @param file     上传的文件
     * @param deviceId 设备ID（父设备）
     * @return 上传结果
     */
    @PostMapping("/upload/3d-model")
    public ResultVO<FileUploadResultVO> upload3DModel(@RequestParam("file") MultipartFile file, @RequestParam("deviceId") Long deviceId) {
        try {
            if (file.isEmpty()) {
                return ResultVO.error("文件不能为空");
            }
            FileUploadResultVO result = fileUploadService.upload3DModel(file, deviceId);
            return ResultVO.success(result);
        } catch (Exception e) {
            return ResultVO.error("上传失败: " + e.getMessage());
        }
    }

}
