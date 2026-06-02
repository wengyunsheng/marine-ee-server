package com.example.demo.controller;

import com.example.demo.entity.BallastWaterTreatmentEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.BallastWaterTreatmentEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用压载水处理设备能效等级控制器
 * 提供压载水处理设备能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/ballast-water-treatment-efficiency")
public class BallastWaterTreatmentEfficiencyController {

    @Autowired
    private BallastWaterTreatmentEfficiencyService ballastWaterTreatmentEfficiencyService;

    /**
     * 查询压载水处理设备能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<BallastWaterTreatmentEfficiency>> getList() {
        List<BallastWaterTreatmentEfficiency> list = ballastWaterTreatmentEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
