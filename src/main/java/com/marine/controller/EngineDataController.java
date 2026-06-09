package com.marine.controller;

import com.marine.entity.EngineInfo;
import com.marine.entity.dto.EngineQueryDTO;
import com.marine.entity.vo.EvaluationResultVO;
import com.marine.entity.vo.ResultVO;
import com.marine.service.EngineDataService;
import lombok.RequiredArgsConstructor;
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
    public ResultVO<Void> importEngine(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResultVO.error("文件不能为空");
            }

            String fileName = file.getOriginalFilename();
            if (fileName == null || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
                return ResultVO.error("仅支持 Excel 文件（.xlsx 或 .xls）");
            }

            engineDataService.importEngineFromExcel(file);
            return ResultVO.success();
        } catch (Exception e) {
            return ResultVO.error("导入失败: " + e.getMessage());
        }
    }

    /**
     * 查询发动机列表
     *
     * @param queryDTO 查询条件
     *                 - brand: 品牌（可选，模糊查询）
     *                 - model: 型号（可选，模糊查询）
     *                 - emissionStandard: 排放标准（可选，精确匹配）
     * @return 发动机信息列表
     */
    @PostMapping("/list")
    public ResultVO<List<EngineInfo>> queryEngines(@RequestBody EngineQueryDTO queryDTO) {
        try {
            List<EngineInfo> list = engineDataService.queryEngines(queryDTO);
            return ResultVO.success(list);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 获取发动机能效评估结果
     *
     * @param engineId 发动机ID
     * @return 能效评估结果
     */
    @GetMapping("/evaluate/{engineId}")
    public ResultVO<EvaluationResultVO> getEvaluation(@PathVariable Long engineId) {
        try {
            EvaluationResultVO result = engineDataService.getEvaluation(engineId);
            return ResultVO.success(result);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

    /**
     * 完成发动机能效评估计算
     *
     * @param engineId 发动机ID
     * @return 能效评估结果，包含能效指数、能效等级、基准值等信息
     */
    @PostMapping("/evaluate/{engineId}")
    public ResultVO<EvaluationResultVO> completeEvaluation(@PathVariable Long engineId) {
        try {
            EvaluationResultVO result = engineDataService.completeEvaluation(engineId);
            return ResultVO.success(result);
        } catch (Exception e) {
            return ResultVO.error("计算失败: " + e.getMessage());
        }
    }

}
