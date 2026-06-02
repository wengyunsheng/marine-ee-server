package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.EngineEfficiencyLevel;

import java.util.List;

public interface EngineEfficiencyLevelService extends IService<EngineEfficiencyLevel> {

    List<EngineEfficiencyLevel> getList(String engineType);
}
