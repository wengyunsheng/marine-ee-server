package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("waste_heat_organic_rankine_efficiency")
public class WasteHeatOrganicRankineEfficiency implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("heat_source_temp_min")
    private BigDecimal heatSourceTempMin;

    @TableField("heat_source_temp_max")
    private BigDecimal heatSourceTempMax;

    @TableField("power_output_min")
    private BigDecimal powerOutputMin;

    @TableField("power_output_max")
    private BigDecimal powerOutputMax;

    @TableField("efficiency_level")
    private Integer efficiencyLevel;

    @TableField("base_value_expression")
    private String baseValueExpression;

    @TableField("base_value_percent")
    private BigDecimal baseValuePercent;

    @TableField("carnot_coefficient")
    private String carnotCoefficient;

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
