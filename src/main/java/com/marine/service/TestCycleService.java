package com.marine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marine.entity.TestCycle;
import com.marine.entity.vo.TestCycleDetailVO;

import java.util.List;

public interface TestCycleService extends IService<TestCycle> {

    /**
     * 获取所有测试循环的详细信息
     *
     * @return 测试循环详情列表
     */
    List<TestCycleDetailVO> getAllCycleDetails();

    /**
     * 根据试验循环编号查询
     *
     * @param cycleCode 试验循环编号
     * @return 试验循环列表
     */
    List<TestCycle> getByCycleCode(String cycleCode);

}
