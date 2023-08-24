package com.example.account.controller;

import com.example.account.dto.ArticleDto;
import com.example.account.dto.BoardDto;
import com.example.account.mappers.ConfigMapper;
import com.example.account.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@Controller
public class ArticleController {

    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/list")
    public String getList(Model model,
                          @RequestParam String code,
                          // value list.html에 선언된 name이름을 들고옴 defaultValue = 초기값을 빈값으로 설정해라는 뜻, 혹시나 잘못 입력 되었을 때를 예방하는 값
                          @RequestParam(value = "searchType", defaultValue = "") String searchType,
                          // list.html input name값을 들고 옴 words
                          @RequestParam(value = "words", defaultValue = "") String words) {

//        System.out.println("searchType: " + searchType);
//        System.out.println("words: " + words);
//        config 이거 제목 title 가져오는 거임
        model.addAttribute("config", configMapper.getConfigOne(code));
//        art 값 들고오는 거 검색 기능 없을 때 사용하는 코드
//        model.addAttribute("art", configMapper.getList(code));
        model.addAttribute("art", articleService.getList(code, searchType, words));

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

        configMapper. setWrite(articleDto); //grp, depth
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

    @GetMapping("/article/view")
    public String getView(@ModelAttribute ArticleDto articleDto, Model model) {
//        제목 들고오는 거
        model.addAttribute("config", configMapper.getConfigOne(articleDto.getCode()));
//
        model.addAttribute("art", configMapper.getEdit(articleDto));

        return "article/view";
    }

    @GetMapping("/article/reply")
    public String getReply(@ModelAttribute ArticleDto articleDto, Model model) {
//        제목 들고오는 거
        model.addAttribute("config", configMapper.getConfigOne(articleDto.getCode()));
        model.addAttribute("art", configMapper.getEdit(articleDto));

        return "article/reply";
    }

    @PostMapping("/article/reply")
    public String setReply(@ModelAttribute ArticleDto articleDto) {
//        기존 값(depth)을 들고와서 + 1 해줌 그리고 그 값을 다시 셋팅 (setDepth)
        articleDto.setDepth(articleDto.getDepth() + 1);

        configMapper.setReply(articleDto);


        return "redirect:/article/list?code=" + articleDto.getCode();
    }
}
