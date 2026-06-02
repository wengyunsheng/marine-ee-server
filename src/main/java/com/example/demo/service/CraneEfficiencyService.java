package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.CraneEfficiency;

import java.util.List;

public interface CraneEfficiencyService extends IService<CraneEfficiency> {

    List<CraneEfficiency> getList();
}
