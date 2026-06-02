package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("generator_efficiency")
public class GeneratorEfficiency implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("generator_type")
    private String generatorType;

    @TableField("rated_capacity")
    private BigDecimal ratedCapacity;

    @TableField("rated_power")
    private BigDecimal ratedPower;

    @TableField("rotor_poles")
    private Integer rotorPoles;

    @TableField("rotor_speed")
    private Integer rotorSpeed;

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
