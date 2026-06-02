package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.BallastWaterTreatmentEfficiency;
import com.example.demo.mapper.BallastWaterTreatmentEfficiencyMapper;
import com.example.demo.service.BallastWaterTreatmentEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallastWaterTreatmentEfficiencyServiceImpl extends ServiceImpl<BallastWaterTreatmentEfficiencyMapper, BallastWaterTreatmentEfficiency> implements BallastWaterTreatmentEfficiencyService {

    @Override
    public List<BallastWaterTreatmentEfficiency> getList() {
        QueryWrapper<BallastWaterTreatmentEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0)
                .orderByAsc("sort");
        return baseMapper.selectList(wrapper);
    }
}
