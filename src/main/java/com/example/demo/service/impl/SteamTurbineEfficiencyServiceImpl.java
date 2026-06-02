package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<SteamTurbineEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0)
                .orderByAsc("sort");
        return baseMapper.selectList(wrapper);
    }
}
