package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.WindlassEfficiency;

import java.util.List;

public interface WindlassEfficiencyService extends IService<WindlassEfficiency> {

    List<WindlassEfficiency> getList(String windlassType);
}
