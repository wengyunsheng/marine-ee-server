package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ChillerEfficiency;
import com.example.demo.mapper.ChillerEfficiencyMapper;
import com.example.demo.service.ChillerEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChillerEfficiencyServiceImpl extends ServiceImpl<ChillerEfficiencyMapper, ChillerEfficiency> implements ChillerEfficiencyService {

    @Override
    public List<ChillerEfficiency> getList() {
        LambdaQueryWrapper<ChillerEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChillerEfficiency::getIsDeleted, 0)
                .orderByAsc(ChillerEfficiency::getEvaluationType, ChillerEfficiency::getCoolingCapacityMin, ChillerEfficiency::getCoolingCapacityMax, ChillerEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
