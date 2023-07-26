package com.example.account.controller;

import com.example.account.dto.RegisterDto;
import com.example.account.mappers.RegisterMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private RegisterMapper registerMapper;

    @GetMapping("/register")
    public String getRegister() {

        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> saveRegister(@ModelAttribute RegisterDto registerDto) {
        registerMapper.getRegister(registerDto);

        Map<String, Object> map = new HashMap<>();

        map.put("msg", "success");
        return map;
    }

    @GetMapping("/login")
    public String getLogin() {

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> setLogin(@ModelAttribute RegisterDto registerDto, HttpServletRequest req) {
        RegisterDto r =  registerMapper.checkLogin(registerDto);

        if(r != null) {
            HttpSession hs = req.getSession();

            hs.setAttribute("userid", r.getUserid());
            hs.setAttribute("username", r.getUsername());
            hs.setAttribute("level", r.getLevel());

            hs.setMaxInactiveInterval(60 * 30);
        }

        return Map.of("msg", "success");
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession hs) {
        hs.invalidate();

        return "redirect:/main";
    }

    @GetMapping("/list")
    public String getlist(Model model) {
        model.addAttribute("mem", registerMapper.getMemberAll());


        return "list";
    }

    @GetMapping("/main")
    public String getMain() {

        return "main";
    }

    @GetMapping("/update")
    public String getUpdate(Model model, @RequestParam int id) {
        model.addAttribute("mem", registerMapper.getMemberId(id));

        return "update";
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> setUpdate(@ModelAttribute RegisterDto registerDto) {

        registerMapper.setUpdate(registerDto);

        return Map.of("msg", "success");

    }
}
