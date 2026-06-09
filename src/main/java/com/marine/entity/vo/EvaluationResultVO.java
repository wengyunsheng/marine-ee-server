package com.marine.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

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
     * 等级描述
     */
    private String levelDescription;

    /**
     * 是否通过
     */
    private boolean passed;
}
