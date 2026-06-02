package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.IncineratorEfficiency;

import java.util.List;

public interface IncineratorEfficiencyService extends IService<IncineratorEfficiency> {

    List<IncineratorEfficiency> getList(String incineratorType);
}
