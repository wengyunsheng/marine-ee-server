package com.example.demo.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class EvaluationResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal efficiencyIndex;

    private Integer efficiencyLevel;

    private BigDecimal baseValue;

    private String levelDescription;

    private boolean passed;
}
