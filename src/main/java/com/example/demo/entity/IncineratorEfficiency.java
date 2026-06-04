package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("incinerator_efficiency")
public class IncineratorEfficiency implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("incinerator_type")
    private String incineratorType;

    @TableField("heat_capacity_min")
    private BigDecimal heatCapacityMin;

    @TableField("heat_capacity_max")
    private BigDecimal heatCapacityMax;

    @TableField("efficiency_level")
    private Integer efficiencyLevel;

    @TableField("base_value")
    private BigDecimal baseValue;

    @TableField("unit")
    private String unit;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    private Integer isDeleted;
}
