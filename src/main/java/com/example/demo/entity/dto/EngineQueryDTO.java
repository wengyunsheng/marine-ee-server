package com.example.demo.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EngineQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String brand;

    private String model;

    private String emissionStandard;
}
