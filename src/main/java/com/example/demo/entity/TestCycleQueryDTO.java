package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestCycleQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cycleCode;

    private String deviceType;

    private String cycleName;
}
