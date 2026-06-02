package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    FileInfo selectByBusinessId(@Param("businessType") String businessType,
                                @Param("businessId") Long businessId);

    int logicDeleteById(@Param("id") Long id);
}
