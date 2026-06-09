package com.marine.entity;

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

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工作半径最小值
     */
    @TableField("work_radius_min")
    private BigDecimal workRadiusMin;

    /**
     * 工作半径最大值
     */
    @TableField("work_radius_max")
    private BigDecimal workRadiusMax;

    /**
     * 载荷范围
     */
    @TableField("load_range")
    private Integer loadRange;

    /**
     * 能效等级
     */
    @TableField("efficiency_level")
    private Integer efficiencyLevel;

    /**
     * 阈值最小值
     */
    @TableField("threshold_min")
    private BigDecimal thresholdMin;

    /**
     * 阈值最大值
     */
    @TableField("threshold_max")
    private BigDecimal thresholdMax;

    /**
     * 阈值表达式
     */
    @TableField("threshold_expression")
    private String thresholdExpression;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 是否删除（0-未删除，1-已删除）
     */
    @TableField("is_deleted")
    private Integer isDeleted;
}
