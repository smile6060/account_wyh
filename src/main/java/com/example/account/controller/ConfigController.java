package com.example.account.controller;

import com.example.account.dto.ConfigDto;
import com.example.account.mappers.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfigController {

    @Autowired
    private ConfigMapper configMapper;

    @GetMapping("/config")
    public String getConfig(Model model) {
        model.addAttribute("config", configMapper.getConfig());

        return "config";
    }
    @GetMapping("/create")
    public String getCreate() {
        return "create";
    }

    @PostMapping("/create")
    public String setCreate(@ModelAttribute ConfigDto configDto) {
        configMapper.setConfig(configDto);

        //테이블 이름을 변수로 만들어주는 mapper코드를 들고오는거임
        configMapper.createTable(configDto.getCode());

        return "redirect:/config";
    }

    @GetMapping("/drop")
    public String setDrop(@RequestParam String code) {
        configMapper.deleteConfig(code);
        configMapper.dropTable(code);

        return "redirect:/config";
    }

}
