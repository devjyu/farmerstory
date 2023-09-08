package com.example.farmerstroy.model.comment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.farmerstroy.model.comment.entity.CommentEntity;



public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
    Optional<CommentEntity> findByIdx(Long idx);

    List<CommentEntity> findByCommunityEntity_Idx(Long CommunityEntity_idx);
}
