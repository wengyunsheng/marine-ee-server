package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.TestCycle;
import com.example.demo.entity.vo.TestCycleDetailVO;
import com.example.demo.mapper.TestCycleMapper;
import com.example.demo.service.TestCycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TestCycleServiceImpl extends ServiceImpl<TestCycleMapper, TestCycle> implements TestCycleService {

    @Override
    public List<TestCycleDetailVO> getAllCycleDetails() {
        LambdaQueryWrapper<TestCycle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(TestCycle::getIsDeleted, 0)
                .orderByAsc(TestCycle::getCycleCode, TestCycle::getConditionNo);

        List<TestCycle> allCycles = baseMapper.selectList(wrapper);

        Map<String, List<TestCycle>> groupedByCode = allCycles.stream()
                .collect(Collectors.groupingBy(TestCycle::getCycleCode,
                        LinkedHashMap::new,
                        Collectors.toList()));

        return groupedByCode.values().stream()
                .map(cycles -> {
                    TestCycleDetailVO detail = new TestCycleDetailVO();
                    TestCycle first = cycles.get(0);

                    detail.setCycleCode(first.getCycleCode());
                    detail.setCycleName(first.getCycleName());

                    List<TestCycleDetailVO.ConditionDTO> conditions = cycles.stream()
                            .map(cycle -> {
                                TestCycleDetailVO.ConditionDTO condition = new TestCycleDetailVO.ConditionDTO();
                                condition.setConditionNo(cycle.getConditionNo());
                                condition.setEngineSpeed(cycle.getEngineSpeed());
                                condition.setPowerMode(cycle.getPowerMode());
                                condition.setWeightCoefficient(cycle.getWeightCoefficient());
                                return condition;
                            })
                            .collect(Collectors.toList());

                    detail.setConditions(conditions);
                    return detail;
                })
                .collect(Collectors.toList());
    }

}
