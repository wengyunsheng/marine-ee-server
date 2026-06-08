package com.example.demo.entity.vo;

import com.example.demo.entity.EngineInfo;
import com.example.demo.entity.EnginePerformanceCurve;
import com.example.demo.entity.EngineTestCondition;
import lombok.Data;

import java.util.List;

@Data
public class EngineDetailVO {

    private EngineInfo engineInfo;
    private EngineTestCondition testCondition;
    private List<EnginePerformanceCurve> performanceCurves;
}
