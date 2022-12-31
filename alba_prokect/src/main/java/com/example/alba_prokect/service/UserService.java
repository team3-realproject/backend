package com.example.alba_prokect.service;

import com.example.alba_prokect.dto.LoginRequestDto;
import com.example.alba_prokect.dto.MsgResponseDto;
import com.example.alba_prokect.dto.SignupRequestDto;
import com.example.alba_prokect.entity.User;
import com.example.alba_prokect.jwt.JwtUtil;
import com.example.alba_prokect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;
    @Transactional
    public ResponseEntity<?> signup(SignupRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(requestDto, password);
        userRepository.saveAndFlush(user);

        return new ResponseEntity<>(new MsgResponseDto("회원가입성공"), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> login(LoginRequestDto requestDto, HttpServletResponse response) {
        User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("유저정보없음")
        );
        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserId()));

        return new ResponseEntity<>(new MsgResponseDto("로그인성공"), HttpStatus.OK);



    }
}
