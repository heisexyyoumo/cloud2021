package com.humorous.springcloud.common;

import lombok.Data;

@Data
public class CommonResult<T> {

    private Boolean success = Boolean.TRUE;
    private String code;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public CommonResult(Boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResult success() {
        return new CommonResult(true, "200", "success");
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(true, "200", "success", data);
    }

    public static CommonResult success(String message) {
        return new CommonResult(true, "200", message);
    }

    public static CommonResult fail(String message) {
        return new CommonResult(true, "500", message);
    }

}
