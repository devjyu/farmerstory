package com.example.farmerstroy.model.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.farmerstroy.model.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    // id 기준으로 가져오기
    Optional <UserEntity> findById(String id);

    // id값이 존재하는지
    boolean existsById(String id);

    // nickname값이 존재하는지
    boolean existsByNickname(String nickname);

    
}
