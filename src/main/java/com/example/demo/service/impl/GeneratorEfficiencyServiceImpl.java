package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<GeneratorEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);

        if (generatorType != null && !generatorType.trim().isEmpty()) {
            wrapper.eq("generator_type", generatorType);
        }

        wrapper.orderByAsc("rated_capacity", "rotor_poles", "efficiency_level", "sort");
        return baseMapper.selectList(wrapper);
    }
}
