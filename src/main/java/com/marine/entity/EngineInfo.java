package com.marine.entity;

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

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备id
     */
    @TableField("device_id")
    private Long deviceId;

    /**
     * 品牌
     */
    @TableField("brand")
    private String brand;

    /**
     * 型号
     */
    @TableField("model")
    private String model;

    /**
     * 缸数
     */
    @TableField("cylinder_count")
    private Integer cylinderCount;

    /**
     * 缸径
     */
    @TableField("cylinder_bore")
    private Integer cylinderBore;

    /**
     * 燃料类型
     */
    @TableField("fuel_type")
    private String fuelType;

    /**
     * 燃料类型1
     */
    @TableField("fuel_type1")
    private String fuelType1;

    /**
     * 燃料类型2
     */
    @TableField("fuel_type2")
    private String fuelType2;

    /**
     * 燃料类型3
     */
    @TableField("fuel_type3")
    private String fuelType3;

    /**
     * 燃料类型1热值
     */
    @TableField("fuel_type1_calorific_value")
    private BigDecimal fuelType1CalorificValue;

    /**
     * 燃料类型2热值
     */
    @TableField("fuel_type2_calorific_value")
    private BigDecimal fuelType2CalorificValue;

    /**
     * 燃料类型3热值
     */
    @TableField("fuel_type3_calorific_value")
    private BigDecimal fuelType3CalorificValue;

    /**
     * 发动机用途
     */
    @TableField("engine_usage")
    private String engineUsage;

    /**
     * 排放标准
     */
    @TableField("emission_standard")
    private String emissionStandard;

    /**
     * 额定转速
     */
    @TableField("rated_speed")
    private Integer ratedSpeed;

    /**
     * 额定功率
     */
    @TableField("rated_power")
    private Integer ratedPower;

    /**
     * 创建时间
     */
    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    /**
     * 是否已评估
     */
    @TableField("is_evaluated")
    private Boolean isEvaluated;

    /**
     * 能效指数
     */
    @TableField("efficiency_index")
    private BigDecimal efficiencyIndex;

    /**
     * 能效等级
     */
    @TableField("efficiency_level")
    private Integer efficiencyLevel;

    /**
     * 能效基准值
     */
    @TableField("efficiency_base_value")
    private BigDecimal efficiencyBaseValue;

    /**
     * 评估时间
     */
    @TableField("evaluation_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime evaluationTime;

    /**
     * 测试工况（非数据库字段）
     */
    @TableField(exist = false)
    private EngineTestCondition testCondition;

    /**
     * 性能曲线列表（非数据库字段）
     */
    @TableField(exist = false)
    private List<EnginePerformanceCurve> performanceCurves;
}
