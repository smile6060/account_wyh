package com.example.account.dto;

import lombok.Data;

@Data
public class BoardDto {
    private int id;
    private String subject;
    private String writer;
    private int grp;
    private int depth;
}
