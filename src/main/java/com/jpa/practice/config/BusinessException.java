package com.jpa.practice.config;

import com.jpa.practice.config.response.ResponseCode;

public class BusinessException extends RuntimeException{

    private ResponseCode responseCode;

    public BusinessException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseCode getErrorCode(){
        return responseCode;
    }
}
