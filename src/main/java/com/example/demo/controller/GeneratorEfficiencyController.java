package com.example.demo.controller;

import com.example.demo.entity.GeneratorEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.GeneratorEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用发电机能效等级控制器
 * 提供发电机能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/generator-efficiency")
public class GeneratorEfficiencyController {

    @Autowired
    private GeneratorEfficiencyService generatorEfficiencyService;

    /**
     * 查询发电机能效等级列表
     *
     * @param generatorType 发电机类型（可选，如：generator-01、generator-02）
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<GeneratorEfficiency>> getList(@RequestParam(required = false) String generatorType) {
        List<GeneratorEfficiency> list = generatorEfficiencyService.getList(generatorType);
        return Result.success(list, list.size());
    }
}
