package com.programmers.programmers.apis.dto;

public class ApiResult<T>{
    private T data;
    private String error;
    private ApiResult(T data, String error) {
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResult<T> succeed(T data) {
        return new ApiResult<>(data,null);
    }
    public static  ApiResult<?> failed(String error) {
        return new ApiResult<>(null, error);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
