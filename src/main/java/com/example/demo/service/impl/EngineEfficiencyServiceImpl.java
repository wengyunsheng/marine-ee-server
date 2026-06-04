package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<EngineEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EngineEfficiency::getIsDeleted, 0);

        if (engineType != null && !engineType.trim().isEmpty()) {
            wrapper.eq(EngineEfficiency::getEngineType, engineType);
        }

        wrapper.orderByAsc(EngineEfficiency::getEmissionLevel, EngineEfficiency::getPowerRangeMin, EngineEfficiency::getPowerRangeMax, EngineEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
