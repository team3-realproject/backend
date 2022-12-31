package com.example.alba_prokect.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MsgResponseDto {
    private String msg;

    public MsgResponseDto(String msg) {
        this.msg = msg;
    }
}
