package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<AhuEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AhuEfficiency::getIsDeleted, 0)
                .orderByAsc(AhuEfficiency::getAirFlowMin, AhuEfficiency::getAirFlowMax, AhuEfficiency::getStaticPressure, AhuEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
