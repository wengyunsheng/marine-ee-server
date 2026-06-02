package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {

    List<Device> selectParentDevices();

    List<Device> selectChildDevices(@Param("parentCode") String parentCode);

    List<Device> selectByParentCode(@Param("parentCode") String parentCode);

    int countByParentCode(@Param("parentCode") String parentCode);

    int logicDeleteById(@Param("id") Long id);
}
