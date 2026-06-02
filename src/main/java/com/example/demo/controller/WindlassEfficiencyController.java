package com.example.demo.controller;

import com.example.demo.entity.WindlassEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.WindlassEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用锚绞机能效等级控制器
 * 提供锚绞机能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/windlass-efficiency")
public class WindlassEfficiencyController {

    @Autowired
    private WindlassEfficiencyService windlassEfficiencyService;

    /**
     * 查询锚绞机能效等级列表
     *
     * @param windlassType 锚绞机类型（可选，如：windlass-01、windlass-02）
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<WindlassEfficiency>> getList(@RequestParam(required = false) String windlassType) {
        List<WindlassEfficiency> list = windlassEfficiencyService.getList(windlassType);
        return Result.success(list, list.size());
    }
}
