package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<IncineratorEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(IncineratorEfficiency::getIsDeleted, 0);

        if (incineratorType != null && !incineratorType.trim().isEmpty()) {
            wrapper.eq(IncineratorEfficiency::getIncineratorType, incineratorType);
        }

        wrapper.orderByAsc(IncineratorEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
