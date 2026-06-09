package com.marine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marine.entity.TestCycle;
import com.marine.entity.vo.TestCycleDetailVO;

import java.util.List;

public interface TestCycleService extends IService<TestCycle> {

    List<TestCycleDetailVO> getAllCycleDetails();

}
