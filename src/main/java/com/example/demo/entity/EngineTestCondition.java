package com.example.demo.entity;

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

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("engine_id")
    private Long engineId;

    @TableField("ambient_temp")
    private BigDecimal ambientTemp;

    @TableField("ambient_humidity")
    private Integer ambientHumidity;

    @TableField("ambient_pressure")
    private Integer ambientPressure;

    @TableField("exhaust_temp")
    private Integer exhaustTemp;

    @TableField("coolant_inlet")
    private Integer coolantInlet;

    @TableField("coolant_outlet")
    private Integer coolantOutlet;

    @TableField("lube_oil_temp")
    private Integer lubeOilTemp;

    @TableField("lube_oil_pressure")
    private BigDecimal lubeOilPressure;

    @TableField("test_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate testDate;
}
