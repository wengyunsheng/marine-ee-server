package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Device;
import com.example.demo.entity.DeviceTreeDTO;
import com.example.demo.mapper.DeviceMapper;
import com.example.demo.service.DeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        device.setCreateTime(new Date());
        device.setUpdateTime(new Date());
        return super.save(device);
    }

    @Override
    public Device getDeviceByCode(String code) {
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("code", code).eq("is_deleted", 0);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<Device> getAllDevices() {
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0).orderByAsc("sort", "id");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Device> getParentDevices() {
        return baseMapper.selectParentDevices();
    }

    @Override
    public List<Device> getChildDevices(String parentCode) {
        return baseMapper.selectChildDevices(parentCode);
    }

    @Override
    public List<Device> getDevicesByParentCode(String parentCode) {
        return baseMapper.selectByParentCode(parentCode);
    }

    @Override
    public int countByParentCode(String parentCode) {
        return baseMapper.countByParentCode(parentCode);
    }

    @Override
    @Transactional
    public int logicDelete(Long id) {
        return baseMapper.logicDeleteById(id);
    }

    @Override
    public List<DeviceTreeDTO> getDeviceTree() {
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0).orderByAsc("sort", "id");
        List<Device> allDevices = baseMapper.selectList(wrapper);

        return buildTree(allDevices, null);
    }

    @Override
    public List<DeviceTreeDTO> getDeviceTreeByParentCode(String parentCode) {
        List<Device> children = baseMapper.selectByParentCode(parentCode);
        return buildTree(children, parentCode);
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

        if (parentCode == null) {
            return treeList.stream()
                    .filter(d -> d.getParentCode() == null)
                    .collect(Collectors.toList());
        } else {
            return treeList;
        }
    }
}
