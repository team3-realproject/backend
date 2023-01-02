package com.example.alba_prokect.dto;

import com.example.alba_prokect.errorcode.CommonStatusCode;
import com.example.alba_prokect.errorcode.StatusCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MsgResponseDto {
    private String msg;

    public MsgResponseDto(String msg) {
        this.msg = msg;
    }

    public MsgResponseDto(StatusCode statusCode){
        this.msg = statusCode.getMsg();
    }


}
