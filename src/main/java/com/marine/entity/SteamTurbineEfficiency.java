package com.marine.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("steam_turbine_efficiency")
public class SteamTurbineEfficiency implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 蒸汽压力最小值
     */
    @TableField("steam_pressure_min")
    private BigDecimal steamPressureMin;

    /**
     * 蒸汽压力最大值
     */
    @TableField("steam_pressure_max")
    private BigDecimal steamPressureMax;

    /**
     * 蒸汽类型
     */
    @TableField("steam_type")
    private String steamType;

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
