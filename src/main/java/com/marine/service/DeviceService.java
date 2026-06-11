package com.marine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marine.entity.Device;
import com.marine.entity.dto.DeviceQueryDTO;
import com.marine.entity.vo.DeviceOptionVO;
import com.marine.entity.vo.DeviceTreeVO;

import java.util.List;

public interface DeviceService extends IService<Device> {

    /**
     * 获取设备树形结构
     *
     * @param queryDTO 设备查询条件
     * @return 设备树形结构列表
     */
    List<DeviceTreeVO> getDeviceTree(DeviceQueryDTO queryDTO);

    /**
     * 获取父级设备选项列表
     *
     * @return 父级设备选项列表
     */
    List<DeviceOptionVO> getParentDeviceOptions();
}
