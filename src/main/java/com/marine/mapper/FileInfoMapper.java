package com.marine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.marine.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    int logicDeleteById(@Param("id") Long id);
}
