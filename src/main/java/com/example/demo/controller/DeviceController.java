package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.entity.dto.DeviceOptionDTO;
import com.example.demo.entity.dto.DeviceQueryDTO;
import com.example.demo.entity.dto.DeviceTreeDTO;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备管理控制器
 * 提供设备的增删改查、树形结构查询等功能
 *
 * @author admin
 * @since 2026-06-02
 */
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 新增设备
     *
     * @param device 设备信息
     * @return 操作结果
     */
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

    /**
     * 删除设备（物理删除）
     *
     * @param id 设备ID
     * @return 操作结果
     */
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

    /**
     * 更新设备信息
     *
     * @param device 设备信息
     * @return 操作结果
     */
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

    /**
     * 获取所有父设备列表（用于下拉选择）
     * 返回简化的设备信息（id, code, name）
     *
     * @return 父设备选项列表
     */
    @GetMapping("/parent-options")
    public Result<List<DeviceOptionDTO>> getParentDeviceOptions() {
        List<DeviceOptionDTO> options = deviceService.getParentDeviceOptions();
        return Result.success(options, options.size());
    }

    /**
     * 获取设备树形结构
     * 支持按设备名称模糊搜索和按父设备编码筛选
     *
     * @param queryDTO 查询条件
     *                 - name: 设备名称（可选，模糊查询）
     *                 - parentCode: 父设备编码（可选，指定后返回该父设备及其所有子孙节点的树）
     * @return 设备树形结构列表
     */
    @PostMapping("/tree")
    public Result<List<DeviceTreeDTO>> getDeviceTree(@RequestBody DeviceQueryDTO queryDTO) {
        List<DeviceTreeDTO> tree = deviceService.getDeviceTree(queryDTO);
        return Result.success(tree, tree.size());
    }

}
