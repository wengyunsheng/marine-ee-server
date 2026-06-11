package com.marine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备选项视图对象
 *
 * @author admin
 * @since 2026-06-02
 */
@AllArgsConstructor
@Data
public class DeviceOptionVO implements Serializable {

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
     * 设备名称
     */
    private String name;

}
