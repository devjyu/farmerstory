package com.example.farmerstroy.model.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.farmerstroy.model.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{
    
}
