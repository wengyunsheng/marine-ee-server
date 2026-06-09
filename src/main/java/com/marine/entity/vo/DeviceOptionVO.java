package com.marine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

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
