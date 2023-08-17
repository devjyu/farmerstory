package com.example.farmerstroy.domain.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SaleDetailController {
    
    @GetMapping("/saledetail")
    public String saleDetailPage(){
        return "saledetail";
    }
}
