package com.marine.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备查询数据传输对象
 *
 * @author admin
 * @since 2026-06-02
 */
@Data
public class DeviceQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 父级设备编码
     */
    private String parentCode;

}
