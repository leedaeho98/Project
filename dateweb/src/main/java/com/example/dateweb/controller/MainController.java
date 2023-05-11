package com.example.dateweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 메인 홈페이지 컨트룰러
@Controller
public class MainController {
    @GetMapping(value = "/")
    public String mainForm(){
        return "main";
    }
}
