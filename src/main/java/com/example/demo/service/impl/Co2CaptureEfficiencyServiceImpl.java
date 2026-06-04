package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Co2CaptureEfficiency;
import com.example.demo.mapper.Co2CaptureEfficiencyMapper;
import com.example.demo.service.Co2CaptureEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Co2CaptureEfficiencyServiceImpl extends ServiceImpl<Co2CaptureEfficiencyMapper, Co2CaptureEfficiency> implements Co2CaptureEfficiencyService {

    @Override
    public List<Co2CaptureEfficiency> getList() {
        LambdaQueryWrapper<Co2CaptureEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Co2CaptureEfficiency::getIsDeleted, 0)
                .orderByAsc(Co2CaptureEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
