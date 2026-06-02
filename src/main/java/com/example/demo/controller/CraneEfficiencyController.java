package com.example.demo.controller;

import com.example.demo.entity.CraneEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.CraneEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用吊机能效等级控制器
 * 提供吊机能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/crane-efficiency")
public class CraneEfficiencyController {

    @Autowired
    private CraneEfficiencyService craneEfficiencyService;

    /**
     * 查询吊机能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<CraneEfficiency>> getList() {
        List<CraneEfficiency> list = craneEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
