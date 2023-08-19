package com.example.farmerstroy.domain.sale.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farmerstroy.common.dto.LoginUserDTO;
import com.example.farmerstroy.domain.sale.dto.ReqSaleInsertDTO;
import com.example.farmerstroy.domain.sale.dto.ReqSaleUpdateDTO;
import com.example.farmerstroy.domain.sale.service.SaleServiceApiV1;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sale")
public class SaleControllerApiV1 {
    
    private final SaleServiceApiV1 saleServiceApiV1;

    // 판매페이지에서 카테고리idx로 판매테이블 가져오기
    @GetMapping("/category/{categoryIdx}")
    public ResponseEntity<?> getSaleListByCategoryIdx(@PathVariable Long categoryIdx){
        return saleServiceApiV1.getSaleListByCategoryIdx(categoryIdx);
    }

    @PostMapping
    public ResponseEntity<?> insertSaleTable(ReqSaleInsertDTO dto, HttpSession session) throws IOException{
        // System.out.println(name);
        System.out.println(dto);
        LoginUserDTO userDTO = (LoginUserDTO) session.getAttribute("dto");
        return saleServiceApiV1.insertSaleTable(dto, userDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateSaleTable(Long idx, ReqSaleUpdateDTO dto, HttpSession session) throws IOException {
        System.out.println("에ㅔㅔㅔㅔㅔㅔㅔㅔㅔㅔ" + dto);
        // System.out.println(saleIdx);
    
        LoginUserDTO userDTO = (LoginUserDTO) session.getAttribute("dto");


        return saleServiceApiV1.updateSaleTable(idx, dto, userDTO);
    }
}
