package com.example.alba_prokect.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    private String userId;
    private String password;
    private String nickname;

}
