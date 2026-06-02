package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.WindlassEfficiency;
import com.example.demo.mapper.WindlassEfficiencyMapper;
import com.example.demo.service.WindlassEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WindlassEfficiencyServiceImpl extends ServiceImpl<WindlassEfficiencyMapper, WindlassEfficiency> implements WindlassEfficiencyService {

    @Override
    public List<WindlassEfficiency> getList(String windlassType) {
        QueryWrapper<WindlassEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);

        if (windlassType != null && !windlassType.trim().isEmpty()) {
            wrapper.eq("windlass_type", windlassType);
        }

        wrapper.orderByAsc("sort");
        return baseMapper.selectList(wrapper);
    }
}
