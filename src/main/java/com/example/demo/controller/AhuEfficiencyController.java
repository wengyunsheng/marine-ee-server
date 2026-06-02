package com.example.demo.controller;

import com.example.demo.entity.AhuEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.AhuEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用组合式空调机组能效等级控制器
 * 提供空调机组能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/ahu-efficiency")
public class AhuEfficiencyController {

    @Autowired
    private AhuEfficiencyService ahuEfficiencyService;

    /**
     * 查询组合式空调机组能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<AhuEfficiency>> getList() {
        List<AhuEfficiency> list = ahuEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
