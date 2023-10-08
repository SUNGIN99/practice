package com.jpa.practice.config.response;

import com.jpa.practice.config.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.NoSuchAlgorithmException;

import static com.jpa.practice.config.response.ResponseCode.SHA_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ResponseCode responseCode = e.getErrorCode();
        ErrorResponse response = new ErrorResponse(responseCode.getStatus(), responseCode.getCode(), responseCode.getDescription());

        return new ResponseEntity<>(response, HttpStatus.valueOf(responseCode.getStatus()));
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    protected ResponseEntity<ErrorResponse> handleEncryptException(NoSuchAlgorithmException e){
        ResponseCode responseCode = SHA_ERROR;
        ErrorResponse response = new ErrorResponse(responseCode.getStatus(), responseCode.getCode(), responseCode.getDescription());

        return new ResponseEntity<>(response, HttpStatus.valueOf(responseCode.getStatus()));
    }

}
