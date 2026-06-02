package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.WasteHeatOrganicRankineEfficiency;
import com.example.demo.mapper.WasteHeatOrganicRankineEfficiencyMapper;
import com.example.demo.service.WasteHeatOrganicRankineEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WasteHeatOrganicRankineEfficiencyServiceImpl extends ServiceImpl<WasteHeatOrganicRankineEfficiencyMapper, WasteHeatOrganicRankineEfficiency> implements WasteHeatOrganicRankineEfficiencyService {

    @Override
    public List<WasteHeatOrganicRankineEfficiency> getList() {
        QueryWrapper<WasteHeatOrganicRankineEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0)
                .orderByAsc("sort");
        return baseMapper.selectList(wrapper);
    }
}
