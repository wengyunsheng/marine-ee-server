package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.AhuEfficiency;

import java.util.List;

public interface AhuEfficiencyService extends IService<AhuEfficiency> {

    List<AhuEfficiency> getList();
}
