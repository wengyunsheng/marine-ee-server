package com.example.demo.controller;

import com.example.demo.entity.WasteHeatOrganicRankineEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.WasteHeatOrganicRankineEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用有机朗肯循环发电装置能效等级控制器
 * 提供能效等级和能效基值查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/organic-rankine-efficiency")
public class WasteHeatOrganicRankineEfficiencyController {

    @Autowired
    private WasteHeatOrganicRankineEfficiencyService organicRankineEfficiencyService;

    /**
     * 查询有机朗肯循环发电装置能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<WasteHeatOrganicRankineEfficiency>> getList() {
        List<WasteHeatOrganicRankineEfficiency> list = organicRankineEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
