package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.CraneEfficiency;
import com.example.demo.mapper.CraneEfficiencyMapper;
import com.example.demo.service.CraneEfficiencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CraneEfficiencyServiceImpl extends ServiceImpl<CraneEfficiencyMapper, CraneEfficiency> implements CraneEfficiencyService {

    @Override
    public List<CraneEfficiency> getList() {
        LambdaQueryWrapper<CraneEfficiency> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CraneEfficiency::getIsDeleted, 0)
                .orderByAsc(CraneEfficiency::getSort);
        return baseMapper.selectList(wrapper);
    }
}
