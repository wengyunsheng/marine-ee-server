package com.example.demo.controller;

import com.example.demo.entity.EngineInfo;
import com.example.demo.entity.dto.EngineQueryDTO;
import com.example.demo.entity.vo.EvaluationResultVO;
import com.example.demo.entity.vo.ResultVO;
import com.example.demo.service.EngineDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/engine")
public class EngineDataController {

    @Autowired
    private EngineDataService engineDataService;

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

            int successCount = engineDataService.importEngineFromExcel(file);
            return ResultVO.successMessage("导入成功，共导入 " + successCount + " 条数据");
        } catch (IllegalArgumentException e) {
            return ResultVO.error(e.getMessage());
        } catch (Exception e) {
            log.error("导入发动机数据失败", e);
            return ResultVO.error("导入失败: " + e.getMessage());
        }
    }

    @PostMapping("/list")
    public ResultVO<List<EngineInfo>> queryEngines(@RequestBody EngineQueryDTO queryDTO) {
        List<EngineInfo> list = engineDataService.queryEngines(queryDTO);
        return ResultVO.success(list);
    }

    @GetMapping("/evaluate/{engineId}")
    public ResultVO<EvaluationResultVO> getEvaluation(@PathVariable Long engineId) {
        try {
            EvaluationResultVO result = engineDataService.getEvaluation(engineId);
            return ResultVO.success(result);
        } catch (Exception e) {
            log.error("获取能效指标失败: engineId={}", engineId, e);
            return ResultVO.error("获取失败: " + e.getMessage());
        }
    }

    @PostMapping("/evaluate/{engineId}")
    public ResultVO<EvaluationResultVO> completeEvaluation(@PathVariable Long engineId) {
        try {
            EvaluationResultVO result = engineDataService.completeEvaluation(engineId);
            return ResultVO.success(result);
        } catch (Exception e) {
            log.error("计算能效指标失败: engineId={}", engineId, e);
            return ResultVO.error("计算失败: " + e.getMessage());
        }
    }

}
