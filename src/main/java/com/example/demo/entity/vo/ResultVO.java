package com.example.demo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private T data;

    private Integer total;

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVO<T> success(T data, Integer total) {
        return new ResultVO<>(200, "操作成功", data, total);
    }
    public static <T> ResultVO<T> success(T data ) {
        return new ResultVO<>(200, "操作成功", data );
    }

    public static <T> ResultVO<T> success(String message, T data) {
        return new ResultVO<>(200, message, data);
    }

    public static <T> ResultVO<T> successMessage(String message) {
        return new ResultVO<>(200, message, null);
    }

    public static <T> ResultVO<T> error(String message) {
        return new ResultVO<>(500, message, null);
    }

}
