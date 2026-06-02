package com.example.demo.controller;

import com.example.demo.entity.InertGasEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.InertGasEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用惰性气体系统能效等级控制器
 * 提供惰性气体系统能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/inert-gas-efficiency")
public class InertGasEfficiencyController {

    @Autowired
    private InertGasEfficiencyService inertGasEfficiencyService;

    /**
     * 查询惰性气体系统能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<InertGasEfficiency>> getList() {
        List<InertGasEfficiency> list = inertGasEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
