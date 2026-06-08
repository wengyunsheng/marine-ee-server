package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("engine_performance_curve")
public class EnginePerformanceCurve implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("engine_id")
    private Long engineId;

    @TableField("condition_id")
    private Long conditionId;

    @TableField("load_factor")
    private BigDecimal loadFactor;

    @TableField("power")
    private BigDecimal power;

    @TableField("speed")
    private BigDecimal speed;

    @TableField("bsfc")
    private BigDecimal bsfc;

    @TableField("bspc")
    private BigDecimal bspc;

    @TableField("bsgc")
    private BigDecimal bsgc;

    @TableField("bsec")
    private BigDecimal bsec;
}
