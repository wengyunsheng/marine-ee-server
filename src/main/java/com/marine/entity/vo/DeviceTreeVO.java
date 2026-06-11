package com.marine.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 设备树视图对象
 *
 * @author admin
 * @since 2026-06-02
 */
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
     * 模型文件静态路径
     */
    private String modelFileUrl;

    /**
     * 子设备列表
     */
    private List<DeviceTreeVO> children;

}
