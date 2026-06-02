package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Device;
import com.example.demo.entity.DeviceQueryDTO;
import com.example.demo.entity.DeviceTreeDTO;
import com.example.demo.mapper.DeviceMapper;
import com.example.demo.service.DeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0).orderByAsc("sort", "id");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<DeviceTreeDTO> getDeviceTree(DeviceQueryDTO queryDTO) {
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);

        boolean hasNameFilter = queryDTO != null && queryDTO.getName() != null && !queryDTO.getName().trim().isEmpty();
        boolean hasParentFilter = queryDTO != null && queryDTO.getParentCode() != null && !queryDTO.getParentCode().trim().isEmpty();

        if (hasNameFilter) {
            wrapper.like("name", queryDTO.getName());
        }

        wrapper.orderByAsc("sort", "id");
        List<Device> filteredDevices = baseMapper.selectList(wrapper);

        if (hasParentFilter) {
            Device parentDevice = baseMapper.selectByCode(queryDTO.getParentCode());
            if (parentDevice != null && !hasNameFilter) {
                List<Device> allDevices = getAllDevices();
                return buildTreeWithParent(allDevices, parentDevice);
            } else if (parentDevice != null) {
                filteredDevices.add(0, parentDevice);
                return buildTreeWithParent(filteredDevices, parentDevice);
            }
        }

        return buildTree(filteredDevices, null);
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

    private List<DeviceTreeDTO> buildTree(List<Device> devices, String parentCode) {
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

        if (parentCode == null || parentCode.trim().isEmpty()) {
            return treeList.stream()
                    .filter(d -> d.getParentCode() == null)
                    .collect(Collectors.toList());
        } else {
            return treeList.stream()
                    .filter(d -> d.getCode().equals(parentCode))
                    .collect(Collectors.toList());
        }
    }
}
