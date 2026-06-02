package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.IncineratorEfficiency;
import com.example.demo.mapper.IncineratorEfficiencyMapper;
import com.example.demo.service.IncineratorEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncineratorEfficiencyServiceImpl extends ServiceImpl<IncineratorEfficiencyMapper, IncineratorEfficiency> implements IncineratorEfficiencyService {

    @Override
    public List<IncineratorEfficiency> getList(String incineratorType) {
        QueryWrapper<IncineratorEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);

        if (incineratorType != null && !incineratorType.trim().isEmpty()) {
            wrapper.eq("incinerator_type", incineratorType);
        }

        wrapper.orderByAsc("sort");
        return baseMapper.selectList(wrapper);
    }
}
