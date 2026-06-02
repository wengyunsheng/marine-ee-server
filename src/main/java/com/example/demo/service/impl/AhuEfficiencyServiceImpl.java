package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.AhuEfficiency;
import com.example.demo.mapper.AhuEfficiencyMapper;
import com.example.demo.service.AhuEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AhuEfficiencyServiceImpl extends ServiceImpl<AhuEfficiencyMapper, AhuEfficiency> implements AhuEfficiencyService {

    @Override
    public List<AhuEfficiency> getList() {
        QueryWrapper<AhuEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0)
                .orderByAsc("air_flow_min", "static_pressure", "efficiency_level", "sort");
        return baseMapper.selectList(wrapper);
    }
}
