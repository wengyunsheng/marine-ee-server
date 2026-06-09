package com.marine.entity;

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

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 热源温度最小值
     */
    @TableField("heat_source_temp_min")
    private BigDecimal heatSourceTempMin;

    /**
     * 热源温度最大值
     */
    @TableField("heat_source_temp_max")
    private BigDecimal heatSourceTempMax;

    /**
     * 输出功率最小值
     */
    @TableField("power_output_min")
    private BigDecimal powerOutputMin;

    /**
     * 输出功率最大值
     */
    @TableField("power_output_max")
    private BigDecimal powerOutputMax;

    /**
     * 能效等级
     */
    @TableField("efficiency_level")
    private Integer efficiencyLevel;

    /**
     * 基准值
     */
    @TableField("base_value")
    private BigDecimal baseValue;

    /**
     * 单位
     */
    @TableField("unit")
    private String unit;

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
     * 是否删除（0-未删除，1-已删除）
     */
    @TableField("is_deleted")
    private Integer isDeleted;
}
