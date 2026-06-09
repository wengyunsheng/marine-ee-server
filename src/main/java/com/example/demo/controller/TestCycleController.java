package com.example.demo.controller;

import com.example.demo.entity.vo.ResultVO;
import com.example.demo.entity.vo.TestCycleDetailVO;
import com.example.demo.service.TestCycleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试验循环配置控制器
 *
 * @author admin
 * @since 2026-06-02
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/test-cycles")
public class TestCycleController {

    private final TestCycleService testCycleService;

    /**
     * 获取所有试验循环详情列表（包含工况数据）
     *
     * @return 试验循环详情列表
     */
    @GetMapping("/cycle-details")
    public ResultVO<List<TestCycleDetailVO>> getAllCycleDetails() {
        List<TestCycleDetailVO> details = testCycleService.getAllCycleDetails();
        return ResultVO.success(details, details.size());
    }

}
