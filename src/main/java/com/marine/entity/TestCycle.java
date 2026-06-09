package com.marine.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("test_cycle")
public class TestCycle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 测试循环编码
     */
    @TableField("cycle_code")
    private String cycleCode;

    /**
     * 测试循环名称
     */
    @TableField("cycle_name")
    private String cycleName;

    /**
     * 工况编号
     */
    @TableField("condition_no")
    private Integer conditionNo;

    /**
     * 发动机转速
     */
    @TableField("engine_speed")
    private String engineSpeed;

    /**
     * 功率模式
     */
    @TableField("power_mode")
    private String powerMode;

    /**
     * 权重系数
     */
    @TableField("weight_coefficient")
    private BigDecimal weightCoefficient;

    /**
     * 是否删除（0-未删除，1-已删除）
     */
    @TableField("is_deleted")
    private Integer isDeleted;

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
     * 删除时间
     */
    @TableField("delete_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deleteTime;
}
