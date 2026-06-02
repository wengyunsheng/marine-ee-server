package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<Co2CaptureEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0)
                .orderByAsc("sort");
        return baseMapper.selectList(wrapper);
    }
}
