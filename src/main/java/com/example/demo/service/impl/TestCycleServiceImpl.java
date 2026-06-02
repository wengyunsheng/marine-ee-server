package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.TestCycle;
import com.example.demo.entity.dto.TestCycleBatchUpdateDTO;
import com.example.demo.entity.dto.TestCycleDetailDTO;
import com.example.demo.mapper.TestCycleMapper;
import com.example.demo.service.TestCycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TestCycleServiceImpl extends ServiceImpl<TestCycleMapper, TestCycle> implements TestCycleService {

    @Override
    public List<TestCycleDetailDTO> getAllCycleDetails() {
        QueryWrapper<TestCycle> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0)
                .orderByAsc("cycle_code", "condition_no", "sort");

        List<TestCycle> allCycles = baseMapper.selectList(wrapper);

        Map<String, List<TestCycle>> groupedByCode = allCycles.stream()
                .collect(Collectors.groupingBy(TestCycle::getCycleCode,
                        LinkedHashMap::new,
                        Collectors.toList()));

        return groupedByCode.values().stream()
                .map(cycles -> {
                    TestCycleDetailDTO detail = new TestCycleDetailDTO();
                    TestCycle first = cycles.get(0);

                    detail.setCycleCode(first.getCycleCode());
                    detail.setCycleName(first.getCycleName());
                    detail.setDeviceType(first.getDeviceType());

                    List<TestCycleDetailDTO.ConditionDTO> conditions = cycles.stream()
                            .map(cycle -> {
                                TestCycleDetailDTO.ConditionDTO condition = new TestCycleDetailDTO.ConditionDTO();
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

    @Override
    @Transactional
    public boolean batchUpdateWeightCoefficient(List<TestCycleBatchUpdateDTO> updateDTOList) {
        if (updateDTOList == null || updateDTOList.isEmpty()) {
            throw new IllegalArgumentException("更新数据不能为空");
        }

        LocalDateTime updateTime = LocalDateTime.now();

        for (TestCycleBatchUpdateDTO updateDTO : updateDTOList) {
            String cycleCode = updateDTO.getCycleCode();

            if (updateDTO.getItems() == null || updateDTO.getItems().isEmpty()) {
                throw new IllegalArgumentException("试验循环 " + cycleCode + " 的更新数据不能为空");
            }

            List<TestCycle> existingCycles = baseMapper.selectByCycleCode(cycleCode);

            if (existingCycles.isEmpty()) {
                throw new IllegalArgumentException("试验循环配置不存在: " + cycleCode);
            }

            Map<Integer, TestCycle> cycleMap = existingCycles.stream()
                    .collect(Collectors.toMap(TestCycle::getConditionNo, c -> c));

            BigDecimal totalWeight = BigDecimal.ZERO;

            for (TestCycleBatchUpdateDTO.WeightCoefficientItem item : updateDTO.getItems()) {
                TestCycle cycle = cycleMap.get(item.getConditionNo());
                if (cycle == null) {
                    throw new IllegalArgumentException("工况 " + item.getConditionNo() + " 不存在");
                }

                if (item.getWeightCoefficient() == null) {
                    throw new IllegalArgumentException("加权系数不能为空");
                }

                if (item.getWeightCoefficient().compareTo(BigDecimal.ZERO) < 0 ||
                        item.getWeightCoefficient().compareTo(new BigDecimal("1")) > 0) {
                    throw new IllegalArgumentException("加权系数必须在 0-1 之间");
                }

                cycle.setWeightCoefficient(item.getWeightCoefficient());
                cycle.setUpdateTime(updateTime);

                totalWeight = totalWeight.add(item.getWeightCoefficient());
            }

            if (totalWeight.compareTo(new BigDecimal("1")) != 0) {
                throw new IllegalArgumentException("试验循环 " + cycleCode + " 的加权系数总和必须等于 1，当前总和: " + totalWeight);
            }

            boolean success = this.updateBatchById(existingCycles);
            if (!success) {
                log.error("试验循环 {} 的加权系数更新失败", cycleCode);
                return false;
            }

            log.info("试验循环 {} 的加权系数更新成功，总和: {}", cycleCode, totalWeight);
        }

        return true;
    }
}
