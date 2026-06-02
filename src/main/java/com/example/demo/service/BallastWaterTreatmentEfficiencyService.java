package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.BallastWaterTreatmentEfficiency;

import java.util.List;

public interface BallastWaterTreatmentEfficiencyService extends IService<BallastWaterTreatmentEfficiency> {

    List<BallastWaterTreatmentEfficiency> getList();
}
