package com.java.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.inventory.dto.LoginUserDto;
import com.java.inventory.dto.RegisterUserDto;
import com.java.inventory.dto.SignUpUserDto;
import com.java.inventory.entity.User;
import com.java.inventory.service.AuthenticationService;
import com.java.inventory.service.JwtService;
import com.java.inventory.vo.LoginResponse;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpUserDto> register(@RequestBody RegisterUserDto registerUserDto) {
    	SignUpUserDto registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        
        authenticationService.saveUserJwtToken(authenticatedUser, jwtToken);

        return ResponseEntity.ok(loginResponse);
    }
}