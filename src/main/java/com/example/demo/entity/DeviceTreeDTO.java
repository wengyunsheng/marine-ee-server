package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DeviceTreeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String code;

    private String parentCode;

    private String name;

    private Integer sort;

    private Integer isDeleted;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private List<DeviceTreeDTO> children;

    public DeviceTreeDTO() {
    }

    @Override
    public String toString() {
        return "DeviceTreeDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                ", children=" + children +
                '}';
    }
}
