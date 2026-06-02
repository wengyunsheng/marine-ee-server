package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.GeneratorEfficiency;

import java.util.List;

public interface GeneratorEfficiencyService extends IService<GeneratorEfficiency> {

    List<GeneratorEfficiency> getList(String generatorType);
}
