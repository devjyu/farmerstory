package com.example.farmerstroy.domain.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.farmerstroy.domain.sale.dto.ResSaleDTO;
import com.example.farmerstroy.domain.sale.service.SaleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    
    // 판매페이지
    @GetMapping("/sale")
    public String salePage(Model model){
        ResSaleDTO resSaleDTOList = saleService.getSaleData();

        model.addAttribute("resSaleDTOList", resSaleDTOList);

        return "sale";
    }

}
