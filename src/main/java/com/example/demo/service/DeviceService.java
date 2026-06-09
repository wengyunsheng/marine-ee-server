package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Device;
import com.example.demo.entity.vo.DeviceOptionVO;
import com.example.demo.entity.dto.DeviceQueryDTO;
import com.example.demo.entity.vo.DeviceTreeVO;

import java.util.List;

public interface DeviceService extends IService<Device> {

    List<DeviceTreeVO> getDeviceTree(DeviceQueryDTO queryDTO);

    List<DeviceOptionVO> getParentDeviceOptions();
}
