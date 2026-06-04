package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.GeneratorEfficiency;
import com.example.demo.mapper.GeneratorEfficiencyMapper;
import com.example.demo.service.GeneratorEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratorEfficiencyServiceImpl extends ServiceImpl<GeneratorEfficiencyMapper, GeneratorEfficiency> implements GeneratorEfficiencyService {

    @Override
    public List<GeneratorEfficiency> getList(String generatorType) {
        LambdaQueryWrapper<GeneratorEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GeneratorEfficiency::getIsDeleted, 0);

        if (generatorType != null && !generatorType.trim().isEmpty()) {
            wrapper.eq(GeneratorEfficiency::getGeneratorType, generatorType);
        }

        wrapper.orderByAsc(GeneratorEfficiency::getRatedCapacity, GeneratorEfficiency::getRatedPower,
                GeneratorEfficiency::getRotorPoles, GeneratorEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
