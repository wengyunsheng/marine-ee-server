package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.SteamTurbineEfficiency;
import com.example.demo.mapper.SteamTurbineEfficiencyMapper;
import com.example.demo.service.SteamTurbineEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SteamTurbineEfficiencyServiceImpl extends ServiceImpl<SteamTurbineEfficiencyMapper, SteamTurbineEfficiency> implements SteamTurbineEfficiencyService {

    @Override
    public List<SteamTurbineEfficiency> getList() {
        LambdaQueryWrapper<SteamTurbineEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SteamTurbineEfficiency::getIsDeleted, 0)
                .orderByAsc(SteamTurbineEfficiency::getSteamPressureMin, SteamTurbineEfficiency::getSteamPressureMax,
                        SteamTurbineEfficiency::getSteamType, SteamTurbineEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
