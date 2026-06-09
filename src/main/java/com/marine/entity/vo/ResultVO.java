package com.marine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> ResultVO<T> success() {
        return new ResultVO<>(200, "操作成功", null);
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(200, "操作成功", data);
    }

    public static <T> ResultVO<T> error(String message) {
        return new ResultVO<>(500, message, null);
    }

}
