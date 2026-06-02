package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.FileInfo;

public interface FileInfoService extends IService<FileInfo> {

    FileInfo getByBusinessId(String businessType, Long businessId);

    void logicDeleteById(Long id);
}
