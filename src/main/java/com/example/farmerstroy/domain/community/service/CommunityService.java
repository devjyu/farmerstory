package com.example.farmerstroy.domain.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.common.exception.BadRequestException;
import com.example.farmerstroy.domain.community.dto.ResCommunityDTO;
import com.example.farmerstroy.domain.community.dto.ResCommunityDetailDTO;
import com.example.farmerstroy.model.comment.entity.CommentEntity;
import com.example.farmerstroy.model.comment.repository.CommentRepository;
import com.example.farmerstroy.model.community.entity.CommunityEntity;
import com.example.farmerstroy.model.community.repository.CommunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {
    
    private final CommunityRepository communityRepository;
    private final CommentRepository commentRepository;

    // 커뮤니티 전체 게시글 가져오기
    public ResCommunityDTO getCommunityData() {
        List<CommunityEntity> communityEntityList = communityRepository.findAll();
        ResCommunityDTO dto = ResCommunityDTO.of(communityEntityList);
        // ResCommunityDTO dto = ResCommunityDTO.builder()
        // .communityList(ResCommunityDTO.Community.fromEntityList(communityEntityList))
        // .build();
        return dto;
    }

    // 커뮤니티 게시글 상세페이지 및 댓글 리스트 가져오기
    public ResCommunityDetailDTO getCommunityDetailData(Long communityIdx) {
        Optional<CommunityEntity> communityEntityOptional = communityRepository.findByIdx(communityIdx);
        if (communityEntityOptional.isEmpty()) {
            throw new BadRequestException("해당 페이지가 없습니다.");
        }

        List<CommentEntity> commentEntityList = commentRepository.findByCommunityEntity_Idx(communityIdx); // 해당 커뮤니티에 속한 댓글만 가져오도록 수정

        ResCommunityDetailDTO dto = ResCommunityDetailDTO.builder()
        .community(ResCommunityDetailDTO.Community.fromEntity(communityEntityOptional.get()))
        .commentList(ResCommunityDetailDTO.Comment.fromEntityList(commentEntityList))
        .build();

        return dto;
    }
}
