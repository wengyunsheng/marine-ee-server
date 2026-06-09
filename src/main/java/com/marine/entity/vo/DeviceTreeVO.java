package com.marine.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeviceTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备ID
     */
    private Long id;

    /**
     * 设备编码
     */
    private String code;

    /**
     * 父级设备编码
     */
    private String parentCode;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 模型文件ID
     */
    private Long modelFileId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否删除（0-未删除，1-已删除）
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deleteTime;

    /**
     * 子设备列表
     */
    private List<DeviceTreeVO> children;

}
