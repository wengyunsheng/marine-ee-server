package com.marine.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@TableName("engine_test_condition")
public class EngineTestCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发动机ID
     */
    @TableField("engine_id")
    private Long engineId;

    /**
     * 环境温度
     */
    @TableField("ambient_temp")
    private BigDecimal ambientTemp;

    /**
     * 环境湿度
     */
    @TableField("ambient_humidity")
    private Integer ambientHumidity;

    /**
     * 环境压力
     */
    @TableField("ambient_pressure")
    private Integer ambientPressure;

    /**
     * 排气温度
     */
    @TableField("exhaust_temp")
    private Integer exhaustTemp;

    /**
     * 冷却水进口温度
     */
    @TableField("coolant_inlet")
    private Integer coolantInlet;

    /**
     * 冷却水出口温度
     */
    @TableField("coolant_outlet")
    private Integer coolantOutlet;

    /**
     * 润滑油温度
     */
    @TableField("lube_oil_temp")
    private Integer lubeOilTemp;

    /**
     * 润滑油压力
     */
    @TableField("lube_oil_pressure")
    private BigDecimal lubeOilPressure;

    /**
     * 测试日期
     */
    @TableField("test_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate testDate;
}
