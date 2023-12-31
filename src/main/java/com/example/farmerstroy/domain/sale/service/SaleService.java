package com.example.farmerstroy.domain.sale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.common.exception.BadRequestException;
import com.example.farmerstroy.domain.sale.dto.ResSaleDTO;
import com.example.farmerstroy.domain.sale.dto.ResSaleDetailDTO;
import com.example.farmerstroy.model.category.entity.CategoryEntity;
import com.example.farmerstroy.model.category.repository.CategoryRepository;
import com.example.farmerstroy.model.review.entity.ReviewEntity;
import com.example.farmerstroy.model.review.repository.ReviewRepository;
import com.example.farmerstroy.model.sale.entity.SaleEntity;
import com.example.farmerstroy.model.sale.repositroy.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleService {
    
    private final SaleRepository saleRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;

    // 판매 페이지 전체 게시글리스트, 카테고리 전체 리스트
    public ResSaleDTO getSaleData() {
        List<SaleEntity> saleEntityList = saleRepository.findAll();
        List<CategoryEntity> categoryNameList = categoryRepository.findAll();
        ResSaleDTO dto = ResSaleDTO.of(saleEntityList, categoryNameList);
        return dto ;
    }

    // 판매 상세페이지 게시글 가져오기 및 리뷰 리스트 가져오기
    public ResSaleDetailDTO getSaleDetailData(Long saleIdx) {
        Optional<SaleEntity> saleEntityOptional = saleRepository.findByIdx(saleIdx);
        if (saleEntityOptional.isEmpty()) {
            throw new BadRequestException("해당 페이지가 없습니다.");
        }

        List<ReviewEntity> reviewEntityList = reviewRepository.findBySaleEntity_Idx(saleIdx);

        ResSaleDetailDTO dto = ResSaleDetailDTO.builder()
        .sale(ResSaleDetailDTO.Sale.fromEntity(saleEntityOptional.get()))
        .reviewList(ResSaleDetailDTO.Review.fromEntityList(reviewEntityList))
        .build();
        return dto;
    }
} 
