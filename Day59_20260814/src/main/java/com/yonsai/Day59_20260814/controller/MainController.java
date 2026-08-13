package com.yonsai.Day59_20260814.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/about")
    public String about() {
        return "forward:/about.html";
    }
}
