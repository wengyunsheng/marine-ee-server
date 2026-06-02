package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.EngineEfficiency;

import java.util.List;

public interface EngineEfficiencyService extends IService<EngineEfficiency> {

    List<EngineEfficiency> getList(String engineType);
}
