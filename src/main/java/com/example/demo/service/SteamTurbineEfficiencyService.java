package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.SteamTurbineEfficiency;

import java.util.List;

public interface SteamTurbineEfficiencyService extends IService<SteamTurbineEfficiency> {

    List<SteamTurbineEfficiency> getList();
}
