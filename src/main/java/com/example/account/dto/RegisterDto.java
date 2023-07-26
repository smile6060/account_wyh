package com.example.account.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private int id;
    private String userid;
    private String username;
    private String password;
    private int level;

}
