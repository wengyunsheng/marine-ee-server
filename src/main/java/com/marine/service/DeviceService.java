package com.marine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marine.entity.Device;
import com.marine.entity.vo.DeviceOptionVO;
import com.marine.entity.dto.DeviceQueryDTO;
import com.marine.entity.vo.DeviceTreeVO;

import java.util.List;

public interface DeviceService extends IService<Device> {

    List<DeviceTreeVO> getDeviceTree(DeviceQueryDTO queryDTO);

    List<DeviceOptionVO> getParentDeviceOptions();
}
