package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.FileInfo;
import com.example.demo.mapper.FileInfoMapper;
import com.example.demo.service.FileInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

    @Override
    public FileInfo getByBusinessId(String businessType, Long businessId) {
        QueryWrapper<FileInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("business_type", businessType)
                .eq("business_id", businessId)
                .eq("is_deleted", 0);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional
    public void logicDeleteById(Long id) {
        baseMapper.logicDeleteById(id);
    }
}
