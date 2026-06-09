package com.marine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.marine.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {

    Device selectByCode(@Param("code") String code);

    List<Device> selectParentDevices();

}
