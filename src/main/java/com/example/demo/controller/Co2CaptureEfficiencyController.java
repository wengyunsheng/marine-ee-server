package com.example.demo.controller;

import com.example.demo.entity.Co2CaptureEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.Co2CaptureEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用二氧化碳捕集设备能效等级控制器
 * 提供二氧化碳捕集设备能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/co2-capture-efficiency")
public class Co2CaptureEfficiencyController {

    @Autowired
    private Co2CaptureEfficiencyService co2CaptureEfficiencyService;

    /**
     * 查询二氧化碳捕集设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<Co2CaptureEfficiency>> getList() {
        List<Co2CaptureEfficiency> list = co2CaptureEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
