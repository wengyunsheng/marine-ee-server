package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Device;
import com.example.demo.entity.dto.DeviceOptionDTO;
import com.example.demo.entity.dto.DeviceQueryDTO;
import com.example.demo.entity.dto.DeviceTreeDTO;

import java.util.List;

public interface DeviceService extends IService<Device> {

    List<Device> getAllDevices();

    List<DeviceTreeDTO> getDeviceTree(DeviceQueryDTO queryDTO);

    List<DeviceOptionDTO> getParentDeviceOptions();
}
