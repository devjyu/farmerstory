package com.example.farmerstroy.domain.sale.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.common.dto.ResponseDTO;
import com.example.farmerstroy.domain.sale.dto.ResSaleDTO;
import com.example.farmerstroy.model.sale.entity.SaleEntity;
import com.example.farmerstroy.model.sale.repositroy.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleServiceApiV1 {

    private final SaleRepository saleRepository;

    // 판매페이지 게시글 카테고리 idx로 게시글 조회
    public ResponseEntity<?> getSaleListByCategoryIdx(Long categoryIdx) {
        List<SaleEntity> saleEntityList = saleRepository.findByCategoryEntity_Idx(categoryIdx);
        ResSaleDTO dto = ResSaleDTO.of(saleEntityList);
        return new ResponseEntity<>(
                ResponseDTO.builder()
                        .code(0)
                        .message("상품 조회에 성공했습니다.")
                        .data(dto)
                        .build(),
                HttpStatus.OK);
    }

}
