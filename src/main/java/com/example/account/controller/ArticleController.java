package com.example.account.controller;

import com.example.account.dto.ArticleDto;
import com.example.account.mappers.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {

    @Autowired
    private ConfigMapper configMapper;

    @GetMapping("/article/list")
    public String getList(@RequestParam String code, Model model) {
        model.addAttribute("config", configMapper.getConfigOne(code));
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
}
