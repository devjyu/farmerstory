package com.example.farmerstroy.model.sale.repositroy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.farmerstroy.model.sale.entity.SaleEntity;


public interface SaleRepository extends JpaRepository<SaleEntity, Long>{
    Optional<SaleEntity> findByIdx(Long idx);

    List<SaleEntity> findByCategoryEntity_Idx(Long categoryEntity_Idx);

    List<SaleEntity> findByCategoryEntity_Name(String categoryEntity_Name);
}
