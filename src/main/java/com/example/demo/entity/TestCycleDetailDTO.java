package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TestCycleDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cycleCode;

    private String cycleName;

    private String deviceType;

    private List<ConditionDTO> conditions;

    @Data
    public static class ConditionDTO implements Serializable {
        private Integer conditionNo;
        private String engineSpeed;
        private String powerMode;
        private BigDecimal weightCoefficient;
    }
}
