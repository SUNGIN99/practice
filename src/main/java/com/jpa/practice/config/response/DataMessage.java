package com.jpa.practice.config.response;

import lombok.Data;

import static com.jpa.practice.config.response.ResponseCode.SUCCESS;

@Data
public class DataMessage<T> {

    private ResponseCode status;
    private T data;

    public static <T> DataMessage<T> success(T data){
        return new DataMessage<>(SUCCESS, data);
    }

    private DataMessage(ResponseCode status, T data){
        this.status = status;
        this.data = data;
    }


}
