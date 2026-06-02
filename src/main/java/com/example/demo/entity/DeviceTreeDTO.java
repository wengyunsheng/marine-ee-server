package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public List<DeviceTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<DeviceTreeDTO> children) {
        this.children = children;
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
