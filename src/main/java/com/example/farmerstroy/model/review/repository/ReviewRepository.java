package com.example.farmerstroy.model.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.farmerstroy.model.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{
    Optional<ReviewEntity> findByIdx(Long idx);

    List<ReviewEntity> findBySaleEntity_Idx(Long SaleEntity_idx);

}
