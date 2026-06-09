package com.marine.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EngineQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号
     */
    private String model;

    /**
     * 排放标准
     */
    private String emissionStandard;
}
