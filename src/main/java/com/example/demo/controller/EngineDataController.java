package com.example.demo.controller;

import com.example.demo.entity.EngineInfo;
import com.example.demo.entity.dto.EngineQueryDTO;
import com.example.demo.entity.dto.EvaluationResultDTO;
import com.example.demo.entity.dto.Result;
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
    public Result<Void> importEngine(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return Result.error("文件不能为空");
            }

            String fileName = file.getOriginalFilename();
            if (fileName == null || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
                return Result.error("仅支持 Excel 文件（.xlsx 或 .xls）");
            }

            int successCount = engineDataService.importEngineFromExcel(file);
            return Result.successMessage("导入成功，共导入 " + successCount + " 条数据");
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            log.error("导入发动机数据失败", e);
            return Result.error("导入失败: " + e.getMessage());
        }
    }

    @PostMapping("/list")
    public Result<List<EngineInfo>> queryEngines(@RequestBody EngineQueryDTO queryDTO) {
        List<EngineInfo> list = engineDataService.queryEngines(queryDTO);
        return Result.success(list);
    }

    @GetMapping("/evaluate/{engineId}")
    public Result<EvaluationResultDTO> getEvaluation(@PathVariable Long engineId) {
        try {
            EvaluationResultDTO result = engineDataService.getEvaluation(engineId);
            return Result.success(result);
        } catch (Exception e) {
            log.error("获取能效指标失败: engineId={}", engineId, e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    @PostMapping("/evaluate/{engineId}")
    public Result<EvaluationResultDTO> completeEvaluation(@PathVariable Long engineId) {
        try {
            EvaluationResultDTO result = engineDataService.completeEvaluation(engineId);
            return Result.success(result);
        } catch (Exception e) {
            log.error("计算能效指标失败: engineId={}", engineId, e);
            return Result.error("计算失败: " + e.getMessage());
        }
    }

}
