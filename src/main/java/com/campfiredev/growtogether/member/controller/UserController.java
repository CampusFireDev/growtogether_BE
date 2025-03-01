package com.campfiredev.growtogether.member.controller;

import com.campfiredev.growtogether.member.dto.UserLoginDto;
import com.campfiredev.growtogether.member.dto.UserRegisterDto;
import com.campfiredev.growtogether.member.entity.UserEntity;
import com.campfiredev.growtogether.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody UserRegisterDto userRegisterDto,
            @RequestParam(required = false) MultipartFile profileImage) {
        UserEntity user = userService.register(userRegisterDto, profileImage);
        return ResponseEntity.ok(Map.of("message", "회원가입이 완료되었습니다.", "user", user));
    }

    @PostMapping("/userLogin")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginDto userLoginDto) {
        String accessToken = userService.userLogin(userLoginDto);
        return ResponseEntity.ok(Map.of("message", "로그인이 완료되었습니다.", "accessToken", accessToken));
    }
}


