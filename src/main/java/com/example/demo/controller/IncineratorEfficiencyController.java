package com.example.demo.controller;

import com.example.demo.entity.IncineratorEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.IncineratorEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用焚烧炉能效等级控制器
 * 提供焚烧炉能效等级和能效基值查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/incinerator-efficiency")
public class IncineratorEfficiencyController {

    @Autowired
    private IncineratorEfficiencyService incineratorEfficiencyService;

    /**
     * 查询焚烧炉能效等级列表
     *
     * @param incineratorType 焚烧炉类型编码（可选，如：incinerator-01）
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<IncineratorEfficiency>> getList(@RequestParam(required = false) String incineratorType) {
        List<IncineratorEfficiency> list = incineratorEfficiencyService.getList(incineratorType);
        return Result.success(list, list.size());
    }
}
