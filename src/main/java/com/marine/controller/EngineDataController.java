package com.marine.controller;

import com.marine.entity.EngineInfo;
import com.marine.entity.vo.EngineInfoVO;
import com.marine.entity.vo.ResultVO;
import com.marine.service.EngineDataService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 发动机数据管理控制器
 *
 * @author admin
 * @since 2026-06-09
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/engine")
public class EngineDataController {

    private final EngineDataService engineDataService;

    /**
     * 从Excel文件导入发动机数据
     *
     * @param file Excel文件（.xlsx 或 .xls格式）
     * @return 导入结果
     */
    @PostMapping("/import")
    public ResultVO<Void> importEngine(@RequestParam("deviceId") Long deviceId, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultVO.error("文件不能为空");
            }

            String fileName = file.getOriginalFilename();
            if (StringUtils.isBlank(fileName) || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
                return ResultVO.error("仅支持 Excel 文件（.xlsx 或 .xls）");
            }

            engineDataService.importEngineFromExcel(deviceId, file);
            return ResultVO.success();
        } catch (Exception e) {
            return ResultVO.error("导入失败: " + e.getMessage());
        }
    }

    /**
     * 查询所有发动机信息
     *
     * @return 所有发动机信息
     */
    @GetMapping("/all")
    public ResultVO<List<EngineInfoVO>> getAllEngines() {
        try {
            List<EngineInfoVO> list = engineDataService.getAllEngines();
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 查询发动机信息
     *
     * @param engineId 发动机ID
     * @return 发动机信息
     */
    @GetMapping("/detail")
    public ResultVO<EngineInfo> queryEngine(@RequestParam("engineId") Long engineId) {
        try {
            EngineInfo engineInfo = engineDataService.queryEngine(engineId);
            return ResultVO.success(engineInfo);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

}
