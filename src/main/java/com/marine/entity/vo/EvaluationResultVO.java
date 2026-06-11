package com.marine.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 能效评估结果视图对象
 *
 * @author admin
 * @since 2026-06-02
 */
@Data
public class EvaluationResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 能效指数
     */
    private BigDecimal efficiencyIndex;

    /**
     * 能效等级
     */
    private Integer efficiencyLevel;

    /**
     * 基准值
     */
    private BigDecimal baseValue;

    /**
     * 是否通过
     */
    private boolean passed;
}
