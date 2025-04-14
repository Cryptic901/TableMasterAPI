package com.tablemaster_api.controller;

import com.tablemaster_api.dto.LoginResponseDto;
import com.tablemaster_api.dto.LoginUserDto;
import com.tablemaster_api.dto.RegisterUserDto;
import com.tablemaster_api.dto.VerifyUserDto;
import com.tablemaster_api.entity.User;
import com.tablemaster_api.service.AuthenticationService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterUserDto registerUserDto) throws MessagingException {
        return ResponseEntity.ok(authenticationService.registerUser(registerUserDto));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verification(@RequestBody @Valid VerifyUserDto verifyUserDto) throws AuthenticationException {
        authenticationService.verifyUser(verifyUserDto);
        return ResponseEntity.ok("Account verified successfully!");
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resend(@RequestBody @Valid String email) throws MessagingException, AuthenticationException {
        authenticationService.resendVerificationEmail(email);
        return ResponseEntity.ok("Message has been resented");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(LoginUserDto loginUserDto) {
        LoginResponseDto response = authenticationService.loginUser(loginUserDto);
        return ResponseEntity.ok(response);
    }
}
