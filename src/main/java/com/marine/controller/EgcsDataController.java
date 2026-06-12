package com.marine.controller;

import com.marine.entity.vo.ResultVO;
import com.marine.service.EgcsDataService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 尾气处理装置数据管理控制器
 *
 * @author admin
 * @since 2026-06-09
 */
@RestController
@RequestMapping("/api/egcs")
@RequiredArgsConstructor
public class EgcsDataController {

    private final EgcsDataService egcsDataService;

    /**
     * 从Excel文件导入尾气处理装置数据（脱硫和脱硝）
     *
     * @param deviceId 设备ID
     * @param file     Excel文件（.xlsx 或 .xls格式）
     * @return 导入结果
     */
    @PostMapping("/import")
    public ResultVO<Void> importEgcs(@RequestParam("deviceId") Long deviceId,
                                     @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultVO.error("文件不能为空");
            }

            String fileName = file.getOriginalFilename();
            if (StringUtils.isBlank(fileName) || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
                return ResultVO.error("仅支持 Excel 文件（.xlsx 或 .xls）");
            }

            egcsDataService.importEgcsFromExcel(deviceId, file);
            return ResultVO.success();
        } catch (Exception e) {
            return ResultVO.error("导入失败: " + e.getMessage());
        }
    }
}
