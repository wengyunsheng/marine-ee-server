package com.marine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marine.entity.FileInfo;
import com.marine.mapper.FileInfoMapper;
import com.marine.service.FileInfoService;
import org.springframework.stereotype.Service;

@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {

}
