package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Co2CaptureEfficiency;

import java.util.List;

public interface Co2CaptureEfficiencyService extends IService<Co2CaptureEfficiency> {

    List<Co2CaptureEfficiency> getList();
}
