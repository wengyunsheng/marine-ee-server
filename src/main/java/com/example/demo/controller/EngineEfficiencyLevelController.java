package com.example.demo.controller;

import com.example.demo.entity.EngineEfficiencyLevel;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.EngineEfficiencyLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用发动机能效等级控制器
 * 提供能效等级和能效基值查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/efficiency-levels")
public class EngineEfficiencyLevelController {

    @Autowired
    private EngineEfficiencyLevelService engineEfficiencyLevelService;

    /**
     * 查询能效等级列表
     * 支持按发动机类型筛选
     *
     * @param engineType 发动机类型（可选，如：低速机、中速机）
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<EngineEfficiencyLevel>> getList(@RequestParam(required = false) String engineType) {
        List<EngineEfficiencyLevel> list = engineEfficiencyLevelService.getList(engineType);
        return Result.success(list, list.size());
    }
}
