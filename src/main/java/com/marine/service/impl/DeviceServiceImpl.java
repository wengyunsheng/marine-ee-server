package com.marine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marine.entity.Device;
import com.marine.entity.FileInfo;
import com.marine.entity.vo.DeviceOptionVO;
import com.marine.entity.dto.DeviceQueryDTO;
import com.marine.entity.vo.DeviceTreeVO;
import com.marine.mapper.DeviceMapper;
import com.marine.service.DeviceService;
import com.marine.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    private final FileInfoService fileInfoService;

    @Value("${file.upload.path}")
    private String basePath;

    @Override
    public List<DeviceTreeVO> getDeviceTree(DeviceQueryDTO queryDTO) {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Device::getIsDeleted, 0)
                .orderByAsc(Device::getId);

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

            childWrapper.orderByAsc(Device::getId);

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

        // 获取上传3D模型信息
        List<Long> fileInfoIds = allDevices.stream()
                .map(Device::getModelFileId)
                .collect(Collectors.toList());
        List<FileInfo> fileInfos = fileInfoService.listByIds(fileInfoIds);
        Map<Long, String> fileInfoMap = fileInfos.stream()
                .collect(Collectors.toMap(FileInfo::getId, FileInfo::getFilePath));
        return buildTree(fileInfoMap, allDevices);
    }

    @Override
    public List<DeviceOptionVO> getParentDeviceOptions() {
        List<Device> parentDevices = baseMapper.selectParentDevices();
        return parentDevices.stream()
                .map(device -> new DeviceOptionVO(device.getId(), device.getCode(), device.getName()))
                .collect(Collectors.toList());
    }

    private List<DeviceTreeVO> buildTreeWithParent(List<Device> devices, Device parentDevice) {
        List<DeviceTreeVO> treeList = new ArrayList<>();

        for (Device device : devices) {
            DeviceTreeVO dto = new DeviceTreeVO();
            BeanUtils.copyProperties(device, dto);
            treeList.add(dto);
        }

        Map<String, List<DeviceTreeVO>> parentMap = treeList.stream()
                .filter(d -> d.getParentCode() != null)
                .collect(Collectors.groupingBy(DeviceTreeVO::getParentCode));

        for (DeviceTreeVO dto : treeList) {
            List<DeviceTreeVO> children = parentMap.get(dto.getCode());
            if (children != null) {
                dto.setChildren(children);
            }
        }

        return treeList.stream()
                .filter(d -> d.getCode().equals(parentDevice.getCode()))
                .collect(Collectors.toList());
    }

    private List<DeviceTreeVO> buildTreeWithFilter(List<Device> devices, String filterName) {
        List<DeviceTreeVO> treeList = new ArrayList<>();

        for (Device device : devices) {
            DeviceTreeVO dto = new DeviceTreeVO();
            BeanUtils.copyProperties(device, dto);
            treeList.add(dto);
        }

        Map<String, List<DeviceTreeVO>> parentMap = treeList.stream()
                .filter(d -> d.getParentCode() != null)
                .collect(Collectors.groupingBy(DeviceTreeVO::getParentCode));

        for (DeviceTreeVO dto : treeList) {
            List<DeviceTreeVO> children = parentMap.get(dto.getCode());
            if (children != null) {
                dto.setChildren(children);
            }
        }

        return treeList.stream()
                .filter(d -> {
                    if (d.getParentCode() == null) {
                        if (d.getName().contains(filterName)) {
                            return true;
                        }
                        return hasMatchingChild(d, filterName);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private boolean hasMatchingChild(DeviceTreeVO node, String filterName) {
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            return false;
        }
        for (DeviceTreeVO child : node.getChildren()) {
            if (child.getName().contains(filterName)) {
                return true;
            }
            if (hasMatchingChild(child, filterName)) {
                return true;
            }
        }
        return false;
    }

    private List<DeviceTreeVO> buildTree(Map<Long, String> fileInfoMap, List<Device> devices) {
        List<DeviceTreeVO> treeList = new ArrayList<>();

        for (Device device : devices) {
            DeviceTreeVO dto = new DeviceTreeVO();
            BeanUtils.copyProperties(device, dto);
            String filePath = fileInfoMap.get(device.getModelFileId());
            if (StringUtils.isNotBlank(filePath)) {
                String fileUrl = "/uploads/" + filePath.replace(basePath, "").replace("\\", "/");
                dto.setModelFileUrl(fileUrl);
            }
            treeList.add(dto);
        }

        Map<String, List<DeviceTreeVO>> parentMap = treeList.stream()
                .filter(d -> d.getParentCode() != null)
                .collect(Collectors.groupingBy(DeviceTreeVO::getParentCode));

        for (DeviceTreeVO dto : treeList) {
            List<DeviceTreeVO> children = parentMap.get(dto.getCode());
            if (children != null) {
                dto.setChildren(children);
            }
        }

        return treeList.stream()
                .filter(d -> d.getParentCode() == null)
                .collect(Collectors.toList());
    }
}
