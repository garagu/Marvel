package com.garagu.marvel.data.entity.common;

/**
 * Created by garagu.
 */
public class ResultEntity<T> {

    private int code;
    private String status;
    private T data;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

}