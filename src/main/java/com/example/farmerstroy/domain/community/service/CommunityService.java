package com.example.farmerstroy.domain.community.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.farmerstroy.common.exception.BadRequestException;
import com.example.farmerstroy.domain.community.dto.ResCommunityDTO;
import com.example.farmerstroy.domain.community.dto.ResCommunityDetailDTO;
import com.example.farmerstroy.model.community.entity.CommunityEntity;
import com.example.farmerstroy.model.community.repository.CommunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {
    
    private final CommunityRepository communityRepository;

    // 커뮤니티 전체 게시글 가져오기
    public ResCommunityDTO getCommunityData() {
        List<CommunityEntity> communityEntityList = communityRepository.findAll();
        ResCommunityDTO dto = ResCommunityDTO.of(communityEntityList);
        System.out.println(dto.getCommunityList().get(0) + "이건");
        // ResCommunityDTO dto = ResCommunityDTO.builder()
        // .communityList(ResCommunityDTO.Community.fromEntityList(communityEntityList))
        // .build();
        return dto;
    }

    // 커뮤니티 게시글 상세페이지 가져오기
    public ResCommunityDetailDTO getCommunityDetailData(Long comunityIdx) {
        Optional<CommunityEntity> communityEntityOptional = communityRepository.findByIdx(comunityIdx);
        if (communityEntityOptional.isEmpty()) {
            throw new BadRequestException("해당 페이지가 없습니다.");
        }
        ResCommunityDetailDTO dto = ResCommunityDetailDTO.builder()
        .community(ResCommunityDetailDTO.Community.fromEntity(communityEntityOptional.get()))
        .build();
        return dto;
    }
}
