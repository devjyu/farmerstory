package com.example.farmerstroy.domain.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.farmerstroy.domain.sale.dto.ResCategoryDTO;
import com.example.farmerstroy.domain.sale.dto.ResSaleDTO;
import com.example.farmerstroy.domain.sale.service.SaleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    
    @GetMapping("/sale")
    public String salePage(Model model){
        ResSaleDTO resSaleDTOList = saleService.getSaleData();

        model.addAttribute("resSaleDTOList", resSaleDTOList);

        return "sale";
    }

    @GetMapping("/sale")
    public String salePageByCategoryName(Model model) {
        ResCategoryDTO categoryList = saleService.getCategoryName();
        model.addAttribute("categoryList", categoryList);
        return "sale";
    }

}
