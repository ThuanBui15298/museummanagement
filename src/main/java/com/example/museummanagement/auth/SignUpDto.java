package com.example.museummanagement.auth;

import lombok.Data;

@Data
public class SignUpDto {
    private String username;
    private String password;
    private String name;
}
