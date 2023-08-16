package com.example.farmerstroy.domain.sale.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.domain.sale.dto.ResSaleDTO;
import com.example.farmerstroy.model.category.entity.CategoryEntity;
import com.example.farmerstroy.model.category.repository.CategoryRepository;
import com.example.farmerstroy.model.sale.entity.SaleEntity;
import com.example.farmerstroy.model.sale.repositroy.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleService {
    
    private final SaleRepository saleRepository;
    private final CategoryRepository categoryRepository;

    public ResSaleDTO getSaleData() {
        List<SaleEntity> saleEntityList = saleRepository.findAll();
        List<CategoryEntity> categoryNameList = categoryRepository.findAll();
        ResSaleDTO dto = ResSaleDTO.of(saleEntityList, categoryNameList);
        return dto ;
    }
}
