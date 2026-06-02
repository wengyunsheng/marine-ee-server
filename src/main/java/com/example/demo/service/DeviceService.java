package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Device;
import com.example.demo.entity.DeviceTreeDTO;

import java.util.List;

public interface DeviceService extends IService<Device> {

    Device getDeviceByCode(String code);

    List<Device> getAllDevices();

    List<Device> getParentDevices();

    List<Device> getChildDevices(String parentCode);

    List<Device> getDevicesByParentCode(String parentCode);

    int countByParentCode(String parentCode);

    int logicDelete(Long id);

    List<DeviceTreeDTO> getDeviceTree();

    List<DeviceTreeDTO> getDeviceTreeByParentCode(String parentCode);
}
