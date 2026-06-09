package com.example.demo.controller;

import com.example.demo.entity.dto.DeviceOptionDTO;
import com.example.demo.entity.dto.DeviceQueryDTO;
import com.example.demo.entity.dto.DeviceTreeDTO;
import com.example.demo.entity.dto.Result;
import com.example.demo.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 设备管理控制器
 *
 * @author admin
 * @since 2026-06-02
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    /**
     * 获取所有父设备列表（用于下拉选择）
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
