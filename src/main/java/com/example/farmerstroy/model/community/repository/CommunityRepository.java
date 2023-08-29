package com.example.farmerstroy.model.community.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.farmerstroy.model.community.entity.CommunityEntity;

public interface CommunityRepository extends JpaRepository<CommunityEntity, Long>{

    Optional<CommunityEntity> findByIdx(Long idx);

    
}
