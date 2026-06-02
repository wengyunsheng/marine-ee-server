package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("chiller_efficiency")
public class ChillerEfficiency implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("evaluation_type")
    private String evaluationType;

    @TableField("product_standard")
    private String productStandard;

    @TableField("unit_type")
    private String unitType;

    @TableField("cooling_capacity_min")
    private BigDecimal coolingCapacityMin;

    @TableField("cooling_capacity_max")
    private BigDecimal coolingCapacityMax;

    @TableField("efficiency_level")
    private Integer efficiencyLevel;

    @TableField("efficiency_value")
    private BigDecimal efficiencyValue;

    @TableField("sort")
    private Integer sort;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @TableField("create_by")
    private String createBy;

    @TableField("update_by")
    private String updateBy;

    @TableField("is_deleted")
    private Integer isDeleted;
}
