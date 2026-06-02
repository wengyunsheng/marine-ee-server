package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.DiscSeparatorEfficiency;

import java.util.List;

public interface DiscSeparatorEfficiencyService extends IService<DiscSeparatorEfficiency> {

    List<DiscSeparatorEfficiency> getList();
}
