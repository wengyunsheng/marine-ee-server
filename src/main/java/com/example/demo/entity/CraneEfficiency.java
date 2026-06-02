package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("crane_efficiency")
public class CraneEfficiency implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("work_radius_min")
    private BigDecimal workRadiusMin;

    @TableField("work_radius_max")
    private BigDecimal workRadiusMax;

    @TableField("load_range")
    private Integer loadRange;

    @TableField("efficiency_level")
    private Integer efficiencyLevel;

    @TableField("threshold_min")
    private BigDecimal thresholdMin;

    @TableField("threshold_max")
    private BigDecimal thresholdMax;

    @TableField("threshold_expression")
    private String thresholdExpression;

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
