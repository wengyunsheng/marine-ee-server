package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        LambdaQueryWrapper<WasteHeatOrganicRankineEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WasteHeatOrganicRankineEfficiency::getIsDeleted, 0)
                .orderByAsc(WasteHeatOrganicRankineEfficiency::getHeatSourceTempMin, WasteHeatOrganicRankineEfficiency::getHeatSourceTempMax,
                        WasteHeatOrganicRankineEfficiency::getPowerOutputMin, WasteHeatOrganicRankineEfficiency::getPowerOutputMin,
                        WasteHeatOrganicRankineEfficiency::getEfficiencyLevel);
        return baseMapper.selectList(wrapper);
    }
}
