package com.yonsai.Day65_20260902.controller;

import com.yonsai.Day65_20260902.service.AiService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final AiService aiService;

    public HomeController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/ask")
    public String ask(@RequestParam String question, Model model) {
        model.addAttribute("question", question);
        model.addAttribute("answer", aiService.질문하기(question));
        return "index";
    }
}
