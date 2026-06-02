package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.EngineEfficiency;
import com.example.demo.mapper.EngineEfficiencyMapper;
import com.example.demo.service.EngineEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineEfficiencyServiceImpl extends ServiceImpl<EngineEfficiencyMapper, EngineEfficiency> implements EngineEfficiencyService {

    @Override
    public List<EngineEfficiency> getList(String engineType) {
        QueryWrapper<EngineEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);

        if (engineType != null && !engineType.trim().isEmpty()) {
            wrapper.eq("engine_type", engineType);
        }

        wrapper.orderByAsc("emission_level", "power_range_min", "efficiency_level", "sort");
        return baseMapper.selectList(wrapper);
    }
}
