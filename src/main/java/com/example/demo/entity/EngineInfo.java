package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("engine_info")
public class EngineInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("brand")
    private String brand;

    @TableField("model")
    private String model;

    @TableField("cylinder_count")
    private Integer cylinderCount;

    @TableField("cylinder_bore")
    private Integer cylinderBore;

    @TableField("fuel_type")
    private String fuelType;

    @TableField("fuel_type1")
    private String fuelType1;

    @TableField("fuel_type2")
    private String fuelType2;

    @TableField("fuel_type3")
    private String fuelType3;

    @TableField("fuel_type1_calorific_value")
    private BigDecimal fuelType1CalorificValue;

    @TableField("fuel_type2_calorific_value")
    private BigDecimal fuelType2CalorificValue;

    @TableField("fuel_type3_calorific_value")
    private BigDecimal fuelType3CalorificValue;

    @TableField("engine_usage")
    private String engineUsage;

    @TableField("emission_standard")
    private String emissionStandard;

    @TableField("rated_speed")
    private Integer ratedSpeed;

    @TableField("rated_power")
    private Integer ratedPower;

    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    @TableField(exist = false)
    private EngineTestCondition testCondition;

    @TableField(exist = false)
    private List<EnginePerformanceCurve> performanceCurves;
}
