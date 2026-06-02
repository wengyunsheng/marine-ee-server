package com.example.demo.controller;

import com.example.demo.entity.Device;
import com.example.demo.entity.DeviceTreeDTO;
import com.example.demo.entity.Result;
import com.example.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public Result<Device> addDevice(@RequestBody Device device) {
        try {
            boolean success = deviceService.save(device);
            if (success) {
                return Result.success("添加成功", device);
            } else {
                return Result.error("添加失败");
            }
        } catch (Exception e) {
            return Result.error("添加失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteDevice(@PathVariable Long id) {
        try {
            boolean success = deviceService.removeById(id);
            if (success) {
                return Result.successMessage("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error("删除失败: " + e.getMessage());
        }
    }

    @PutMapping
    public Result<Device> updateDevice(@RequestBody Device device) {
        try {
            boolean success = deviceService.updateById(device);
            if (success) {
                return Result.success("更新成功", device);
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error("更新失败: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getById(id);
        if (device != null) {
            return Result.success(device);
        } else {
            return Result.notFound("设备不存在");
        }
    }

    @GetMapping("/code/{code}")
    public Result<Device> getDeviceByCode(@PathVariable String code) {
        Device device = deviceService.getDeviceByCode(code);
        if (device != null) {
            return Result.success(device);
        } else {
            return Result.notFound("设备不存在");
        }
    }

    @GetMapping
    public Result<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return Result.success(devices, devices.size());
    }

    @GetMapping("/parents")
    public Result<List<Device>> getParentDevices() {
        List<Device> devices = deviceService.getParentDevices();
        return Result.success(devices, devices.size());
    }

    @GetMapping("/children/{parentCode}")
    public Result<List<Device>> getChildDevices(@PathVariable String parentCode) {
        List<Device> devices = deviceService.getDevicesByParentCode(parentCode);
        return Result.success(devices, devices.size());
    }

    @PostMapping("/{id}/logic-delete")
    public Result<Void> logicDelete(@PathVariable Long id) {
        try {
            int rows = deviceService.logicDelete(id);
            if (rows > 0) {
                return Result.successMessage("逻辑删除成功");
            } else {
                return Result.error("逻辑删除失败");
            }
        } catch (Exception e) {
            return Result.error("逻辑删除失败: " + e.getMessage());
        }
    }

    @GetMapping("/tree")
    public Result<List<DeviceTreeDTO>> getDeviceTree() {
        List<DeviceTreeDTO> tree = deviceService.getDeviceTree();
        return Result.success(tree, tree.size());
    }

    @GetMapping("/tree/{parentCode}")
    public Result<List<DeviceTreeDTO>> getDeviceTreeByParentCode(@PathVariable String parentCode) {
        List<DeviceTreeDTO> tree = deviceService.getDeviceTreeByParentCode(parentCode);
        return Result.success(tree, tree.size());
    }
}
