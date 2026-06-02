package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("device")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("code")
    private String code;

    @TableField("parent_code")
    private String parentCode;

    @TableField("name")
    private String name;

    @TableField("sort")
    private Integer sort;

    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("delete_time")
    private Date deleteTime;

    public Device() {
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}
