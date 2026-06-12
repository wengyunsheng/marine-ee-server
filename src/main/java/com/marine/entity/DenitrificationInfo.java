package com.marine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("denitrification_info")
public class DenitrificationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("device_id")
    private Long deviceId;

    @TableField("brand")
    private String brand;

    @TableField("model")
    private String model;

    @TableField("smoke_flow_rate")
    private BigDecimal smokeFlowRate;

    @TableField("denitrification_efficiency")
    private BigDecimal denitrificationEfficiency;

    @TableField("system_power")
    private Integer systemPower;

    @TableField("nox_removal_amount")
    private BigDecimal noxRemovalAmount;

    @TableField("energy_consumption_ratio")
    private BigDecimal energyConsumptionRatio;

    @TableField("imo_compliance")
    private String imoCompliance;

    @TableField("efficiency_level")
    private Integer efficiencyLevel;

    @TableField("is_evaluated")
    private Boolean isEvaluated;

    @TableField("efficiency_index")
    private BigDecimal efficiencyIndex;

    @TableField("efficiency_base_value")
    private BigDecimal efficiencyBaseValue;

    @TableField("evaluation_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime evaluationTime;

    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdTime;

    @TableField("is_deleted")
    private Integer isDeleted;
}
