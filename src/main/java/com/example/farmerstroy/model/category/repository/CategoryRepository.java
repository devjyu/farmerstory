package com.example.farmerstroy.model.category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.farmerstroy.model.category.entity.CategoryEntity;



public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
    Optional <CategoryEntity> findByIdx(Long idx);

}
