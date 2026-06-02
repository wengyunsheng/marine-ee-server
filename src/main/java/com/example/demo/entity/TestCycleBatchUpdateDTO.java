package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TestCycleBatchUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cycleCode;

    private List<WeightCoefficientItem> items;

    @Data
    public static class WeightCoefficientItem implements Serializable {
        private Integer conditionNo;
        private BigDecimal weightCoefficient;
    }
}
