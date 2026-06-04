package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.InertGasEfficiency;
import com.example.demo.mapper.InertGasEfficiencyMapper;
import com.example.demo.service.InertGasEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InertGasEfficiencyServiceImpl extends ServiceImpl<InertGasEfficiencyMapper, InertGasEfficiency> implements InertGasEfficiencyService {

    @Override
    public List<InertGasEfficiency> getList() {
        LambdaQueryWrapper<InertGasEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InertGasEfficiency::getIsDeleted, 0)
                .orderByAsc(InertGasEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
