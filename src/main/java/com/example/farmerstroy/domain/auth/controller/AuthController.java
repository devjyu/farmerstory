package com.example.farmerstroy.domain.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    // 회원가입
    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    // 로그인
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logoutPage(HttpSession session) {
        System.out.println("통과");
        //session.invalidate();
		session.invalidate();   

        return "redirect:/main";
    }
}
