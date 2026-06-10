package com.marine.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeviceTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    private Long id;

    /**
     * 设备编码
     */
    private String code;

    /**
     * 父级设备编码
     */
    private String parentCode;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 模型文件存储路径
     */
    private String modelFilePath;

    /**
     * 子设备列表
     */
    private List<DeviceTreeVO> children;

}
