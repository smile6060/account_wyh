package com.example.account.dto;

import lombok.Data;

@Data
public class ArticleDto {
    private int id;
    private String subject;
    private String writer;
    private String content;
    private int grp;
    private int depth;
//    들고와야할 때 코드로 들고옴
    private String code;
}
