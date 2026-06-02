package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Device;
import com.example.demo.entity.DeviceQueryDTO;
import com.example.demo.entity.DeviceTreeDTO;

import java.util.List;

public interface DeviceService extends IService<Device> {

    List<Device> getAllDevices();

    List<DeviceTreeDTO> getDeviceTree(DeviceQueryDTO queryDTO);
}
