package com.example.farmerstroy.domain.sale.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.farmerstroy.domain.sale.dto.ResSaleDTO;
import com.example.farmerstroy.domain.sale.dto.ResSaleDetailDTO;
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

    // 판매 상세페이지
    @GetMapping("/sale/{saleIdx}")
        public String saleDetail(@PathVariable Long saleIdx, Model model){
        ResSaleDetailDTO resSaleDetailList = saleService.getSaleDetailData(saleIdx);
        model.addAttribute("resSaleDetailList", resSaleDetailList);
        return "saledetail";
    }

}
