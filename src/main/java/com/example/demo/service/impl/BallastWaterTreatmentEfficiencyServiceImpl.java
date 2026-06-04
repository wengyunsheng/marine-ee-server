package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<BallastWaterTreatmentEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BallastWaterTreatmentEfficiency::getIsDeleted, 0)
                .orderByAsc(BallastWaterTreatmentEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
