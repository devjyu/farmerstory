package com.example.farmerstroy.domain.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage(HttpSession session) {
        // if (session.getAttribute("dto") == null) {
        //     return "redirect:/main";
        // }
        System.out.println(session.getAttribute("dto"));
        return "main";
    }
}
