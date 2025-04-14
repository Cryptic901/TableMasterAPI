package com.tablemaster_api.controller;

import com.tablemaster_api.dto.LoginUserDto;
import com.tablemaster_api.dto.RegisterUserDto;
import com.tablemaster_api.dto.VerifyUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/register")
    public ResponseEntity<?> register(RegisterUserDto registerUserDto) {
        //TODO
        return null;
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verification(VerifyUserDto verifyUserDto) {
        //TODO
        return null;
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resend(String email) {
        //TODO
        return null;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(LoginUserDto loginUserDto) {
        //TODO
        return null;
    }
}
