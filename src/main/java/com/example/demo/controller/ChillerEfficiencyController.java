package com.example.demo.controller;

import com.example.demo.entity.ChillerEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.ChillerEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用冷水机组能效等级控制器
 * 提供冷水机组能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/chiller-efficiency")
public class ChillerEfficiencyController {

    @Autowired
    private ChillerEfficiencyService chillerEfficiencyService;

    /**
     * 查询冷水机组能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<ChillerEfficiency>> getList() {
        List<ChillerEfficiency> list = chillerEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
