package com.example.museummanagement.controller;

import com.example.museummanagement.auth.CustomUserDetails;
import com.example.museummanagement.auth.LoginRequest;
import com.example.museummanagement.auth.LoginResponse;
import com.example.museummanagement.auth.SignUpDto;
import com.example.museummanagement.config.JwtTokenProvider;
import com.example.museummanagement.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        try {
            userServiceImpl.registerUser(signUpDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}