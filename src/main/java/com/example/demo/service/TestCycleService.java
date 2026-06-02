package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.TestCycle;
import com.example.demo.entity.TestCycleBatchUpdateDTO;
import com.example.demo.entity.TestCycleDetailDTO;

import java.util.List;

public interface TestCycleService extends IService<TestCycle> {

    List<TestCycleDetailDTO> getAllCycleDetails();

    boolean batchUpdateWeightCoefficient(List<TestCycleBatchUpdateDTO> updateDTOList);
}
