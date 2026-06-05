package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Device;
import com.example.demo.entity.dto.DeviceOptionDTO;
import com.example.demo.entity.dto.DeviceQueryDTO;
import com.example.demo.entity.dto.DeviceTreeDTO;
import com.example.demo.mapper.DeviceMapper;
import com.example.demo.service.DeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Override
    @Transactional
    public boolean save(Device device) {
        if (device.getIsDeleted() == null) {
            device.setIsDeleted(0);
        }
        if (device.getSort() == null) {
            device.setSort(0);
        }
        device.setCreateTime(LocalDateTime.now());
        device.setUpdateTime(LocalDateTime.now());
        return super.save(device);
    }

    @Override
    public List<Device> getAllDevices() {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Device::getIsDeleted, 0)
                .orderByAsc(Device::getSort, Device::getId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<DeviceTreeDTO> getDeviceTree(DeviceQueryDTO queryDTO) {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Device::getIsDeleted, 0)
                .orderByAsc(Device::getSort, Device::getId);

        boolean hasNameFilter = queryDTO != null && queryDTO.getName() != null && !queryDTO.getName().trim().isEmpty();
        boolean hasParentFilter = queryDTO != null && queryDTO.getParentCode() != null && !queryDTO.getParentCode().trim().isEmpty();

        if (hasParentFilter) {
            Device parentDevice = baseMapper.selectOne(new LambdaQueryWrapper<Device>()
                    .eq(Device::getIsDeleted, 0)
                    .eq(Device::getCode, queryDTO.getParentCode()));

            if (parentDevice == null) {
                return new ArrayList<>();
            }

            LambdaQueryWrapper<Device> childWrapper = new LambdaQueryWrapper<Device>()
                    .eq(Device::getIsDeleted, 0)
                    .eq(Device::getParentCode, parentDevice.getCode());

            if (hasNameFilter) {
                childWrapper.like(Device::getName, queryDTO.getName());
            }

            childWrapper.orderByAsc(Device::getSort, Device::getId);

            List<Device> childDevices = baseMapper.selectList(childWrapper);

            if (childDevices.isEmpty()) {
                return new ArrayList<>();
            }

            List<Device> allDevices = new ArrayList<>();
            allDevices.add(parentDevice);
            allDevices.addAll(childDevices);

            return buildTreeWithParent(allDevices, parentDevice);
        }

        if (hasNameFilter) {
            List<Device> matchedDevices = baseMapper.selectList(wrapper.clone().like(Device::getName, queryDTO.getName()));

            if (matchedDevices.isEmpty()) {
                return new ArrayList<>();
            }

            List<String> parentCodes = new ArrayList<>();
            for (Device device : matchedDevices) {
                if (device.getParentCode() != null && !parentCodes.contains(device.getParentCode())) {
                    parentCodes.add(device.getParentCode());
                }
            }

            List<Device> allDevices = new ArrayList<>(matchedDevices);

            if (!parentCodes.isEmpty()) {
                List<Device> parentDevices = baseMapper.selectList(new LambdaQueryWrapper<Device>()
                        .eq(Device::getIsDeleted, 0)
                        .in(Device::getCode, parentCodes));

                for (Device parentDevice : parentDevices) {
                    if (allDevices.stream().noneMatch(d -> d.getId().equals(parentDevice.getId()))) {
                        allDevices.add(parentDevice);
                    }
                }
            }

            return buildTreeWithFilter(allDevices, queryDTO.getName());
        }

        List<Device> allDevices = baseMapper.selectList(wrapper);
        return buildTree(allDevices);
    }

    @Override
    public List<DeviceOptionDTO> getParentDeviceOptions() {
        List<Device> parentDevices = baseMapper.selectParentDevices();
        return parentDevices.stream()
                .map(device -> new DeviceOptionDTO(device.getId(), device.getCode(), device.getName()))
                .collect(Collectors.toList());
    }

    private List<DeviceTreeDTO> buildTreeWithParent(List<Device> devices, Device parentDevice) {
        List<DeviceTreeDTO> treeList = new ArrayList<>();

        for (Device device : devices) {
            DeviceTreeDTO dto = new DeviceTreeDTO();
            BeanUtils.copyProperties(device, dto);
            treeList.add(dto);
        }

        Map<String, List<DeviceTreeDTO>> parentMap = treeList.stream()
                .filter(d -> d.getParentCode() != null)
                .collect(Collectors.groupingBy(DeviceTreeDTO::getParentCode));

        for (DeviceTreeDTO dto : treeList) {
            List<DeviceTreeDTO> children = parentMap.get(dto.getCode());
            if (children != null) {
                dto.setChildren(children);
            }
        }

        return treeList.stream()
                .filter(d -> d.getCode().equals(parentDevice.getCode()))
                .collect(Collectors.toList());
    }

    private List<DeviceTreeDTO> buildTreeWithFilter(List<Device> devices, String filterName) {
        List<DeviceTreeDTO> treeList = new ArrayList<>();

        for (Device device : devices) {
            DeviceTreeDTO dto = new DeviceTreeDTO();
            BeanUtils.copyProperties(device, dto);
            treeList.add(dto);
        }

        Map<String, List<DeviceTreeDTO>> parentMap = treeList.stream()
                .filter(d -> d.getParentCode() != null)
                .collect(Collectors.groupingBy(DeviceTreeDTO::getParentCode));

        for (DeviceTreeDTO dto : treeList) {
            List<DeviceTreeDTO> children = parentMap.get(dto.getCode());
            if (children != null) {
                dto.setChildren(children);
            }
        }

        Map<String, DeviceTreeDTO> deviceMap = treeList.stream()
                .collect(Collectors.toMap(DeviceTreeDTO::getCode, d -> d));

        return treeList.stream()
                .filter(d -> {
                    if (d.getParentCode() == null) {
                        if (d.getName().contains(filterName)) {
                            return true;
                        }
                        return hasMatchingChild(d, filterName, deviceMap);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private boolean hasMatchingChild(DeviceTreeDTO node, String filterName, Map<String, DeviceTreeDTO> deviceMap) {
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            return false;
        }
        for (DeviceTreeDTO child : node.getChildren()) {
            if (child.getName().contains(filterName)) {
                return true;
            }
            if (hasMatchingChild(child, filterName, deviceMap)) {
                return true;
            }
        }
        return false;
    }

    private List<DeviceTreeDTO> buildTree(List<Device> devices) {
        List<DeviceTreeDTO> treeList = new ArrayList<>();

        for (Device device : devices) {
            DeviceTreeDTO dto = new DeviceTreeDTO();
            BeanUtils.copyProperties(device, dto);
            treeList.add(dto);
        }

        Map<String, List<DeviceTreeDTO>> parentMap = treeList.stream()
                .filter(d -> d.getParentCode() != null)
                .collect(Collectors.groupingBy(DeviceTreeDTO::getParentCode));

        for (DeviceTreeDTO dto : treeList) {
            List<DeviceTreeDTO> children = parentMap.get(dto.getCode());
            if (children != null) {
                dto.setChildren(children);
            }
        }

        return treeList.stream()
                .filter(d -> d.getParentCode() == null)
                .collect(Collectors.toList());
    }
}
