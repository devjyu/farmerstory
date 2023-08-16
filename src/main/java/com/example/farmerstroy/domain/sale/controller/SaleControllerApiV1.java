package com.example.farmerstroy.domain.sale.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmerstroy.domain.sale.service.SaleServiceApiV1;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sale")
public class SaleControllerApiV1 {
    
    private final SaleServiceApiV1 saleServiceApiV1;

    @GetMapping("/category/{categoryIdx}")
    public ResponseEntity<?> getSaleListByCategoryIdx(@PathVariable Long categoryIdx){
        return saleServiceApiV1.getSaleListByCategoryIdx(categoryIdx);
    }
}
