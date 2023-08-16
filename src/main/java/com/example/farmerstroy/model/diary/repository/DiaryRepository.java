package com.example.farmerstroy.model.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.farmerstroy.model.diary.entity.DiaryEntity;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Long>{
    
}
