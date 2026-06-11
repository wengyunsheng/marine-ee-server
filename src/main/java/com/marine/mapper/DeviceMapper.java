package com.marine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.marine.entity.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {

    /**
     * 查询所有父级设备（没有父设备的顶级设备）
     *
     * @return 父级设备列表
     */
    List<Device> selectParentDevices();

}
