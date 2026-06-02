package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceOptionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String code;

    private String name;

    public DeviceOptionDTO() {
    }

    public DeviceOptionDTO(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
