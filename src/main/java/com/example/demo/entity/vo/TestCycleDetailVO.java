package com.example.demo.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TestCycleDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cycleCode;

    private String cycleName;

    private List<ConditionDTO> conditions;

    @Data
    public static class ConditionDTO implements Serializable {

        private static final long serialVersionUID = 1L;
        private Integer conditionNo;

        private String engineSpeed;

        private String powerMode;

        private BigDecimal weightCoefficient;
    }
}
