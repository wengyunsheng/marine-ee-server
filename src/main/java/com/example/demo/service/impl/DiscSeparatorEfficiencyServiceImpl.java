package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.DiscSeparatorEfficiency;
import com.example.demo.mapper.DiscSeparatorEfficiencyMapper;
import com.example.demo.service.DiscSeparatorEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscSeparatorEfficiencyServiceImpl extends ServiceImpl<DiscSeparatorEfficiencyMapper, DiscSeparatorEfficiency> implements DiscSeparatorEfficiencyService {

    @Override
    public List<DiscSeparatorEfficiency> getList() {
        QueryWrapper<DiscSeparatorEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0)
                .orderByAsc("sort");
        return baseMapper.selectList(wrapper);
    }
}
