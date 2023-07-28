package com.example.account.controller;

import com.example.account.dto.ArticleDto;
import com.example.account.dto.BoardDto;
import com.example.account.mappers.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;

@Controller
public class ArticleController {

    @Autowired
    private ConfigMapper configMapper;

    @GetMapping("/article/list")
    public String getList(@RequestParam String code, Model model) {
//        config 이거 제목 title 가져오는 거임
        model.addAttribute("config", configMapper.getConfigOne(code));
//        art 값 들고오는 거
        model.addAttribute("art", configMapper.getList(code));

        return "article/list";
    }

    @GetMapping("/article/write")
    public String getWrite(@RequestParam String code, Model model) {
        model.addAttribute("config", configMapper.getConfigOne(code));

        return "article/write";
    }

//    ajax 없이 값 들고오기
    @PostMapping("/article/write")
    public String setWrite(@ModelAttribute ArticleDto articleDto) {
        int cnt = configMapper.getMaxCnt(articleDto.getCode());

//        0에서 1로 바뀜
        articleDto.setGrp(cnt);

        configMapper.setWrite(articleDto); //grp, depth
        return "redirect:/article/list?code=" + articleDto.getCode();
    }

    @GetMapping("/article/delete")
    public String delArticle(@ModelAttribute ArticleDto articleDto) {
        configMapper.delArticle(articleDto);
        return "redirect:/article/list?code=" + articleDto.getCode();
    }

    @GetMapping("/article/edit")
    public String getEdit(@ModelAttribute ArticleDto articleDto, Model model) {
//        제목 들고오는 거
        model.addAttribute("config", configMapper.getConfigOne(articleDto.getCode()));
//        값 들고오는 거
        model.addAttribute("art", configMapper.getEdit(articleDto));

        return "article/edit";
    }

    @PostMapping("/article/edit")
    public String setEdit(@ModelAttribute ArticleDto articleDto) {
        configMapper.setEdit(articleDto);

        return "redirect:/article/list?code=" + articleDto.getCode();
    }
}
