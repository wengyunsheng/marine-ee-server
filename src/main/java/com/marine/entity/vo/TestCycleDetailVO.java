package com.marine.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 测试循环详情视图对象
 *
 * @author admin
 * @since 2026-06-02
 */
@Data
public class TestCycleDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 测试循环编码
     */
    private String cycleCode;

    /**
     * 测试循环名称
     */
    private String cycleName;

    /**
     * 工况列表
     */
    private List<ConditionDTO> conditions;

    /**
     * 工况内部类
     */
    @Data
    public static class ConditionDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 工况编号
         */
        private Integer conditionNo;

        /**
         * 发动机转速
         */
        private String engineSpeed;

        /**
         * 功率模式
         */
        private String powerMode;

        /**
         * 权重系数
         */
        private BigDecimal weightCoefficient;
    }
}
