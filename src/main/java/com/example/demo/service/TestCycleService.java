package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.TestCycle;
import com.example.demo.entity.dto.TestCycleDetailDTO;

import java.util.List;

public interface TestCycleService extends IService<TestCycle> {

    List<TestCycleDetailDTO> getAllCycleDetails();

}
