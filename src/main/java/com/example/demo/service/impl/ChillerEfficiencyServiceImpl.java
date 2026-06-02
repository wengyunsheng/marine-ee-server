package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ChillerEfficiency;
import com.example.demo.mapper.ChillerEfficiencyMapper;
import com.example.demo.service.ChillerEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChillerEfficiencyServiceImpl extends ServiceImpl<ChillerEfficiencyMapper, ChillerEfficiency> implements ChillerEfficiencyService {

    @Override
    public List<ChillerEfficiency> getList() {
        QueryWrapper<ChillerEfficiency> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0)
                .orderByAsc("sort");
        return baseMapper.selectList(wrapper);
    }
}
