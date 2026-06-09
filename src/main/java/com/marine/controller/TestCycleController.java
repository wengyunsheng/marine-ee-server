package com.marine.controller;

import com.marine.entity.vo.ResultVO;
import com.marine.entity.vo.TestCycleDetailVO;
import com.marine.service.TestCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 试验循环配置控制器
 *
 * @author admin
 * @since 2026-06-02
 */
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
        try {
            List<TestCycleDetailVO> details = testCycleService.getAllCycleDetails();
            return ResultVO.success(details);
        } catch (Exception e) {
            return ResultVO.error("查询失败: " + e.getMessage());
        }
    }

}
