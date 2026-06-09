package com.marine.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("engine_performance_curve")
public class EnginePerformanceCurve implements Serializable {

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
     * 工况ID
     */
    @TableField("condition_id")
    private Long conditionId;

    /**
     * 负荷因子
     */
    @TableField("load_factor")
    private BigDecimal loadFactor;

    /**
     * 功率
     */
    @TableField("power")
    private BigDecimal power;

    /**
     * 转速
     */
    @TableField("speed")
    private BigDecimal speed;

    /**
     * 燃油消耗率
     */
    @TableField("bsfc")
    private BigDecimal bsfc;

    /**
     * 比油耗
     */
    @TableField("bspc")
    private BigDecimal bspc;

    /**
     * 燃气消耗率
     */
    @TableField("bsgc")
    private BigDecimal bsgc;

    /**
     * 能量消耗率
     */
    @TableField("bsec")
    private BigDecimal bsec;
}
