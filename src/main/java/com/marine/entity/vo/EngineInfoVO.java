package com.marine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 发动机信息视图对象
 *
 * @author admin
 * @since 2026-06-02
 */
@AllArgsConstructor
@Data
public class EngineInfoVO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 显示名称
     */
    private String displayName;
}
