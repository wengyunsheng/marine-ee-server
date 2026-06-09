package com.marine.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class EngineImportDTO implements Serializable {

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
     * 缸数
     */
    private Integer cylinderCount;

    /**
     * 缸径
     */
    private Integer cylinderBore;

    /**
     * 燃料类型
     */
    private String fuelType;

    /**
     * 燃料类型1
     */
    private String fuelType1;

    /**
     * 燃料类型2
     */
    private String fuelType2;

    /**
     * 燃料类型3
     */
    private String fuelType3;

    /**
     * 燃料类型1热值
     */
    private BigDecimal fuelType1CalorificValue;

    /**
     * 燃料类型2热值
     */
    private BigDecimal fuelType2CalorificValue;

    /**
     * 燃料类型3热值
     */
    private BigDecimal fuelType3CalorificValue;

    /**
     * 发动机用途
     */
    private String engineUsage;

    /**
     * 排放等级
     */
    private String emissionStandard;

    /**
     * 额定转速
     */
    private Integer ratedSpeed;

    /**
     * 额定功率
     */
    private Integer ratedPower;

    /**
     * 环境温度
     */
    private BigDecimal ambientTemp;

    /**
     * 环境湿度
     */
    private Integer ambientHumidity;

    /**
     * 环境压力
     */
    private Integer ambientPressure;

    /**
     * 排气温度
     */
    private Integer exhaustTemp;

    /**
     * 气缸冷却水进口温度
     */
    private Integer coolantInlet;

    /**
     * 气缸冷却水出口温度
     */
    private Integer coolantOutlet;

    /**
     * 发动机前润滑油温度
     */
    private Integer lubeOilTemp;

    /**
     * 发动机前润滑油压力
     */
    private BigDecimal lubeOilPressure;

    /**
     * 性能曲线列表
     */
    private List<PerformanceCurveDTO> performanceCurves;

    @Data
    public static class PerformanceCurveDTO implements Serializable{

        private static final long serialVersionUID = 1L;

        /**
         * 负荷因子
         */
        private BigDecimal loadFactor;

        /**
         * 功率
         */
        private BigDecimal power;

        /**
         * 转速
         */
        private BigDecimal speed;

        /**
         * 燃油消耗率
         */
        private BigDecimal bsfc;

        /**
         * 燃油消耗率
         */
        private BigDecimal bspc;

        /**
         * 燃气消耗率
         */
        private BigDecimal bsgc;

        /**
         * 能量消耗率
         */
        private BigDecimal bsec;
    }
}