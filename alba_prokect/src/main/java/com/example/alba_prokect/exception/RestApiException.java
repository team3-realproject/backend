package com.example.alba_prokect.exception;

import com.example.alba_prokect.errorcode.StatusCode;
import lombok.Getter;

@Getter
public class RestApiException extends RuntimeException{

    // 필드값
    private final StatusCode statusCode;

    //getter
    public StatusCode getStatusCode(){
        return this.statusCode;
    }
    // 생성자
    public RestApiException(StatusCode statusCode){
        this.statusCode = statusCode;
    }
}
