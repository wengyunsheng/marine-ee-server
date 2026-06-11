package com.marine.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一响应结果视图对象
 *
 * @author admin
 * @since 2026-06-02
 */
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

    /**
     * 创建成功响应（无数据）
     *
     * @param <T> 数据类型
     * @return 成功的响应结果
     */
    public static <T> ResultVO<T> success() {
        return new ResultVO<>(200, "操作成功", null);
    }

    /**
     * 创建成功响应（带数据）
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return 成功的响应结果
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(200, "操作成功", data);
    }

    /**
     * 创建错误响应
     *
     * @param message 错误消息
     * @param <T>     数据类型
     * @return 错误的响应结果
     */
    public static <T> ResultVO<T> error(String message) {
        return new ResultVO<>(500, message, null);
    }

}
