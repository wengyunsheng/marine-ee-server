package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.EngineEfficiencyLevel;
import com.example.demo.mapper.EngineEfficiencyLevelMapper;
import com.example.demo.service.EngineEfficiencyLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineEfficiencyLevelServiceImpl extends ServiceImpl<EngineEfficiencyLevelMapper, EngineEfficiencyLevel> implements EngineEfficiencyLevelService {

    @Override
    public List<EngineEfficiencyLevel> getList(String engineType) {
        QueryWrapper<EngineEfficiencyLevel> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);

        if (engineType != null && !engineType.trim().isEmpty()) {
            wrapper.eq("engine_type", engineType);
        }

        wrapper.orderByAsc("emission_level", "power_range_min", "efficiency_level", "sort");
        return baseMapper.selectList(wrapper);
    }
}
