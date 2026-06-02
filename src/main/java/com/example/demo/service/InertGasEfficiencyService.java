package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.InertGasEfficiency;

import java.util.List;

public interface InertGasEfficiencyService extends IService<InertGasEfficiency> {

    List<InertGasEfficiency> getList();
}
