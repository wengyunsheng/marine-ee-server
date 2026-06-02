package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.WasteHeatOrganicRankineEfficiency;

import java.util.List;

public interface WasteHeatOrganicRankineEfficiencyService extends IService<WasteHeatOrganicRankineEfficiency> {

    List<WasteHeatOrganicRankineEfficiency> getList();
}
