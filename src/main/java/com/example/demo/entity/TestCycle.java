package com.example.demo.entity;

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

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("cycle_code")
    private String cycleCode;

    @TableField("cycle_name")
    private String cycleName;

    @TableField("condition_no")
    private Integer conditionNo;

    @TableField("engine_speed")
    private String engineSpeed;

    @TableField("power_mode")
    private String powerMode;

    @TableField("weight_coefficient")
    private BigDecimal weightCoefficient;

    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @TableField("delete_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deleteTime;
}
