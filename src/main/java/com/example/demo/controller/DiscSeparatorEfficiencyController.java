package com.example.demo.controller;

import com.example.demo.entity.DiscSeparatorEfficiency;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.DiscSeparatorEfficiencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 船用碟式分离机能效等级控制器
 * 提供碟式分离机能效等级查询功能
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RestController
@RequestMapping("/api/disc-separator-efficiency")
public class DiscSeparatorEfficiencyController {

    @Autowired
    private DiscSeparatorEfficiencyService discSeparatorEfficiencyService;

    /**
     * 查询碟式分离机能效等级列表
     *
     * @return 能效等级列表
     */
    @GetMapping("/list")
    public Result<List<DiscSeparatorEfficiency>> getList() {
        List<DiscSeparatorEfficiency> list = discSeparatorEfficiencyService.getList();
        return Result.success(list, list.size());
    }
}
