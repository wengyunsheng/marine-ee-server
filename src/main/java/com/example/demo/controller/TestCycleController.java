package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.TestCycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试验循环配置控制器
 * 提供试验循环配置的增删改查功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/test-cycles")
public class TestCycleController {

    @Autowired
    private TestCycleService testCycleService;

    /**
     * 获取所有试验循环详情列表（包含工况数据）
     * 用于表格展示，返回每个试验循环及其所有工况配置
     *
     * @return 试验循环详情列表
     */
    @GetMapping("/cycle-details")
    public Result<List<TestCycleDetailDTO>> getAllCycleDetails() {
        List<TestCycleDetailDTO> details = testCycleService.getAllCycleDetails();
        return Result.success(details, details.size());
    }

    /**
     * 批量编辑试验循环配置的加权系数
     * 只能编辑加权系数，所有工况的加权系数总和必须等于 1
     *
     * @param updateDTOList 批量更新数据列表
     * @return 操作结果
     */
    @PostMapping("/batch-update-weight")
    public Result<Void> batchUpdateWeightCoefficient(@RequestBody List<TestCycleBatchUpdateDTO> updateDTOList) {
        try {
            boolean success = testCycleService.batchUpdateWeightCoefficient(updateDTOList);
            if (success) {
                return Result.successMessage("加权系数更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            log.error("批量更新加权系数失败", e);
            return Result.error("更新失败: " + e.getMessage());
        }
    }

}
